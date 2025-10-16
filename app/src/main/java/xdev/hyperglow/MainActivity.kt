package xdev.hyperglow

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class MainActivity : AppCompatActivity() {
    private var glowController: GlowController? = null
    lateinit var glowView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(
            findViewById(R.id.main)
        ) { v: View?, insets: WindowInsetsCompat? -> insets }
        glowView = findViewById(R.id.main)
        glowController = GlowController(glowView)
    }

    override fun onStart() {
        super.onStart()
        glowController!!.start(true)
    }

    override fun onStop() {
        super.onStop()
        glowController!!.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
