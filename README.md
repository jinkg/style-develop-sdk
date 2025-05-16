# Style Developer SDK

Style supports developers in creating their own live wallpaper effects and loading them as components (plugins) via Style (hot loading).
All components will be displayed in Style's **Special Effects Live Wallpapers** list for users to choose from.

Similarly, the **Live Wallpaper Collection** application can also load these components.

This article mainly introduces:

  * Component Introduction
  * How to Develop Components
  * How to Test and Go Live
  * Performance Notes
  * Code Obfuscation

## What is a Live Wallpaper Component?

#### Wallpaper Engine Framework

The wallpaper engine framework is the environment where wallpaper components run. It contains the dependency code for wallpaper rendering, such as relevant code from BadLogicGames.
The engine framework will register a blank service in the system and delegate the lifecycle methods of this blank service to a proxy object.
Simultaneously, the engine framework is also responsible for creating proxy objects and calling their lifecycle methods.
You can understand the engine framework as an empty shell. The actual wallpaper rendering is done by the component.

#### Differences Between Wallpaper Components and Wallpaper Applications

  * Although both exist in .apk format, a component is not a complete application. It only contains code and resources related to wallpaper rendering and cannot run independently.

  * The `WallpaperService` implementation in a component must inherit from `WallpaperServiceProxy`, `GLWallpaperServiceProxy`, or `GDXWallpaperServiceProxy`, which are the proxy classes. These correspond to regular wallpaper rendering services, OpenGL wallpaper rendering services, and BadLogicGames game engine wallpaper rendering services, respectively.
    In contrast, in a wallpaper application, the application's `WallpaperService` usually directly inherits from `GLWallpaperService` or `WallpaperService` and must have a no-argument constructor for the system to create an object.

  * Components depend on the SDK module and need to implement the `IProvider` interface provided by the SDK to return the `WallpaperService` proxy object within the component.

  * Components do not need to have the four major Android components (Activities, Services, Broadcast Receivers, Content Providers); they only need to focus on wallpaper rendering.

Apart from the points mentioned above, other aspects are basically the same as a normal application. Therefore, the cost of converting most existing wallpaper applications into components, and the development cost of new components, are very low.

#### Project Module Description

This project contains a total of three modules:

  * sdk
  * cube-wallpaper
  * component-app

The **sdk** module is a library module that provides developers with the component programming specification. It offers the `IProvider` interface and three proxy classes: `WallpaperServiceProxy`, `GLWallpaperServiceProxy`, and `GDXWallpaperServiceProxy`. Developers need to implement one of these three to meet the component specification. The code in this module is a compile-time dependency; the runtime environment is provided by the engine framework. Therefore, this module should not be packaged into the component APK (use `provided` instead of `compile` in Gradle).

The **cube-wallpaper** module is a library module and a demo of a wallpaper implementation. It implements `GLWallpaperServiceProxy` to render a cube. This module depends on the sdk module, but `provided` instead of `compile` should be used in Gradle to prevent packaging the sdk module into the component.

The **component-app** module is an application module. It contains no content and simply depends on the cube-wallpaper module. You can think of it as a **shell** for the component. This is because, in Gradle, application modules cannot use `provided` for dependencies. Therefore, an extra **shell** module is needed.

## How to Develop Components

You can create your own component wallpaper in just a few steps:

1.  Create a library module (e.g., point-wallpaper). In its build.gradle, add the SDK as a compile-time dependency.
    `provided ‘com.kinglloy.style.developer:style-component-sdk:1.0.0’`

<!-- end list -->

```
dependencies {
    provided 'com.kinglloy.style.developer:style-component-sdk:1.0.0'
}
```

2.  Create a `PointWallpaperService` class that inherits from the `WallpaperServiceProxy` class, and create a `PointWallpaperEngine` class that inherits from the `WallpaperServiceProxy.ActiveEngine` class. The structure is as follows:

<!-- end list -->

```java
public class PointWallpaperService extends WallpaperServiceProxy {
    public PointWallpaperService(Context host) {
        super(host);
    }

    @Override
    public Engine onCreateEngine() {
        return new PointWallpaperEngine();
    }

    private class PointWallpaperEngine extends ActiveEngine {
      // ...
      // implement your wallpaper logic
    }
}
```

The rendering logic implemented in the `PointWallpaperEngine` is the same as in a regular wallpaper application.

3.  After implementing the rendering logic, implement the `IProvider` interface and return an object of `PointWallpaperService` through its method.

<!-- end list -->

```java
package com.yalin.wallpaper.point;

public class ProviderImpl implements IProvider {
    @Override
    public WallpaperService provideProxy(Context host) {
        return new PointWallpaperService(host);
    }
}
```

4.  Create a **shell** module, `component-app`. It doesn't need any content; just add a dependency on the module created in step 1 in its build.gradle file.

5.  Execute `./gradlew assemble`. This will generate the component's APK file in the component-app/build/outputs/apk/ directory. And that's it, the component development is complete.

There are no signature requirements for the component package; you can sign it with any key.

## Testing and Going Live

#### Testing

In the root directory of this project, a **sdk\_test.apk** file is provided. It contains the runtime environment for components in Style. Therefore, you just need to ensure it can run your component.

The test application reads the configuration from device storage and loads the corresponding component.
Therefore, you need a configuration file named **config.json** with the following content. Place it in the /sdcard/style/ directory.
The test application does not dynamically request permissions. Before running, please ensure that the *Read external storage* permission is granted to **sdk\_test.apk**.

```json
{
  "component_name": "point.apk",
  "provider_name": "com.yalin.wallpaper.point.ProviderImpl"
}
```

Here, **component\_name** is the filename of the component package, which also needs to be placed in the /sdcard/style/ directory. **provider\_name** is the class name of the `IProvider` implementation created above (package name + class name).

After placing both **config.json** and **point.apk** in the /sdcard/style/ directory, run the test application. Click the button. If the wallpaper loads normally, then we have succeeded.

#### Publishing

To publish components in Style and Bayin Wallpaper, you need to contact the developer at nilaynij@gmail.com.
After the component is reviewed by the developer, it will be published to Style and Bayin Wallpaper soon.

## Performance Notes

  * The power consumption, memory usage, CPU usage, etc., of Android live wallpapers largely depend on the specific implementation. Therefore, in your component, you need to optimize your code to reduce performance consumption.

  * When a crash occurs in a component, it will affect Style and Bayin Wallpaper to varying degrees. In Style, it manifests as an application crash. Although the application will not crash in Bayin Wallpaper, it will still affect the user experience.

  * Users need to download the component when they first run it. Therefore, if the component package is large, users will consume more time and data. So, try to reduce the size of the component package. Practice has shown that the size of the component package depends more on the size of its resource files (images), while the amount of component code is generally small.

## Code Obfuscation

The engine framework creates an object of the `IProvider` implementation by its name and obtains the proxy object through its methods. Therefore, when obfuscating, you need to keep the `IProvider` implementation class. The obfuscation rule is as follows:

```
-keep class * implements com.yalin.style.engine.IProvider
```
