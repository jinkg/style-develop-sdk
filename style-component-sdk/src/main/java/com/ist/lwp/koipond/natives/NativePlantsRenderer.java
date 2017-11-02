package com.ist.lwp.koipond.natives;

public class NativePlantsRenderer {
    public NativePlantsRenderer() {
        NativeLibraryMethods.plantsrenderer_init();
    }

    public void render(float deltaTime) {
        NativeLibraryMethods.plantsrenderer_render(deltaTime);
    }

    public void setPlantsSizeScale(float sizeScale) {
        NativeLibraryMethods.plantsrenderer_setPlantsSizeScale(sizeScale);
    }
}
