# Style开发者sdk
Style支持开发者自己实现动态壁纸效果，并以组件（插件）的方式被Style所加载（热加载）。
所有的组件都会在Style的**特效动态壁纸**列表里展现，供用户选择使用。

这篇文章主要介绍：
* 如何开发动态壁纸组件
* 如何测试
* 一些需要注意的地方

## 壁纸组件与壁纸应用的区别
尽管两者都以.apk形式存在，但组件不是一个完整的应用，不能够独立运行。

另外，组件中的WallpaperService实现，必须继承自`GLWallpaperServiceProxy`或者`WallpaperServiceProxy`，这是两个代理服务。前者是`GLWallpaperService`的子类，支持OpenGL。后者是系统`WallpaperService`的子类，支持普通的实现。而者两个代理类，都有一个带有`Context`参数的构造方法。
而在壁纸应用中，应用的WallpaperService通常是直接继承自`GLWallpaperService`或者`WallpaperService`的，并且必须有个无参的构造方法，供系统创建对象。

组件中需要实现sdk提供`IProvider`接口，以返回组件中的WallpaperService实现。

最后，组件中不支持.so文件。

组件中除了上述几点之外，其他的基本跟普通应用没有区别。因此现成的绝大多数壁纸应用转化成组件的成本、组件的开发成本非常低廉。
