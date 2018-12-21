package slawomir.kustra.starysky.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
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
        this.addView(StarsView(context, attributeSet, defStyleAttr))
        init(context)
    }

    private fun init(context: Context) {
        View.inflate(context, R.layout.player_controls, this)
    }
}