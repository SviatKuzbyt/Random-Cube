package ua.sviatkuzbyt.randomcube.data.repositories

import android.content.Context
import androidx.room.Room
import ua.sviatkuzbyt.randomcube.data.random.isChangeWords
import ua.sviatkuzbyt.randomcube.data.wordsDataBase.DataBase
import ua.sviatkuzbyt.randomcube.data.wordsDataBase.Words

class WordsRepository(context: Context) {
    private val dataBase = Room.databaseBuilder(context, DataBase::class.java, "Words").build()
    private val dao = dataBase.dao()

    fun addWord(word: String){
        val wordEntity = Words(0, word)
        dao.addWord(wordEntity)
        setChangeChangeWords()
    }

    fun deleteWord(id: Int){
        dao.deleteWord(id)
        setChangeChangeWords()
    }

    fun getWords() = dao.getWords()
    fun getId() = dao.getLastId()

    fun clear(){
        dao.clear()
        setChangeChangeWords()
    }

    fun getWordsStringList() = dao.getWordsString()

    private fun setChangeChangeWords(){
        if (!isChangeWords)
            isChangeWords = true
    }
}