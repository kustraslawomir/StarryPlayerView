package slawomir.kustra.starrysky.utils

import android.graphics.Bitmap

class Star(
    var star: Bitmap?,
    val x: Float,
    val y: Float,
    var shouldAnimate: Boolean
) {
    internal var alpha = 255
}