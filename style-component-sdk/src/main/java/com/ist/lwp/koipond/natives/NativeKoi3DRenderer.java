package com.ist.lwp.koipond.natives;

public class NativeKoi3DRenderer {
    public NativeKoi3DRenderer() {
        NativeLibraryMethods.koi3drenderer_init();
    }

    public void render(float deltaTime) {
        NativeLibraryMethods.koi3drenderer_render(deltaTime);
    }

    public void addKoi(int id, String species, float sizeScale) {
        NativeLibraryMethods.koi3drenderer_addKoi(id, species, sizeScale);
    }

    public void updateKoi(int id, String species, float sizeScale) {
        NativeLibraryMethods.koi3drenderer_updateKoi(id, species, sizeScale);
    }

    public void removeKoi(int instanceId) {
        NativeLibraryMethods.koi3drenderer_removeKoi(instanceId);
    }
}
