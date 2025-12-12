package xdev.hyperglow

import android.graphics.BlendMode
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.View.LAYER_TYPE_HARDWARE
import android.view.View.LAYER_TYPE_SOFTWARE
import android.view.View.VISIBLE
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import xdev.hyperglow.helpers.AnimHelper


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity() {
    lateinit var glowController: GlowController
    lateinit var mIconLogoView: ImageView
    lateinit var mLogoView: FrameLayout
    lateinit var mLogoViewShade: LinearLayout

    lateinit var mNext: ImageView

    lateinit var mArrow: ImageView

    var glowView: View? = null

    var renderViewLayout: RenderViewLayout? = null

    lateinit var view2: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v: View?, insets: WindowInsetsCompat? -> insets }
        // glowView = findViewById(R.id.main)
        val renderViewLayout: RenderViewLayout? = findViewById(R.id.render_view_layout)

        view2 = View(this)
        renderViewLayout?.attachView(view2, 0.2f, -16777216)
        glowController = GlowController(view2)

        mLogoView = findViewById(R.id.logo_image_wrapper)
        mIconLogoView = findViewById(R.id.app_icon_logo_view)
        mLogoViewShade = findViewById(R.id.logo_view_linear_layout_shade)


        //shade
        mIconLogoView.setImageResource(R.drawable.new_logo_image)
        mIconLogoView.setColorFilter(getResources().getColor(R.color.logo_overlay_color, null))
        mLogoViewShade.visibility = VISIBLE

        val overlayPaint = Paint()
        overlayPaint.blendMode = BlendMode.OVERLAY
        overlayPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.OVERLAY)
        mLogoView.setLayerType(LAYER_TYPE_HARDWARE, overlayPaint)

        //next
        mNext = findViewById(R.id.next)
        mArrow = findViewById(R.id.next_arrow)

        mNext.setImageResource(R.drawable.new_next)
        mArrow.setImageResource(R.drawable.icon_arrow)

        val nextViewLayout = findViewById<LinearLayout>(R.id.next_view_linear_layout)
        val nextViewShade = findViewById<LinearLayout>(R.id.new_view_linear_layout_shade)

        mNext.setColorFilter(getResources().getColor(R.color.logo_overlay_color, null))
        nextViewShade.visibility = VISIBLE
        nextViewLayout.setLayerType(LAYER_TYPE_HARDWARE, overlayPaint)

        mNext.setOnClickListener {
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        glowController.start(true)
        glowController.setCircleYOffsetWithView(
            findViewById(R.id.logo_image_wrapper),
            renderViewLayout
        )

        AnimHelper.startPageLogoAnim(mLogoView)
        AnimHelper.startPageLogoAnim(mLogoViewShade)

        mNext.alpha = 0.1f
        AnimHelper.startPageBtnAnim(findViewById(R.id.next_layout))

        Handler(Looper.getMainLooper()).postDelayed({
            mNext.alpha = 1f
        }, 6340)
    }

    override fun onStop() {
        super.onStop()
        glowController.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
