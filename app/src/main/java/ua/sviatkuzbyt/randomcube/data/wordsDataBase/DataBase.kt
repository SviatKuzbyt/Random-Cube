package ua.sviatkuzbyt.randomcube.data.wordsDataBase

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(version = 1, entities = [Words::class])
abstract class DataBase : RoomDatabase(){
    abstract fun dao(): WordsDao
}