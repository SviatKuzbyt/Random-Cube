package ua.sviatkuzbyt.randomcube.data.wordsDataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Words(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val word: String
)