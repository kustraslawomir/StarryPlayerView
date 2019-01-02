package slawomir.kustra.starysky.view

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import android.animation.PropertyValuesHolder
import slawomir.kustra.starysky.utils.Constants.Companion.PROPERTY_ROTATE


class VinylView : View {

    private var vinylContainerWidth: Int = 0
    private var vinylContainerHeight: Int = 0

    private lateinit var vinyl: Bitmap

    private val paint = Paint()
    private val animator = ValueAnimator()
    private var propertyRotate = PropertyValuesHolder.ofInt(PROPERTY_ROTATE, 0, 360)
    private var rotateAngle: Int = 0

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

            val left = (vinylContainerWidth - vinyl.width) / 2f
            val top = (vinylContainerHeight / 2 - vinyl.height) / 2f

            canvas.save()

            canvas.rotate(
                rotateAngle.toFloat(),
                (vinylContainerWidth - vinyl.width).toFloat(),
                (vinylContainerHeight / 2 - vinyl.height).toFloat()
            )
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

        animator.setValues(propertyRotate)
        animator.repeatMode = ValueAnimator.REVERSE
        animator.duration = 10000

        animator.addUpdateListener { animation ->
            val rotation = animation.getAnimatedValue(PROPERTY_ROTATE) as Int
            if (rotateAngle != rotation) {
                rotateAngle = rotation
                println("rotateAngle: $rotateAngle")
                invalidate()
            }
        }
        animator.start()
    }
}
