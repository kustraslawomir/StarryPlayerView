package slawomir.kustra.starrysky

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.graphics.RectF
import slawomir.kustra.starrysky.utils.*


internal class StarsView : FrameLayout {

    private var stars: ArrayList<Star> = arrayListOf()

    private var starsContainerHeight = 0
    private var starsContainerWidth = 0
    private val alphaPaint = Paint()

    private var intervalHandler = Handler()
    private var invalidateHandler = Handler()

    private val rect = RectF(0f, 0f, 0f, 0f)

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

    fun scaleBitmapAndKeepRation(targetBmp: Bitmap, reqHeightInPixels: Int, reqWidthInPixels: Int): Bitmap {
        val matrix = Matrix()
        matrix.setRectToRect(
            RectF(0f, 0f, targetBmp.width.toFloat(), targetBmp.height.toFloat()),
            RectF(0f, 0f, reqWidthInPixels.toFloat(), reqHeightInPixels.toFloat()),
            Matrix.ScaleToFit.CENTER
        )
        return Bitmap.createBitmap(targetBmp, 0, 0, targetBmp.width, targetBmp.height, matrix, true)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)


        for (i in stars.indices) {
            if (stars[i].star != null) {

                val bitmapStar = stars[i].star

                if (bitmapStar != null) {
                    alphaPaint.alpha = stars[i].alpha

                    var startX = stars[i].x - bitmapStar.width / 2
                    var startY = stars[i].y - bitmapStar.height / 2
                    var endX = startX + bitmapStar.width
                    var endY = startY + bitmapStar.height

                    if (stars[i].shouldAnimate) {

                        startX = stars[i].x - bitmapStar.width / 2 * ((stars[i].alpha * 100 / 255) * 0.010).toFloat()
                        startY = stars[i].y - bitmapStar.height / 2 * ((stars[i].alpha * 100 / 255) * 0.010).toFloat()

                        endX = startX + bitmapStar.width * ((stars[i].alpha * 100 / 255) * 0.010).toFloat()
                        endY = startY + bitmapStar.height * ((stars[i].alpha * 100 / 255) * 0.010).toFloat()
                    }

                    rect.left = startX
                    rect.top = startY
                    rect.right = endX
                    rect.bottom = endY
                    canvas.drawBitmap(bitmapStar, null, rect, alphaPaint)
                    println("startX: $startX startY $startY endX $endX endY $endY")
                }
            }

        }
    }

    private fun init(context: Context) {
        starsContainerWidth = context.resources.displayMetrics.widthPixels
        starsContainerHeight = context.resources.displayMetrics.heightPixels

        stars = getStars(starsContainerWidth, starsContainerHeight / 2)

        for (i in 0..9) {
            val starSize = getStarSize(starsContainerWidth)
            val options = BitmapFactory.Options()
            options.inScaled = false
            val star = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.ic_star, options), starSize, starSize, true)
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
        animator.duration = rangeRandomValue(2500, 5000).toLong()

        var starToAnimate = rangeRandomValue(0, stars.size)

        while (animatingStars.contains(starToAnimate) && animatingStars.size < stars.size) {
            starToAnimate = rangeRandomValue(0, stars.size)
        }

        animatingStars.add(starToAnimate)
        stars[starToAnimate].shouldAnimate = getRandomBoolean()

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
