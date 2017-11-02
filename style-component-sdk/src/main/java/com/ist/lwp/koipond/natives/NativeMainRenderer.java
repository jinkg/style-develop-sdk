package com.ist.lwp.koipond.natives;

import com.badlogic.gdx.math.Vector3;

public class NativeMainRenderer {
    public NativeMainRenderer(int windowWidth, int windowHeight) {
        NativeLibraryMethods.mainrenderer_init(windowWidth, windowHeight);
    }

    public void preRender(float deltaTime, Vector3 gyroVector) {
        NativeLibraryMethods.mainrenderer_preRender(deltaTime, gyroVector.x, gyroVector.y, gyroVector.z);
    }

    public void postRender() {
        NativeLibraryMethods.mainrenderer_postRender();
    }

    public void setGyroEnabled(boolean enabled) {
        NativeLibraryMethods.mainrenderer_setGyroEnabled(enabled);
    }

    public void touchDown(int screenX, int screenY, int pointer) {
        NativeLibraryMethods.mainrenderer_touchDown(screenX, screenY, pointer);
    }

    public void touchUp(int screenX, int screenY, int pointer) {
        NativeLibraryMethods.mainrenderer_touchUp(screenX, screenY, pointer);
    }

    public void touchMove(int screenX, int screenY, int deltaX, int deltaY, int pointer) {
        NativeLibraryMethods.mainrenderer_touchMove(screenX, screenY, deltaX, deltaY, pointer);
    }

    public void setPanEnabled(boolean enabled) {
        NativeLibraryMethods.mainrenderer_setPanEnabled(enabled);
    }

    public void updateSurfaceCameraDestin(float xOffsetPercent, float yOffsetPercent) {
        NativeLibraryMethods.mainrenderer_updateSurfaceCameraDestin(xOffsetPercent, yOffsetPercent);
    }
}
