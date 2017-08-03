package com.yalin.style.engine;


import android.content.Context;
import android.service.wallpaper.WallpaperService;

/**
 * @author jinyalin
 * @since 2017/7/28.
 */

public class WallpaperServiceProxy extends WallpaperService {
    public WallpaperServiceProxy(Context host) {

    }

    @Override
    public Engine onCreateEngine() {
        return null;
    }

    public class ActiveEngine extends Engine {

    }
}
