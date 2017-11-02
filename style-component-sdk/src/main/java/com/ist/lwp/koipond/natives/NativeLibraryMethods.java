package com.ist.lwp.koipond.natives;

class NativeLibraryMethods {
    static native void antipiracy_defend();

    static native void baitsrenderer_addBait(int i, int i2, int i3);

    static native void baitsrenderer_init();

    static native void baitsrenderer_render();

    static native void bottomrenderer_init();

    static native void bottomrenderer_render();

    static native void bottomrenderer_setFogColorAtBottom(float f, float f2, float f3, float f4);

    static native void bottomrenderer_setFogDensityAtBottomNeg(float f);

    static native void faderenderer_animate();

    static native void faderenderer_init();

    static native boolean faderenderer_isFaded();

    static native void faderenderer_render(float f);

    static native void koi3drenderer_addKoi(int i, String str, float f);

    static native void koi3drenderer_init();

    static native void koi3drenderer_removeKoi(int i);

    static native void koi3drenderer_render(float f);

    static native void koi3drenderer_updateKoi(int i, String str, float f);

    static native void mainrenderer_init(int i, int i2);

    static native void mainrenderer_postRender();

    static native void mainrenderer_preRender(float f, float f2, float f3, float f4);

    static native void mainrenderer_setGyroEnabled(boolean z);

    static native void mainrenderer_setPanEnabled(boolean z);

    static native void mainrenderer_touchDown(int i, int i2, int i3);

    static native void mainrenderer_touchMove(int i, int i2, int i3, int i4, int i5);

    static native void mainrenderer_touchUp(int i, int i2, int i3);

    static native void mainrenderer_updateSurfaceCameraDestin(float f, float f2);

    static native void plantsrenderer_init();

    static native void plantsrenderer_render(float f);

    static native void plantsrenderer_setPlantsSizeScale(float f);

    static native void refractionsrenderer_init();

    static native void refractionsrenderer_render();

    static native void refractionsrenderer_setEsToLightDir(float f, float f2, float f3);

    static native void refractionsrenderer_setFogColorAtBottom(float f, float f2, float f3, float f4);

    static native void refractionsrenderer_setFogDensityAtBottomNeg(float f);

    static native void refractionsrenderer_setLightDiffuse(float f, float f2, float f3, float f4);

    static native double ripplesrenderer_getUnitWaterSpeed();

    static native float ripplesrenderer_getWaterDamping();

    static native float ripplesrenderer_getWaterTurbulencePercent();

    static native void ripplesrenderer_init(int i, float f, float f2);

    static native void ripplesrenderer_render(float f);

    static native void ripplesrenderer_renderRainDrop();

    static native void ripplesrenderer_setUnitWaterSpeed(double d);

    static native void ripplesrenderer_setWaterDamping(float f);

    static native void ripplesrenderer_setWaterTurbulencePercent(float f);

    static native void schoolrenderer_init(int i, float f);

    static native void schoolrenderer_render(float f);

    static native void schoolrenderer_setFishFinsColor(float f, float f2, float f3, float f4);

    static native void schoolrenderer_setFishScalesColor(float f, float f2, float f3, float f4);

    static native void schoolrenderer_setFogColor(float f, float f2, float f3, float f4);

    static native void schoolrenderer_setFogDensity(float f);

    static native void schoolrenderer_setFps(float f);

    static native void shadermanager_init();

    static native void surfacerenderer_init();

    static native void surfacerenderer_render();

    static native void surfacerenderer_setEnvReflectionsPercent(float f);

    static native void surfacerenderer_setEsToLightDir(float f, float f2, float f3);

    static native void surfacerenderer_setFogColor(float f, float f2, float f3, float f4);

    static native void surfacerenderer_setFogDensity(float f);

    static native void surfacerenderer_setLightAmbient(float f, float f2, float f3, float f4);

    static native void surfacerenderer_setLightDiffuse(float f, float f2, float f3, float f4);

    static native void texturemanager_init();

    static native void texturemanager_putTexture(String str, int i, int i2, int i3);

    static native void texturemanager_removeTexture(String str);

    NativeLibraryMethods() {
    }

    static {
        System.loadLibrary("koipond");
    }
}
