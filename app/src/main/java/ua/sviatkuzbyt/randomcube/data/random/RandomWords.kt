package ua.sviatkuzbyt.randomcube.data.random

import android.content.Context
import ua.sviatkuzbyt.randomcube.data.repositories.WordsRepository
import kotlin.random.Random

var isChangeWords = true
class RandomWords(context: Context) {
    private val wordsRepository = WordsRepository(context)
    var listWords = emptyArray<String>()

    fun getRandom(): String{
        if (isChangeWords){
            loadWords()
            isChangeWords = false
        }
        val randomId = Random.nextInt(0, listWords.size)
        return listWords[randomId]
    }

    private fun loadWords(){
        listWords = wordsRepository.getWordsStringList()
    }
}