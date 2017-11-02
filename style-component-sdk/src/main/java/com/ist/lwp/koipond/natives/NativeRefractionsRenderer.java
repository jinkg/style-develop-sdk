package com.ist.lwp.koipond.natives;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

public class NativeRefractionsRenderer {
    public NativeRefractionsRenderer() {
        NativeLibraryMethods.refractionsrenderer_init();
    }

    public void render() {
        NativeLibraryMethods.refractionsrenderer_render();
    }

    public void setFogDensityAtBottomNeg(float fogDensityAtBottomNeg) {
        NativeLibraryMethods.refractionsrenderer_setFogDensityAtBottomNeg(fogDensityAtBottomNeg);
    }

    public void setEsToLightDir(Vector3 esToLightDir) {
        NativeLibraryMethods.refractionsrenderer_setEsToLightDir(esToLightDir.x, esToLightDir.y, esToLightDir.z);
    }

    public void setLightDiffuse(Color lightDiffuse) {
        NativeLibraryMethods.refractionsrenderer_setLightDiffuse(lightDiffuse.r, lightDiffuse.g, lightDiffuse.b, lightDiffuse.a);
    }

    public void setFogColorAtBottom(Color fogColorAtBottom) {
        NativeLibraryMethods.refractionsrenderer_setFogColorAtBottom(fogColorAtBottom.r, fogColorAtBottom.g, fogColorAtBottom.b, fogColorAtBottom.a);
    }
}
