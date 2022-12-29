package ua.sviatkuzbyt.randomcube.data.repositories

import android.content.Context
import ua.sviatkuzbyt.randomcube.R
import ua.sviatkuzbyt.randomcube.data.random.RandomNumber

class MainRepository(private val context: Context) {
    private val randomNumber = RandomNumber(context)

    suspend fun getRandomValue(type: Int): String{
        return when(type){
            R.id.numbers_menu -> getRandomNumber()
            else -> "ups..."
        }
    }

    private suspend fun getRandomNumber(): String{
        return try {
            randomNumber.getNumbers()
            randomNumber.getRandom().toString()
        } catch (e: Exception){
            context.getString(R.string.error_random)
        }
    }
}