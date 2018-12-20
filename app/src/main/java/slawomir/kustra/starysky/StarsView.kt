package slawomir.kustra.starysky

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout

class StarsView : FrameLayout {

    private lateinit var starIcon: Bitmap
    private lateinit var starDestination: Rect
    private lateinit var paint: Paint

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

        canvas.drawBitmap(starIcon, 0f, 0f, paint)
    }

    private fun init(context: Context) {
        starIcon = BitmapFactory.decodeResource(context.resources, R.drawable.star)
        starDestination = Rect()
        paint = Paint()
    }
}