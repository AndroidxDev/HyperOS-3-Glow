package xdev.hyperglow

import android.graphics.BlendMode
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.os.Bundle
import android.view.View
import android.view.View.LAYER_TYPE_HARDWARE
import android.view.View.VISIBLE
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity() {
    lateinit var glowController: GlowController
    lateinit var mIconLogoView: ImageView
    lateinit var mLogoView: FrameLayout
    lateinit var mLogoViewShade: LinearLayout

    lateinit var renderViewLayout: RenderViewLayout
    lateinit var view2: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v: View?, insets: WindowInsetsCompat? -> insets }

        val renderViewLayout = findViewById<RenderViewLayout>(R.id.render_view_layout)

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

        val logoView: LinearLayout = findViewById(R.id.logo_view_linear_layout)
        logoView.setLayerType(LAYER_TYPE_HARDWARE, overlayPaint)


        //next
        val mNext = findViewById<ImageView>(R.id.next)
        val mArrow = findViewById<ImageView>(R.id.next_arrow)

        mNext.setImageResource(R.drawable.new_next)
        mArrow.setImageResource(R.drawable.icon_arrow)

        val nextViewLayout = findViewById<LinearLayout>(R.id.next_view_linear_layout)
        val nextViewShade = findViewById<LinearLayout>(R.id.new_view_linear_layout_shade)

        mNext.setColorFilter(getResources().getColor(R.color.logo_overlay_color, null))
        nextViewShade.visibility = VISIBLE
        val overlayPaint2 = Paint()
        overlayPaint2.blendMode = BlendMode.OVERLAY
        overlayPaint2.xfermode = PorterDuffXfermode(PorterDuff.Mode.OVERLAY)
        nextViewLayout.setLayerType(LAYER_TYPE_HARDWARE, overlayPaint2)

    }

    override fun onStart() {
        super.onStart()
        glowController.start(true)
        glowController.setCircleYOffsetWithView(
            findViewById(R.id.logo_image_wrapper),
            renderViewLayout
        )
    }

    override fun onStop() {
        super.onStop()
        glowController.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
