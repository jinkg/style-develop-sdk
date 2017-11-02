package com.ist.lwp.koipond.natives;

public class NativeRipplesRenderer {
    public NativeRipplesRenderer(int meshSize, float rightMost, float topMost) {
        NativeLibraryMethods.ripplesrenderer_init(meshSize, rightMost, topMost);
    }

    public double getUnitWaterSpeed() {
        return NativeLibraryMethods.ripplesrenderer_getUnitWaterSpeed();
    }

    public void setUnitWaterSpeed(double unitWaterSpeed) {
        NativeLibraryMethods.ripplesrenderer_setUnitWaterSpeed(unitWaterSpeed);
    }

    public float getWaterDamping() {
        return NativeLibraryMethods.ripplesrenderer_getWaterDamping();
    }

    public void setWaterDamping(float waterDamping) {
        NativeLibraryMethods.ripplesrenderer_setWaterDamping(waterDamping);
    }

    public float getWaterTurbulencePercent() {
        return NativeLibraryMethods.ripplesrenderer_getWaterTurbulencePercent();
    }

    public void setWaterTurbulencePercent(float waterTurbulencePercent) {
        NativeLibraryMethods.ripplesrenderer_setWaterTurbulencePercent(waterTurbulencePercent);
    }

    public void render(float delta) {
        NativeLibraryMethods.ripplesrenderer_render(delta);
    }

    public void renderRainDrop() {
        NativeLibraryMethods.ripplesrenderer_renderRainDrop();
    }
}
