package ua.sviatkuzbyt.randomcube.data.repositories

import android.content.Context
import ua.sviatkuzbyt.randomcube.R
import ua.sviatkuzbyt.randomcube.data.random.RandomCategory
import ua.sviatkuzbyt.randomcube.data.random.RandomNumber
import ua.sviatkuzbyt.randomcube.data.random.RandomWords

class MainRepository(private val context: Context) {
    private val randomNumber: RandomNumber = RandomNumber(context)
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
            randomNumber.getNumbers()
            randomNumber.getRandom().toString()
        } catch (e: Exception){
            context.getString(R.string.error_random)
        }
    }

    fun getRandomCategory(): String{
        initRandomCategory()
        return randomCategory!!.getRandomCategory()
    }

    private fun getRandomWord(): String{
        return try {
            initRandomNumber()
            randomWords!!.loadWords()
            randomWords!!.getRandom()
        } catch (e: Exception){
            context.getString(R.string.error_random)
        }
    }

    private fun initRandomNumber(){
        if (randomWords == null)
            randomWords = RandomWords(context)
    }

    private fun initRandomCategory(){
        if (randomCategory == null)
            randomCategory = RandomCategory(context)
    }

}