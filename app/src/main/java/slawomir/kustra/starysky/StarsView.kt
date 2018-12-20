package slawomir.kustra.starysky

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout
import slawomir.kustra.starysky.utils.getStarSize
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.DisplayMetrics





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

        canvas.drawBitmap(starIcon, 50f,50f,null)
    }

    private fun init(context: Context) {
        val screenWidth = context.resources.displayMetrics.widthPixels
        val starSize = getStarSize(screenWidth)
        println("screenWidth: $screenWidth, starSize: $starSize")

        starIcon = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(resources, R.drawable.star), starSize, starSize, false)

        starDestination = Rect()
        paint = Paint()
    }
}