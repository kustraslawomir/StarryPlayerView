package slawomir.kustra.starysky.utils

import java.util.*


fun getStarSize(screenWidth: Int): Int {
    val starSize = screenWidth / 15
    return rand((starSize * 0.7).toInt(), (starSize * 1.3.toInt()))
}

fun rand(from: Int, to: Int): Int {
    val random = Random()
    return random.nextInt(to - from) + from
}

fun getRandomPosition(range: Int) = rand(0, range / 2).toFloat()
