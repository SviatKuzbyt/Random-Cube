package ua.sviatkuzbyt.randomcube.data.wordsDataBase

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface WordsDao{
    @Insert
    fun addWord(word: Words)

    @Query("DELETE FROM words WHERE id IN (:id)")
    fun deleteWord(id: Int)

    @Query("SELECT * FROM words ORDER BY id DESC")
    fun getWords(): MutableList<Words>

    @Query("SELECT word FROM words ORDER BY id DESC")
    fun getWordsString(): Array<String>

    @Query("SELECT id FROM words ORDER BY id DESC LIMIT 1")
    fun getLastId(): Int

    @Query("DELETE FROM words")
    fun clear()
}