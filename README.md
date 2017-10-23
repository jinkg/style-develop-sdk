# Style开发者sdk
Style支持开发者自己实现动态壁纸效果，并以组件（插件）的方式被Style所加载（热加载）。
所有的组件都会在Style的**特效动态壁纸**列表里展现，供用户选择使用。

同时**动态壁纸合集**应用也可以同样加载组件

这篇文章主要介绍：
* 组件介绍
* 如何开发组件
* 如何测试及上线
* 性能说明
* 代码混淆

## 什么是动态壁纸组件
#### 壁纸引擎框架
壁纸引擎框架是运行壁纸组件的环境，里面包含了壁纸渲染的依赖代码，例如BadLogicGames的相关代码。
引擎框架会在系统中注册空白的服务，并将空白服务的生命周期方法交给代理对象。
同时引擎框架也负责创建代理对象，并调用它们的生命周期方法。
可以理解为，引擎框架就是一副空壳。壁纸的渲染都由组件完成。

#### 壁纸组件与壁纸应用的区别
* 尽管两者都以.apk形式存在，但组件不是一个完整的应用，里面只包含关于壁纸渲染的代码和资源，不能够独立运行。

* 组件中的WallpaperService实现，必须继承自`WallpaperServiceProxy`、`GLWallpaperServiceProxy`或者`GDXWallpaperServiceProxy`，他们便是代理类。分别是普通壁纸渲染服务、OpenGL壁纸渲染服务、BadLogicGames游戏引擎壁纸渲染服务。他们会由
而壁纸应用中，应用的WallpaperService通常是直接继承自`GLWallpaperService`或者`WallpaperService`的，并且必须有个无参的构造方法，供系统创建对象。

* 组件依赖sdk模块，且需要实现sdk提供的`IProvider`接口，以返回组件中的WallpaperService代理对象。

* 组件中不支持.so文件。

* 组件中不需要有四大组件，只需要关注壁纸的渲染。

除了上述几点之外，其他的基本跟普通应用没有区别。因此现成的绝大多数壁纸应用转化成组件的成本、组件的开发成本都非常低廉。

#### 工程模块说明
本工程一共包含三个模块：
* sdk
* cube-wallpaper
* component-app
sdk模块是一个library模块，它是为开发者提供的组件编程规范。里面提供了`IProvider`接口、`WallpaperServiceProxy`，`GLWallpaperServiceProxy`和`GDXWallpaperServiceProxy`三个代理类，开发者需要实现三者之一以满足组件的规范。当前模块都代码都是编译时依赖的，运行时由引擎框架提供运行环境。因此此模块不能打到组件apk包中（在gradle中用`provided`而不是`compile`）。

cube-wallpaper模块是一个library模块，是壁纸的实现的一个demo，它实现`GLWallpaperServiceProxy`渲染出了一个立方体。该模块依赖sdk模块，但是在gradle中需要使用`provided`而不是`compile`,防止将sdk模块打到组件包里。

component-app模块是application模块。里面没有任何内容，只是简单的依赖了cube-wallpaper模块。你可以将它理解为组件的**壳**。因为在gradle中，application模块是不能使用`provided`来依赖的。因此需要多一层**壳**模块。

## 如何开发组件
仅需以下几步，即可创建你自己的组件壁纸

1、创建library模块（例如point-wallpaper,在他的build.gradle中增加sdk编译时依赖。
`provided ‘com.kinglloy.style.developer:style-component-sdk:1.0.0’`
```
dependencies {
    provided 'com.kinglloy.style.developer:style-component-sdk:1.0.0'
}
```

2、创建`PointWallpaperService`类，继承自`WallpaperServiceProxy`类，并创建`PointWallpaperEngine`类，继承自`WallpaperServiceProxy.ActiveEngine`类。形式如下。
```
public class PointWallpaperService extends WallpaperServiceProxy {
    public PointWallpaperService(Context host) {
        super(host);
    }

    @Override
    public Engine onCreateEngine() {
        return new PointWallpaperEngine();
    }

    private class PointWallpaperEngine extends ActiveEngine {
      ...
      // implement your wallpaper logic
    }
```

在`PointWallpaperEngine`引擎中实现的渲染逻辑和普通壁纸应用是一样的。

3、实现完渲染逻辑之后，接着实现`IProvider`接口，并通过其中的方法返回`PointWallpaperService`的对象。
```
package com.yalin.wallpaper.point;

public class ProviderImpl implements IProvider {
    @Override
    public WallpaperService provideProxy(Context host) {
        return new PointWallpaperService(host);
    }
}
```
4、创建**壳**模块`component-app`，里面什么都不需要，只需要在它都build.gradle文件中依赖由第1步创建的模块。

5、执行./gradlew assemble，会在component-app/build/outputs/apk/目录下产生组件的apk文件。那么一切就绪，组件开发完成。

组件包对签名没有要求，你可以使用任意密钥进行签名。

## 测试及上线
#### 测试
在本工程的根目录下，为大家提供了**sdk_test.apk**文件。它包含Style中组件的运行环境。所以只要保证它能运行组件即可。

测试应用会从设备存储中读取配置，并加载相应组件。
因此你需要一个配置文件**config.json**内容如下。并将它放到/sdcard/style/目录下面。
测试应用没有动态申请权限，运行前请确保将*读取外部存储*权限给**sdk_test.apk**。
```
{
  "component_name": "point.apk",
  "provider_name": "com.yalin.wallpaper.point.ProviderImpl"
}
```
其中**component_name**是组件包的文件名，也需要放在/sdcard/style/目录。**provider_name**是上面实现的`IProvider`类名称，包名+类名。

将**config.json**和**point.apk**都放在/sdcard/style/目录下之后，运行测试应用。点击按钮，如果壁纸能正常加载，那么我们就成功了。

#### 发布
要在Style和壁纸合集中发布组件，需要联系开发者jinkg@live.cn，或者QQ：349218364。
组件经过开发者审核之后，很快就会发布到Style和壁纸合集中。

## 性能说明
* Android动态壁纸的耗电量，内存占用，CPU使用等等很大程度上取决于具体的实现。因此在你的组件中，需要优化你的代码，让性能消耗变得更低。

* 当组件中出现崩溃时，会对Style和壁纸合集造成不同程度的影响。Style中变现为应用崩溃，壁纸合集中虽然应用不会崩溃，但也会影响用户体验。

* 组件首次运行需要用户下载，因此当组件包较大时，用户会消耗较长时间和较多流量。因此尽量减少组件包的大小。实践证明，组件包大小更多的取决于其中资源文件大小（图片），而组件代码量一般较少。




