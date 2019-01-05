package slawomir.kustra.starysky.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.os.Handler
import android.view.animation.LinearInterpolator

class VinylView : View {

    private var vinylContainerWidth: Int = 0
    private var vinylContainerHeight: Int = 0

    private lateinit var vinyl: Bitmap

    private val paint = Paint()
    private val animator = ValueAnimator.ofInt(0, 360)
    private var rotateAngle: Int = 0

    private var rotateHandler = Handler()

    private var rotateRunnable = object : Runnable {
        override fun run() {
            postDelayed(this, 5000)
            animator.start()
        }
    }

    private var lastTimeStamp = System.currentTimeMillis()

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
        if (::vinyl.isInitialized) {
            canvas.save()

            val left = (vinylContainerWidth - vinyl.width).toFloat()
            val top = (vinylContainerHeight / 2 - vinyl.height).toFloat()

            canvas.rotate(rotateAngle.toFloat(), left, top)

            canvas.drawBitmap(vinyl, left / 2, top / 2, paint)

            println("lastTimeStamp diff: ${System.currentTimeMillis() - lastTimeStamp}")
            lastTimeStamp = System.currentTimeMillis()

            canvas.restore()
        }
    }

    private fun init(context: Context) {
        vinylContainerWidth = context.resources.displayMetrics.widthPixels
        vinylContainerHeight = context.resources.displayMetrics.heightPixels

        val vinylSize = (vinylContainerHeight / 2 * 0.5).toInt()
        vinyl = Bitmap.createScaledBitmap(
            BitmapFactory.decodeResource(resources, slawomir.kustra.starysky.R.drawable.vinyl),
            vinylSize,
            vinylSize,
            true
        )
        animator.repeatCount = ValueAnimator.INFINITE
        animator.interpolator = LinearInterpolator()
        animator.duration = 5000

        animator.addUpdateListener { animation ->
            val value = animation.animatedValue.toString().toInt()
            if (rotateAngle != value) {
                rotateAngle = value
                println("rotateAngle: $rotateAngle")
                invalidate()
            }
            if (rotateAngle == 360) {
                println("reset animation")
                rotateAngle = 0
             }
        }
    }

    private fun runRotateHandler() {
        rotateHandler.apply {
            postDelayed(rotateRunnable, 0)
        }
    }

    private fun stopRotatingHandler() {
        rotateHandler.removeCallbacks(rotateRunnable)
    }

    fun resumePlayerUi() {
        println("resumePlayerUi $rotateAngle")
        if (rotateAngle != 0)
            animator.resume()
        else
            animator.start()
    }

    fun pausePlayerUi() {
        println("pausePlayerUi")
        animator.pause()
    }
}
