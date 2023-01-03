package ua.sviatkuzbyt.randomcube.data.repositories

import android.content.Context
import ua.sviatkuzbyt.randomcube.R
import ua.sviatkuzbyt.randomcube.data.random.RandomNumber
import ua.sviatkuzbyt.randomcube.data.random.RandomWords

class MainRepository(private val context: Context) {
    private val randomNumber = RandomNumber(context)
    private val randomWords = RandomWords(context)

    suspend fun getRandomValue(type: Int, isChange: Boolean): String{
        return when(type){
            R.id.numbers_menu -> getRandomNumber()
            R.id.words_menu -> getRandomWord()
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

    private fun getRandomWord(): String{
        return try {
            randomWords.loadWords()
            randomWords.getRandom()
        } catch (e: Exception){
            context.getString(R.string.error_random)
        }
    }

}