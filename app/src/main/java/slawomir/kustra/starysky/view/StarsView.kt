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
import slawomir.kustra.starysky.utils.rangeRandomValue

internal class StarsView : FrameLayout {

    private var stars: ArrayList<Star> = arrayListOf()

    private var starsContainerHeight = 0
    private var starsContainerWidth = 0
    private val alphaPaint = Paint()

    private var intervalHandler = Handler()
    private var invalidateHandler = Handler()

    private val starsRunnable = object : Runnable {
        override fun run() {
            postDelayed(this, 5000)
            for (i in 0..stars.size) {
                animateStar()
            }
        }
    }

    private val invalidateRunnable = object : Runnable {
        override fun run() {
            postDelayed(this, 16)
            invalidate()
        }
    }

    private var animatingStars = arrayListOf<Int>()

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

        runAnimationInterval()
    }

    private fun runAnimationInterval() {

        intervalHandler.apply {
            postDelayed(starsRunnable, 0)
        }

        invalidateHandler.apply {
            postDelayed(invalidateRunnable, 0)
        }
    }

    private fun animateStar() {

        val animator = ValueAnimator.ofInt(0, 100)
        animator.duration = rangeRandomValue(2000, 5000).toLong()

        var starToAnimate = rangeRandomValue(0, stars.size)

        while (animatingStars.contains(starToAnimate) && animatingStars.size < stars.size) {
            starToAnimate = rangeRandomValue(0, stars.size)
        }

        animatingStars.add(starToAnimate)

        animator.addUpdateListener { animation ->
            val animatedValue = animation.animatedValue.toString().toInt()
            val starAlphaValue = (animatedValue * 0.01 * 255).toInt()

            if (animatedValue >= 50)
                stars[starToAnimate].alpha = starAlphaValue
            else
                stars[starToAnimate].alpha = 255 - starAlphaValue

            if (animatedValue == 100) {
                animatingStars.remove(starToAnimate)
            }
        }
        animator.start()
    }

    internal fun clearHandler() {
        intervalHandler.removeCallbacks(starsRunnable)
        invalidateHandler.removeCallbacks(invalidateRunnable)
    }
}
