package ua.sviatkuzbyt.randomcube.data.random

import android.content.Context
import ua.sviatkuzbyt.randomcube.R
import kotlin.random.Random

var selectedCategory = 0
class RandomCategory(private val context: Context) {
    private val yesNoLis = arrayOf(
        context.getString(R.string.yes),
        context.getString(R.string.no)
    )

    private val magicBallList = arrayOf(
        R.string.one_ball_answer,
        R.string.two_ball_answer,
        R.string.three_ball_answer,
        R.string.four_ball_answer,
        R.string.five_ball_answer,
        R.string.six_ball_answer,
        R.string.seven_ball_answer,
        R.string.eight_ball_answer,
        R.string.nine_ball_answer,
        R.string.ten_ball_answer,
        R.string.eleven_ball_answer,
        R.string.twelve_ball_answer,
        R.string.thirteen_ball_answer,
        R.string.fourteen_answer,
        R.string.fifteen_ball_answer,
        R.string.sixteen_ball_answer,
        R.string.seventeen_ball_answer,
        R.string.eighteen_ball_answer,
        R.string.nineteen_ball_answer,
        R.string.twenty_ball_answer,
    )

    fun getRandomCategory(): String{
        return when(selectedCategory){
            0 -> getRandomYesNo()
            1 -> getRandomNumberCube()
            else -> getMagicBallAnswer()
        }
    }
    private fun getRandomYesNo(): String{
        val number = getRandomNumber(0, 1)
        return yesNoLis[number]
    }

    private fun getRandomNumberCube() = getRandomNumber(1, 6).toString()

    private fun getMagicBallAnswer(): String{
        val number = getRandomNumber(0, magicBallList.size-1)
        val word = magicBallList[number]
        return context.getString(word)
    }

    private fun getRandomNumber(min: Int, max: Int) =
        Random.nextInt(min, max+1)
}