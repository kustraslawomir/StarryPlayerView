package slawomir.kustra.starysky.utils

import java.util.*
import kotlin.collections.ArrayList

fun getStarSize(screenWidth: Int): Int {
    val starSize = screenWidth / 15
    return rangeRandomValue((starSize * 0.5).toInt(), (starSize * 1))
}

fun rangeRandomValue(from: Int, to: Int): Int {
    val random = Random()
    return random.nextInt(to - from) + from
}

fun getStars(screenWidth: Int, screenHeight: Int): ArrayList<Star> {
    val stars = arrayListOf<Star>()
    with(stars) {
        add(Star(star = null, x = (screenWidth * 0.08f), y = -30f)) //1
        add(Star(star = null, x = (screenWidth * 0.55).toFloat(), y = 0.98f)) //2
        add(Star(star = null, x = (screenWidth * 0.95f), y = (screenHeight * 0.1f))) //3
        add(Star(star = null, x = (screenWidth * 0.25f), y = (screenHeight * 0.25f))) //4
        add(Star(star = null, x = (screenWidth * 0.35f), y = (screenHeight * 0.35f))) //5
        add(Star(star = null, x = (screenWidth * 0.26f), y = (screenHeight * 0.45f))) //6
        add(Star(star = null, x = (screenWidth * 0.6f), y = (screenHeight * 0.33f))) //7
        add(Star(star = null, x = (screenWidth * 0.65f), y = (screenHeight * 0.6f))) //8
        add(Star(star = null, x = (screenWidth * 0.58f), y = (screenHeight * 0.65f))) //9
        add(Star(star = null, x = (screenWidth * 0.64f), y = (screenHeight * 0.7f))) //10
    }
    return stars
}