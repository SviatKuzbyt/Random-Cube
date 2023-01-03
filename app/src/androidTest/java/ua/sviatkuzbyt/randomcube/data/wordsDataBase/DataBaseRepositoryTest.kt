package ua.sviatkuzbyt.randomcube.data.wordsDataBase

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test


internal class DataBaseRepositoryTest{
    private lateinit var appContext: Context
    @Before
    fun setup() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun checkSaveData(): Unit = runBlocking{
        launch {
            val dataBaseRepository = DataBaseRepository(appContext)
            dataBaseRepository.addWord("321")
            val list = dataBaseRepository.getWords()
            Assert.assertEquals(mutableListOf("321", "123"), list)
        }
    }

    @Test
    fun checkRemoveData(): Unit = runBlocking{
        launch {
            val dataBaseRepository = DataBaseRepository(appContext)
            dataBaseRepository.deleteWord(1)
            val list = dataBaseRepository.getWords()
            Assert.assertEquals(mutableListOf(Words(id=3, word="321")), list)
        }
    }

    @Test
    fun checkAutoSetId(): Unit = runBlocking{
        launch {
            val dataBaseRepository = DataBaseRepository(appContext)
            dataBaseRepository.addWord("First")
            println(dataBaseRepository.getWords().toString())
        }
    }

    @Test
    fun clearDB(): Unit = runBlocking {
        launch {
            val dataBaseRepository = DataBaseRepository(appContext)
            dataBaseRepository.clear()
            dataBaseRepository.addWord("ds")
            Assert.assertEquals(1, dataBaseRepository.getId())
        }
    }

}