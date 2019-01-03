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

            canvas.rotate(
                rotateAngle.toFloat(),
                (vinylContainerWidth - vinyl.width).toFloat(),
                (vinylContainerHeight / 2 - vinyl.height).toFloat()
            )

            val left = (vinylContainerWidth - vinyl.width) / 2f
            val top = (vinylContainerHeight / 2 - vinyl.height) / 2f
            canvas.drawBitmap(vinyl, left, top, paint)
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

        animator.duration = 5000
        animator.addUpdateListener { animation ->
            val value = animation.animatedValue.toString().toInt()
            if (rotateAngle != value) {
                rotateAngle = value
                println("rotateAngle: $rotateAngle")
                invalidate()
            }
        }

        rotateHandler.apply {
            postDelayed(rotateRunnable, 0)
        }
    }
}
