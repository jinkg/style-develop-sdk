package com.ist.lwp.koipond.natives;

import com.badlogic.gdx.graphics.Color;

public class NativeBottomRenderer {
    public NativeBottomRenderer() {
        NativeLibraryMethods.bottomrenderer_init();
    }

    public void render() {
        NativeLibraryMethods.bottomrenderer_render();
    }

    public void setFogDensityAtBottomNeg(float fogDensityAtBottomNeg) {
        NativeLibraryMethods.bottomrenderer_setFogDensityAtBottomNeg(fogDensityAtBottomNeg);
    }

    public void setFogColorAtBottom(Color color) {
        NativeLibraryMethods.bottomrenderer_setFogColorAtBottom(color.r, color.g, color.b, color.a);
    }
}
