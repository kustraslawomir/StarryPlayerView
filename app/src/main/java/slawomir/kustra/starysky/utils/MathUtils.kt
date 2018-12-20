package slawomir.kustra.starysky.utils

import java.util.*


fun getStarSize(screenWidth: Int): Int {
    val starSize = screenWidth / 15
    return rand((starSize * 0.7).toInt(), starSize)
}

fun rand(from: Int, to: Int): Int {
    val random = Random()
    return random.nextInt(to - from) + from
}