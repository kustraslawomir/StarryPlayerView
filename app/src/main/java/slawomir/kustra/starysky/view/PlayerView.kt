package slawomir.kustra.starysky.view

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.FrameLayout
import slawomir.kustra.starysky.R

class PlayerView : FrameLayout {

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        init(context)
    }

    private fun init(context: Context) {
        inflate(context, R.layout.player_controls, this)
        val starsView = StarsView(context)
        starsView.setBackgroundColor(Color.parseColor("#00000000"))
        val vinylView = VinylView(context)
        vinylView.setBackgroundColor(Color.parseColor("#00000000"))

        this.addView(vinylView)
        this.addView(starsView)
    }
}