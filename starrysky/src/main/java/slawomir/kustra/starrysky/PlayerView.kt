package slawomir.kustra.starrysky

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.controls_ui.view.*
import slawomir.kustra.starrysky.R.*
import slawomir.kustra.starrysky.utils.Constants.Companion.PAUSE
import slawomir.kustra.starrysky.utils.Constants.Companion.RESUME
import kotlin.properties.Delegates

open class PlayerView : FrameLayout {

    private lateinit var vinylView: VinylView

    private var playerState: Int by Delegates.observable(PAUSE) { _, _, new -> handlePlayerStateChange(new) }

    private fun handlePlayerStateChange(state: Int) {
        println("player state: $state")
        when (state) {
            PAUSE -> {
                vinylView.pausePlayerUi()
                mediaControl.setImageResource(drawable.ic_ico_play)
            }
            RESUME -> {
                vinylView.resumePlayerUi()
                mediaControl.setImageResource(drawable.ic_pause_button)
            }
        }
    }

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        init(context, attributeSet)
    }

    constructor(context: Context, attributeSet: AttributeSet, defStyleAttr: Int) : super(
        context,
        attributeSet,
        defStyleAttr
    ) {
        init(context, attributeSet)
    }

    private fun init(context: Context, attributeSet: AttributeSet?) {
        inflate(context, layout.controls_ui, this)

        vinylView = VinylView(context)
        vinylView.setBackgroundColor(Color.parseColor("#00000000"))
        addView(vinylView)

        var shouldDrawPlayerControls = true

        if (attributeSet != null) {
            val typedArray = context.theme.obtainStyledAttributes(attributeSet, styleable.PlayerView, 0, 0)
            try {
                shouldDrawPlayerControls = typedArray.getBoolean(R.styleable.PlayerView_player_visible, true)
            } finally {
                Log.d("PlayerView", "shouldDrawPlayerControls $shouldDrawPlayerControls")
            }
        }

        val starsView = StarsView(context)
        starsView.setBackgroundColor(Color.parseColor("#00000000"))
        addView(starsView)

        if (!shouldDrawPlayerControls)
            controls.visibility = View.GONE

        mediaControl.setOnClickListener {
            if (playerState == RESUME)
                pausePlayerUi()
            else resumePlayerUi()
        }
    }

    internal fun resumePlayerUi() {
        playerState = RESUME
    }

    internal fun pausePlayerUi() {
        playerState = PAUSE
    }
}