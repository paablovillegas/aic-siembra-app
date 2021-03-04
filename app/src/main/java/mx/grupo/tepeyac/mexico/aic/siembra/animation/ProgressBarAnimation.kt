package mx.grupo.tepeyac.mexico.aic.siembra.animation

import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ProgressBar
import kotlin.math.roundToInt

class ProgressBarAnimation(
    private val progressBar: ProgressBar,
    private val from: Int,
    private val to: Int
) : Animation() {
    override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
        super.applyTransformation(interpolatedTime, t)
        val value = (from + (to - from) * interpolatedTime).roundToInt()
        progressBar.progress = value
    }
}