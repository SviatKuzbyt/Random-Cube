package ua.sviatkuzbyt.randomcube.data.repositories

import android.content.Context
import ua.sviatkuzbyt.randomcube.R
import ua.sviatkuzbyt.randomcube.data.random.RandomNumber

class MainRepository(context: Context) {
    private val randomNumber = RandomNumber(context)

    suspend fun getRandomValue(type: Int): String{
        return when(type){
            R.id.numbers_menu -> getRandomNumber()
            else -> "ups..."
        }
    }

    private suspend fun getRandomNumber(): String{
        randomNumber.getNumbers()
        return randomNumber.getRandom().toString()
    }
}