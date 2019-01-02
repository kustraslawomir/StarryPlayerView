package slawomir.kustra.starysky.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View


class VinylView : View {

    private var vinylContainerWidth: Int = 0
    private var vinylContainerHeight: Int = 0

    private lateinit var vinyl: Bitmap

    private val paint = Paint()

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
        if (::vinyl.isInitialized && vinylContainerWidth > 0) {

            canvas.drawBitmap(
                vinyl,
                (vinylContainerWidth - vinyl.width) / 2f,
                (vinylContainerHeight/2 - vinyl.height) / 2f,
                paint
            )

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
    }
}
