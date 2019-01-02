package slawomir.kustra.starysky.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout
import slawomir.kustra.starysky.utils.getStarSize
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import slawomir.kustra.starysky.R
import slawomir.kustra.starysky.utils.Star
import slawomir.kustra.starysky.utils.getStars
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.ScaleAnimation
import slawomir.kustra.starysky.utils.rangeRandomValue


internal class StarsView : FrameLayout {

    private var stars: ArrayList<Star> = arrayListOf()
    private var starsContainerHeight = 0
    private var starsContainerWidth = 0

    private val alphaPaint = Paint()

    private var scaleAnimation =
        ScaleAnimation(0.7f, 1f, 0.7f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context, attributeSet,
        defStyleAttr
    ) {
        init(context)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        for (i in stars.indices) {
            if (stars[i].star != null) {
                alphaPaint.alpha = stars[i].alpha
                println("alphaPaint.alpha ${alphaPaint.alpha}")
                canvas.drawBitmap(
                    stars[i].star!!,
                    stars[i].x,
                    stars[i].y,
                    alphaPaint
                )
            }
        }
    }

    private fun init(context: Context) {
        starsContainerWidth = context.resources.displayMetrics.widthPixels
        starsContainerHeight = context.resources.displayMetrics.heightPixels
        println("starsContainerHeight: $starsContainerHeight, starsContainerWidth: $starsContainerWidth")

        stars = getStars(starsContainerWidth, starsContainerHeight / 2)

        for (i in 0..9) {
            val starSize = getStarSize(starsContainerWidth)
            val star = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.star),
                starSize,
                starSize,
                true
            )
            stars[i].star = star
        }

        scaleAnimation.duration = 500
        scaleAnimation.fillAfter = true

        runStarsChooserTimer()
    }

    private fun runStarsChooserTimer() {
        Handler().apply {
            val runnable = object : Runnable {
                override fun run() {
                    postDelayed(this, 3000)
                    val starToAnimate = rangeRandomValue(0, stars.size)
                    val animator = ValueAnimator.ofInt(0, 100)
                    animator.duration = 2000
                    animator.interpolator = DecelerateInterpolator()
                    animator.addUpdateListener { animation ->

                        val animatedValue = animation.animatedValue.toString().toInt()
                        val starAlphaValue = (animatedValue * 0.01 * 255).toInt()

                        if (animatedValue >= 50)
                            stars[starToAnimate].alpha = starAlphaValue
                        else
                            stars[starToAnimate].alpha = 255 - starAlphaValue
                        invalidate()
                    }
                    animator.start()
                }
            }
            postDelayed(runnable, 0)
        }
    }
}
