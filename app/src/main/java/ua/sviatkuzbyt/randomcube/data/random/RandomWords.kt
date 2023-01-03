package ua.sviatkuzbyt.randomcube.data.random

import android.content.Context
import ua.sviatkuzbyt.randomcube.data.wordsDataBase.DataBaseRepository
import kotlin.random.Random

class RandomWords(context: Context) {
    private val dataBaseRepository = DataBaseRepository(context)
    var listWords = emptyArray<String>()

    fun loadWords(){
        listWords = dataBaseRepository.getWordsStringList()
    }

    fun getRandom(): String{
        val randomId = Random.nextInt(0, listWords.size)
        return listWords[randomId]
    }
}