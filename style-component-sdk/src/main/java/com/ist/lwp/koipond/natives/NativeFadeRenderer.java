package com.ist.lwp.koipond.natives;

public class NativeFadeRenderer {
    public NativeFadeRenderer() {
        NativeLibraryMethods.faderenderer_init();
    }

    public void render(float deltaTime) {
        NativeLibraryMethods.faderenderer_render(deltaTime);
    }

    public void animate() {
        NativeLibraryMethods.faderenderer_animate();
    }

    public boolean isFaded() {
        return NativeLibraryMethods.faderenderer_isFaded();
    }
}
