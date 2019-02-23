package slawomir.kustra.starrysky

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.widget.FrameLayout
import kotlinx.android.synthetic.main.player_controls.view.*
import slawomir.kustra.starysky.utils.Constants.Companion.PAUSE
import slawomir.kustra.starysky.utils.Constants.Companion.RESUME
import slawomir.kustra.starysky.view.StarsView
import slawomir.kustra.starysky.view.VinylView
import kotlin.properties.Delegates

open class PlayerView : FrameLayout {

    private lateinit var vinylView: VinylView

    private var playerState: Int by Delegates.observable(PAUSE) { _, _, new -> handlePlayerStateChange(new) }

    private fun handlePlayerStateChange(state: Int) {
        println("player state: $state")
        when (state) {
            PAUSE -> {
                vinylView.pausePlayerUi()
                mediaControl.setImageResource(R.drawable.ic_play_button)
            }
            RESUME -> {
                vinylView.resumePlayerUi()
                mediaControl.setImageResource(R.drawable.ic_pause_button)
            }
        }
    }

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

        vinylView = VinylView(context)
        vinylView.setBackgroundColor(Color.parseColor("#00000000"))
        addView(vinylView)

        val starsView = StarsView(context)
        starsView.setBackgroundColor(Color.parseColor("#00000000"))
        addView(starsView)

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