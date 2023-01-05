package ua.sviatkuzbyt.randomcube.data.repositories

import android.content.Context
import ua.sviatkuzbyt.randomcube.R
import ua.sviatkuzbyt.randomcube.data.random.RandomCategory
import ua.sviatkuzbyt.randomcube.data.random.RandomNumber
import ua.sviatkuzbyt.randomcube.data.random.RandomWords

class MainRepository(private val context: Context) {
    private var randomNumber: RandomNumber? = null
    private var randomWords: RandomWords? = null
    private var randomCategory: RandomCategory? = null

    suspend fun getRandomValue(type: Int): String{
        return when(type){
            R.id.numbers_menu -> getRandomNumber()
            R.id.words_menu -> getRandomWord()
            else -> getRandomCategory()
        }
    }

    private suspend fun getRandomNumber(): String{
        return try {
            initRandomNumbers()
            randomNumber!!.getRandom()
        } catch (e: Exception){
            context.getString(R.string.error_random)
        }
    }
    private fun initRandomNumbers(){
        if (randomNumber == null)
            randomNumber = RandomNumber(context)
    }

    private fun getRandomWord(): String{
        return try {
            initRandomWords()
            randomWords!!.getRandom()
        } catch (e: Exception){
            context.getString(R.string.error_random)
        }
    }
    private fun initRandomWords(){
        if (randomWords == null)
            randomWords = RandomWords(context)
    }

    private fun getRandomCategory(): String{
        return try {
            initRandomCategory()
            randomCategory!!.getRandomCategory()
        } catch (e: Exception){
            context.getString(R.string.error_random)
        }
    }
    private fun initRandomCategory(){
        if (randomCategory == null)
            randomCategory = RandomCategory(context)
    }
}