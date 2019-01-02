package slawomir.kustra.starysky.view

import android.content.Context
import android.util.AttributeSet
import android.view.View

class VinylView : View {

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

    private fun init(context: Context) {

    }
}
