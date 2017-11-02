package com.ist.lwp.koipond.natives;

import com.badlogic.gdx.graphics.Color;

public class NativeSchoolRenderer {
    public NativeSchoolRenderer(int fishPopulation, float refFishLength) {
        NativeLibraryMethods.schoolrenderer_init(fishPopulation, refFishLength);
    }

    public void render(float deltaTime) {
        NativeLibraryMethods.schoolrenderer_render(deltaTime);
    }

    public void setFps(float fps) {
        NativeLibraryMethods.schoolrenderer_setFps(fps);
    }

    public void setFogDensity(float fogDensity) {
        NativeLibraryMethods.schoolrenderer_setFogDensity(fogDensity);
    }

    public void setFogColor(Color fogColor) {
        NativeLibraryMethods.schoolrenderer_setFogColor(fogColor.r, fogColor.g, fogColor.b, fogColor.a);
    }

    public void setFishScalesColor(Color fishScalesColor) {
        NativeLibraryMethods.schoolrenderer_setFishScalesColor(fishScalesColor.r, fishScalesColor.g, fishScalesColor.b, fishScalesColor.a);
    }

    public void setFishFinsColor(Color fishFinsColor) {
        NativeLibraryMethods.schoolrenderer_setFishFinsColor(fishFinsColor.r, fishFinsColor.g, fishFinsColor.b, fishFinsColor.a);
    }
}
