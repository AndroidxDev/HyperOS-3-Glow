package xdev.hyperglow

import android.view.View

class GlowController(private val mTarget: View) : Runnable {
    var mGlowPainter: GlowPainter? = null
    private var mLastGlobalTime: Long = 0
    private var mTime = 0f
    private var mTimeDirection = 1.0f

    private fun tickPingPong() {
        val nanoTime = System.nanoTime()
        val f = (((nanoTime - this.mLastGlobalTime).toDouble()) * 1.0E-9).toFloat()
        var f2 = this.mTime
        val f3 = this.mTimeDirection
        f2 += f * f3
        this.mTime = f2
        if (f3 > 0.0f) {
            if (f2 >= 120.0f) {
                this.mTimeDirection = -1.0f
            }
        } else if (f2 <= 2.0f) {
            this.mTimeDirection = 1.0f
        }
        this.mLastGlobalTime = nanoTime
    }

    override fun run() {
        if (this.mGlowPainter != null) {
            tickPingPong()
            this.mGlowPainter!!.setAnimTime(this.mTime)
            this.mGlowPainter!!.setResolution(
                this.mTarget.width.toFloat(),
                this.mTarget.height.toFloat()
            )
            this.mTarget.setRenderEffect(this.mGlowPainter!!.renderEffect)
            this.mTarget.postDelayed(this, 16)
        }
    }

    fun start(z: Boolean) {
        if (this.mGlowPainter == null) {
            val glowPainter = GlowPainter(this.mTarget.context)
            this.mGlowPainter = glowPainter
            glowPainter.needAdmission(z)
            this.mLastGlobalTime = System.nanoTime()
            resetTime()
            this.mTarget.post(this)
        }
    }


    fun stop() {
        if (this.mGlowPainter != null) {
            this.mTarget.removeCallbacks(this)
            this.mGlowPainter = null
            this.mTarget.setRenderEffect(null)
        }
    }

    fun resetTime() {
        this.mLastGlobalTime = System.nanoTime()
        this.mTime = 0.0f
    }
}