package ua.sviatkuzbyt.randomcube.ui.main.elements

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView

class CubeAnimation(cube: CardView, textOnCube: TextView) {
    private val rotationCube = ObjectAnimator.ofFloat(
        cube, View.ROTATION, 0f, 360f).apply{
        duration = 750
    }
    private val scaleDownX = ObjectAnimator.ofFloat(
        cube, View.SCALE_X, 1f, 0.75f).apply{
        duration = 375
    }
    private val scaleDownY = ObjectAnimator.ofFloat(
        cube, View.SCALE_Y, 1f, 0.75f).apply{
        duration = 375
    }
    private val scaleUpX = ObjectAnimator.ofFloat(
        cube, View.SCALE_X, 0.75f, 1f).apply{
        duration = 375
    }
    private val scaleUpY = ObjectAnimator.ofFloat(
        cube, View.SCALE_Y, 0.75f, 1f).apply{
        duration = 375
    }
    private val textAlpha = ObjectAnimator.ofFloat(
        textOnCube, View.ALPHA, 0f, 1f).apply{
        duration = 1000
    }

    fun play(){
        rotationCube.start()
        textAlpha.start()
        AnimatorSet().apply {
            play(scaleDownX).with(scaleDownY).before(scaleUpX).before(scaleUpY)
            start()
        }
    }
}
