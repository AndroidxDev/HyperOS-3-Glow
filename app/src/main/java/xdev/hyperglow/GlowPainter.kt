package xdev.hyperglow

import android.content.Context
import android.content.res.Resources
import android.graphics.RenderEffect
import android.graphics.RuntimeShader
import android.util.Log
import java.util.Objects
import java.util.Scanner

class GlowPainter(context: Context) {
    var mShader: RuntimeShader
    var uBrightnessInMax: Float = 1.0f
    var uBrightnessInMin: Float = 0.25f
    var uBrightnessOutMax: Float = 1.0f
    var uBrightnessOutMin: Float = 0.25f
    var uCircleAddBlend: Float = 0.04f
    var uCircleAnimationOffset: Float = 0.0f
    var uCircleColorFreq: Float = 1.0f
    var uCircleColorOffset: Float = 0.25f
    var uCircleColorSpeed: Float = 0.0f
    var uCircleEasing: Float = 1.4f
    var uCircleFinalRadius: Float = 1.0f
    var uCircleScreenBlend: Float = 1.0f
    var uCircleSpeed: Float = 0.9f
    var uCircleThickness: Float = 0.4f
    var uCircleUVDistort: Float = 0.0f
    var uCircleYOffset: Float = 0.1f
    var uColorInMax: Float = 1.0f
    var uColorInMin: Float = 0.3f
    var uColorMidPoint: Float = 0.47f
    var uColorOutMax: Float = 0.86f
    var uColorOutMin: Float = 0.3f
    var uColorToDistortWidthRatio: Float = 0.6f
    var uDistortEnd: Float = 1.0f
    var uDistortEndTime: Float = 0.3f
    var uDistortStart: Float = 0.0f
    var uDistortStartTime: Float = 0.2f
    var uMaskDelay: Float = 0.3f
    var uMaskThickness: Float = 0.3f
    var uScale: Float = 1.3f
    var uScale2: Float = 0.82f
    var uShowCircle: Float = 1.0f
    var uSpeed: Float = 0.4f
    var uSpeed2: Float = 0.49f
    var uStripeFrequency: Float = 0.0f
    var uStripeStrengthX: Float = 0.0f
    var uStripeStrengthY: Float = 0.0f
    var uStripeUVDistort: Float = 0.0f
    var uUseOklab: Float = 1.0f

    init {
        val loadShader = loadShader(context.resources, R.raw.glow)
        Objects.requireNonNull<String?>(loadShader).javaClass
        val runtimeShader = RuntimeShader(loadShader!!)
        this.mShader = runtimeShader
        runtimeShader.setFloatUniform("uScale2", this.uScale2)
        this.mShader.setFloatUniform("uSpeed2", this.uSpeed2)
        this.mShader.setFloatUniform("uColorInMin", this.uColorInMin)
        this.mShader.setFloatUniform("uColorInMax", this.uColorInMax)
        this.mShader.setFloatUniform("uColorOutMin", this.uColorOutMin)
        this.mShader.setFloatUniform("uColorOutMax", this.uColorOutMax)
        this.mShader.setFloatUniform("uColorMidPoint", this.uColorMidPoint)
        this.mShader.setFloatUniform("uUseOklab", this.uUseOklab)
        this.mShader.setFloatUniform(
            "uColorBlack",
            //toFloatArray(context.getColor(android.R.color.system_accent1_700))
            floatArrayOf(0.961f, 0.157f, 0.157f)
        )
        this.mShader.setFloatUniform(
            "uColorMid",
            //toFloatArray(context.getColor(android.R.color.system_accent2_400))
            floatArrayOf(0.604f, 0.659f, 0.961f)
        )
        this.mShader.setFloatUniform(
            "uColorWhite",
            //toFloatArray(context.getColor(android.R.color.system_accent3_100))
            floatArrayOf(0.302f, 0.29f, 0.843f)
        )
        this.mShader.setFloatUniform("uScale", this.uScale)
        this.mShader.setFloatUniform("uSpeed", this.uSpeed)
        this.mShader.setFloatUniform("uBrightnessInMin", this.uBrightnessInMin)
        this.mShader.setFloatUniform("uBrightnessInMax", this.uBrightnessInMax)
        this.mShader.setFloatUniform("uBrightnessOutMin", this.uBrightnessOutMin)
        this.mShader.setFloatUniform("uBrightnessOutMax", this.uBrightnessOutMax)
        this.mShader.setFloatUniform("uShowCircle", this.uShowCircle)
        this.mShader.setFloatUniform("uCircleThickness", this.uCircleThickness)
        this.mShader.setFloatUniform("uCircleFinalRadius", this.uCircleFinalRadius)
        this.mShader.setFloatUniform("uCircleYOffset", this.uCircleYOffset)
        this.mShader.setFloatUniform("uCircleSpeed", this.uCircleSpeed)
        this.mShader.setFloatUniform("uCircleColorFreq", this.uCircleColorFreq)
        this.mShader.setFloatUniform("uCircleColorSpeed", this.uCircleColorSpeed)
        this.mShader.setFloatUniform("uCircleEasing", this.uCircleEasing)
        this.mShader.setFloatUniform("uCircleAnimationOffset", this.uCircleAnimationOffset)
        this.mShader.setFloatUniform("uMaskDelay", this.uMaskDelay)
        this.mShader.setFloatUniform("uMaskThickness", this.uMaskThickness)
        this.mShader.setFloatUniform("uCircleScreenBlend", this.uCircleScreenBlend)
        this.mShader.setFloatUniform("uCircleAddBlend", this.uCircleAddBlend)
        this.mShader.setFloatUniform("uCircleColorOffset", this.uCircleColorOffset)
        this.mShader.setFloatUniform("uCircleUVDistort", this.uCircleUVDistort)
        this.mShader.setFloatUniform("uColorToDistortWidthRatio", this.uColorToDistortWidthRatio)
        this.mShader.setFloatUniform("uDistortStartTime", this.uDistortStartTime)
        this.mShader.setFloatUniform("uDistortEndTime", this.uDistortEndTime)
        this.mShader.setFloatUniform("uDistortStart", this.uDistortStart)
        this.mShader.setFloatUniform("uDistortEnd", this.uDistortEnd)
        this.mShader.setFloatUniform("uStripeFrequency", this.uStripeFrequency)
        this.mShader.setFloatUniform("uStripeStrengthX", this.uStripeStrengthX)
        this.mShader.setFloatUniform("uStripeStrengthY", this.uStripeStrengthY)
        this.mShader.setFloatUniform("uStripeUVDistort", this.uStripeUVDistort)
    }

    private fun loadShader(resources: Resources, i: Int): String? {
        try {
            val openRawResource = resources.openRawResource(i)
            try {
                val scanner = Scanner(openRawResource)
                try {
                    val stringBuilder = StringBuilder()
                    while (scanner.hasNextLine()) {
                        stringBuilder.append(scanner.nextLine())
                        stringBuilder.append("\n")
                    }
                    val stringBuilder2 = stringBuilder.toString()
                    scanner.close()
                    openRawResource.close()
                    return stringBuilder2
                } catch (_: Throwable) {
                    // th.addSuppressed(th);
                }
            } catch (_: Throwable) {
                //  th.addSuppressed(th2);
            }
        } catch (e: Exception) {
            Log.e("Error", e.toString())
            return null
        }
        return null
    }

    fun setCircleYOffset(f: Float) {
        this.uCircleYOffset = f
        this.mShader.setFloatUniform("uCircleYOffset", f)
    }

    fun getRenderEffect(): RenderEffect {
        return RenderEffect.createShaderEffect(this.mShader)
    }

    fun needAdmission(isNeed: Boolean) {
        this.mShader.setFloatUniform("uShowCircle", if (isNeed) 1.0f else 0.0f)
    }

    fun setAnimTime(f: Float) {
        this.mShader.setFloatUniform("uTime", f)
    }

    fun setResolution(f: Float, f2: Float) {
        this.mShader.setFloatUniform("uResolution", f, f2)
    }
}
