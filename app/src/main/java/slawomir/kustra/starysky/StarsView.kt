package slawomir.kustra.starysky

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout
import slawomir.kustra.starysky.utils.getStarSize
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import slawomir.kustra.starysky.utils.getRandomPosition

class StarsView : FrameLayout {

    private val stars: ArrayList<Bitmap> = arrayListOf()
    private var starsContainerHeight = 0
    private var starsContainerWidth = 0


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
            canvas.drawBitmap(stars[i], getRandomPosition(starsContainerWidth), getRandomPosition(starsContainerHeight), null)
        }
    }

    private fun init(context: Context) {
        starsContainerWidth = context.resources.displayMetrics.widthPixels
        starsContainerHeight = context.resources.displayMetrics.heightPixels
        println("starsContainerHeight: $starsContainerHeight, starsContainerHeight: $starsContainerHeight")

        for (i in 1..10) {
            val starSize = getStarSize(starsContainerWidth)
            stars.add(
                Bitmap.createScaledBitmap(
                    BitmapFactory.decodeResource(resources, R.drawable.star),
                    starSize,
                    starSize,
                    false
                )
            )
        }

    }
}