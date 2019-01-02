package slawomir.kustra.starysky.utils

import java.util.*
import kotlin.collections.ArrayList

fun getStarSize(screenWidth: Int): Int {
    val starSize = screenWidth / 15
    return rangeRandomValue((starSize * 0.7).toInt(), (starSize * 1.3.toInt()))
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
        add(Star(star = null, x = (screenWidth * 0.95f), y = (screenHeight * 0.05f))) //3
        add(Star(star = null, x = (screenWidth * 0.25f), y = (screenHeight * 0.4f))) //4
        add(Star(star = null, x = (screenWidth * 0.35f), y = (screenHeight * 0.45f))) //5
        add(Star(star = null, x = (screenWidth * 0.3f), y = (screenHeight * 0.5f))) //6
        add(Star(star = null, x = (screenWidth * 0.6f), y = (screenHeight * 0.33f))) //7
        add(Star(star = null, x = (screenWidth * 0.75f), y = (screenHeight * 0.7f))) //8
        add(Star(star = null, x = (screenWidth * 0.68f), y = (screenHeight * 0.75f))) //9
        add(Star(star = null, x = (screenWidth * 0.74f), y = (screenHeight * 0.8f))) //10
    }
    return stars
}