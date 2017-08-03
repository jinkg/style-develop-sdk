# Style开发者sdk
Style支持开发者自己实现动态壁纸效果，并以组件（插件）的方式被Style所加载（热加载）。
所有的组件都会在Style的**特效动态壁纸**列表里展现，供用户选择使用。

这篇文章主要介绍：
* 如何开发动态壁纸组件
* 如何测试
* 一些需要注意的地方

### 壁纸组件与壁纸应用的区别
尽管两者都以.apk形式存在，但组件不是一个完整的应用，不能够独立运行。

另外，组件中的WallpaperService实现，必须继承自`GLWallpaperServiceProxy`或者`WallpaperServiceProxy`，这是两个代理服务。前者是`GLWallpaperService`的子类，支持OpenGL。后者是系统`WallpaperService`的子类，支持普通的实现。而者两个代理类，都有一个带有`Context`参数的构造方法。
而在壁纸应用中，应用的WallpaperService通常是直接继承自`GLWallpaperService`或者`WallpaperService`的，并且必须有个无参的构造方法，供系统创建对象。

组件中需要实现sdk提供`IProvider`接口，以返回组件中的WallpaperService实现。

最后，组件中不支持.so文件。

组件中除了上述几点之外，其他的基本跟普通应用没有区别。因此现成的绝大多数壁纸应用转化成组件的成本、组件的开发成本非常低廉。

### 工程模块说明
本工程一共包含三个模块：
* sdk
* cube-wallpaper
* component-app
sdk模块是一个library模块，它是为开发者提供的组件编程规范。里面提供了`IProvider`接口、`GLWallpaperServiceProxy`类和`WallpaperServiceProxy`类，开发者需要实现他们以满足组件的规范。

cube-wallpaper模块是一个library模块，是壁纸的实现的一个demo，它实现`GLWallpaperServiceProxy`渲染出了一个立方体。该模块依赖sdk模块，但是在gradle中需要使用`provided`而不是`compile`,防止将sdk模块打到组件包里。

component-app模块是application模块。里面没有任何内容，只是简单的依赖了cube-wallpaper模块。你可以将它理解为组件的**壳**

## 第一个组件壁纸
仅需以下几步，即可创建你自己的组件壁纸

1、导入sdk模块

2、新建library模块（例如poing-wallpaper），并通过`provided project(':sdk')`依赖sdk模块。

3、新建`PointWallpaperService`类，继承自`WallpaperServiceProxy`类，并新建`PoingWallpaperEngine`类，继承自`WallpaperServiceProxy.ActiveEngine`类。例如
```
public class PointWallpaperService extends WallpaperServiceProxy {
    public PointWallpaperService(Context host) {
        super(host);
    }

    @Override
    public Engine onCreateEngine() {
        return new PoingWallpaperEngine();
    }

    private class PoingWallpaperEngine extends ActiveEngine {
      ...
      // implement your wallpaper logic
    }
```

4、实现`IProvider`接口，并通过其中的方法返回一个`PointWallpaperService`对象。
```
package com.yalin.wallpaper.point;

public class ProviderImpl implements IProvider {
    @Override
    public WallpaperService provideProxy(Context host) {
        return new PointWallpaperService(host);
    }
}
```

5、执行./gradlew assemble，会在component-app/build/outputs/apk/目录下产生组件的apk文件。那么一切就绪，组件开发完成。

## 测试壁纸组件
组件在Style中发布，需要经过审核，以保证组件质量。

在本工程的根目录下，为大家提供了**sdk_test.apk**文件。它包含Style中组件的运行环境。所以只要保证它能运行组件即可。

另外，你还需要一个配置文件**config.json**内容如下。并将它放到**/sdcard/style/**目录下面。
```
{
  "component_name": "point.apk",
  "provider_name": "com.yalin.wallpaper.point.ProviderImpl"
}
```
