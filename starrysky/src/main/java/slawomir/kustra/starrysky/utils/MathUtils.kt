package slawomir.kustra.starrysky.utils

import java.util.*
import kotlin.collections.ArrayList

fun getStarSize(screenWidth: Int): Int {
    val starSize = screenWidth / 11
    return rangeRandomValue((starSize * 0.3).toInt(), (starSize * 1))
}

fun rangeRandomValue(from: Int, to: Int): Int {
    val random = Random()
    return random.nextInt(to - from) + from
}

fun getRandomBoolean(): Boolean {
    val random = Random()
    return random.nextBoolean()
}

fun getStars(screenWidth: Int, screenHeight: Int): ArrayList<Star> {
    val stars = arrayListOf<Star>()
    with(stars) {
        add(Star(star = null, x = (screenWidth * 0.08f), y = 0.85f, shouldAnimate = getRandomBoolean())) //1
        add(Star(star = null, x = (screenWidth * 0.55).toFloat(), y = 0.98f, shouldAnimate = getRandomBoolean())) //2
        add(
            Star(
                star = null,
                x = (screenWidth * 0.95f),
                y = (screenHeight * 0.1f),
                shouldAnimate = getRandomBoolean()
            )
        ) //3
        add(
            Star(
                star = null,
                x = (screenWidth * 0.25f),
                y = (screenHeight * 0.25f),
                shouldAnimate = getRandomBoolean()
            )
        ) //4
        add(
            Star(
                star = null,
                x = (screenWidth * 0.35f),
                y = (screenHeight * 0.35f),
                shouldAnimate = getRandomBoolean()
            )
        ) //5
        add(
            Star(
                star = null,
                x = (screenWidth * 0.26f),
                y = (screenHeight * 0.45f),
                shouldAnimate = getRandomBoolean()
            )
        ) //6
        add(
            Star(
                star = null,
                x = (screenWidth * 0.6f),
                y = (screenHeight * 0.33f),
                shouldAnimate = getRandomBoolean()
            )
        ) //7
        add(
            Star(
                star = null,
                x = (screenWidth * 0.65f),
                y = (screenHeight * 0.6f),
                shouldAnimate = getRandomBoolean()
            )
        ) //8
        add(
            Star(
                star = null,
                x = (screenWidth * 0.58f),
                y = (screenHeight * 0.65f),
                shouldAnimate = getRandomBoolean()
            )
        ) //9
        add(
            Star(
                star = null,
                x = (screenWidth * 0.64f),
                y = (screenHeight * 0.7f),
                shouldAnimate = getRandomBoolean()
            )
        ) //10
    }
    return stars
}