package slawomir.kustra.starysky

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.widget.FrameLayout
import slawomir.kustra.starysky.utils.getStarSize
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import slawomir.kustra.starysky.utils.Star
import slawomir.kustra.starysky.utils.getRandomStarPosition
import slawomir.kustra.starysky.utils.getStars

class StarsView : FrameLayout {

    private var stars: ArrayList<Star> = arrayListOf()
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
            if (stars[i].star != null)
                canvas.drawBitmap(
                    stars[i].star!!,
                    stars[i].x,
                    stars[i].y,
                    null
                )
        }
    }

    private fun init(context: Context) {
        starsContainerWidth = context.resources.displayMetrics.widthPixels
        starsContainerHeight = context.resources.displayMetrics.heightPixels
        println("starsContainerHeight: $starsContainerHeight, starsContainerHeight: $starsContainerHeight")

        stars = getStars(starsContainerWidth, starsContainerHeight / 2)

        for (i in 0..9) {
            val starSize = getStarSize(starsContainerWidth)
            val star = Bitmap.createScaledBitmap(
                BitmapFactory.decodeResource(resources, R.drawable.star),
                starSize,
                starSize,
                false
            )
            stars[i].star = star
        }

    }
}