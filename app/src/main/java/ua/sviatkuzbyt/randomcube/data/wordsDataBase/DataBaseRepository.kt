package ua.sviatkuzbyt.randomcube.data.wordsDataBase

import android.content.Context
import android.util.Log
import androidx.room.Room

class DataBaseRepository(context: Context) {
    private val dataBase = Room.databaseBuilder(context, DataBase::class.java, "Words").build()
    private val dao = dataBase.dao()

    fun addWord(word: String){
        val wordEntity = Words(0, word)
        dao.addWord(wordEntity)
    }

    fun deleteWord(id: Int){
        dao.deleteWord(id)
    }

    fun getWords() = dao.getWords()
    fun getId() = dao.getLastId()

    fun clear(){
        dao.clear()
    }

    fun getWordsStringList() = dao.getWordsString()
}