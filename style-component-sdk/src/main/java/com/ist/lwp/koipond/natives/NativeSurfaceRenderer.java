package com.ist.lwp.koipond.natives;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector3;

public class NativeSurfaceRenderer {
    public NativeSurfaceRenderer() {
        NativeLibraryMethods.surfacerenderer_init();
    }

    public void render() {
        NativeLibraryMethods.surfacerenderer_render();
    }

    public void setFogDensity(float fogDensity) {
        NativeLibraryMethods.surfacerenderer_setFogDensity(fogDensity);
    }

    public void setEnvReflectionsPercent(float envReflectionsPercent) {
        NativeLibraryMethods.surfacerenderer_setEnvReflectionsPercent(envReflectionsPercent);
    }

    public void setEsToLightDir(Vector3 esToLightDir) {
        NativeLibraryMethods.surfacerenderer_setEsToLightDir(esToLightDir.x, esToLightDir.y, esToLightDir.z);
    }

    public void setFogColor(Color fogColor) {
        NativeLibraryMethods.surfacerenderer_setFogColor(fogColor.r, fogColor.g, fogColor.b, fogColor.a);
    }

    public void setLightDiffuse(Color lightDiffuse) {
        NativeLibraryMethods.surfacerenderer_setLightDiffuse(lightDiffuse.r, lightDiffuse.g, lightDiffuse.b, lightDiffuse.a);
    }

    public void setLightAmbient(Color lightAmbient) {
        NativeLibraryMethods.surfacerenderer_setLightAmbient(lightAmbient.r, lightAmbient.g, lightAmbient.b, lightAmbient.a);
    }
}
