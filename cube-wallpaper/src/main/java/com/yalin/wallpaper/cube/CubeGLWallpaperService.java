package com.yalin.wallpaper.cube;

import android.content.Context;
import android.service.wallpaper.WallpaperService;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.yalin.style.engine.GLWallpaperServiceProxy;

/**
 * @author jinyalin
 * @since 2017/7/24.
 */

public class CubeGLWallpaperService extends GLWallpaperServiceProxy {
    public CubeGLWallpaperService(Context host) {
        super(host);
    }

    @Override
    public WallpaperService.Engine onCreateEngine() {
        return new AdvanceEngineGL();
    }

    private class AdvanceEngineGL extends GLActiveEngine {
        CubeRenderer renderer;

        public AdvanceEngineGL() {
            super();
            renderer = new CubeRenderer(CubeGLWallpaperService.this);
            setRenderer(renderer);
            setRenderMode(RENDERMODE_CONTINUOUSLY);
        }

        @Override
        public void onCreate(SurfaceHolder surfaceHolder) {
            super.onCreate(surfaceHolder);
            // Add touch events
            setTouchEventsEnabled(true);
        }

        @Override
        public void onTouchEvent(MotionEvent event) {
            super.onTouchEvent(event);
            renderer.onTouchEvent(event);
        }

        @Override
        public void onDestroy() {
            super.onDestroy();
            if (renderer != null) {
                renderer.release();
            }
            renderer = null;
        }
    }
}
