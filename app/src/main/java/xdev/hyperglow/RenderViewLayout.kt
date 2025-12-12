package xdev.hyperglow

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import kotlin.math.ceil
import androidx.core.view.size


class RenderViewLayout : ViewGroup {
    var mChildScale: Float = 0.5f

    override fun onLayout(z: Boolean, i: Int, i2: Int, i3: Int, i4: Int) {
        Log.e("RENDERVIEW", "onLayout: width=$width height=$height childCount=$childCount")
        var i: Int
        var i2: Int
        var i3: Int
        val childCount = size
        if (childCount != 0) {
            i = ceil(((width.toFloat()) * this.mChildScale).toDouble()).toInt()
            i2 = ceil(((height.toFloat()) * this.mChildScale).toDouble()).toInt()
            i3 = 0
            while (i3 < childCount) {
                val childAt = getChildAt(i3)
                if (childAt.visibility != GONE) {
                    childAt.scaleX = 1.0f / this.mChildScale
                    childAt.scaleY = 1.0f / this.mChildScale
                    val width = (((width - i).toFloat()) * 0.5f).toInt()
                    val height = (((height - i2).toFloat()) * 0.5f).toInt()
                    childAt.layout(width, height, width + i, height + i2)
                }
                i3++
            }
        }
    }


    fun attachView(view: View, f: Float) {
        this.mChildScale = f
        addView(view)
        if (view !is TextureView) {
            view.setBackgroundColor(Color.TRANSPARENT)
        }
    }

    fun attachView(view: View, f: Float, i: Int) {
        this.mChildScale = f
        addView(view)
        if (view !is TextureView) {
            view.setBackgroundColor(i)
        }
    }

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attributeSet: AttributeSet?) : super(context, attributeSet)

    constructor(context: Context?, attributeSet: AttributeSet?, i: Int) : super(context, attributeSet, i)

   // constructor(context: Context?, attributeSet: AttributeSet?, i: Int, i2: Int)

    val internalHeight: Int
        get() = ((height.toFloat()) * this.mChildScale).toInt()

    val internalWidth: Int
        get() = ((width.toFloat()) * this.mChildScale).toInt()
}