package ua.sviatkuzbyt.randomcube.data.wordsDataBase

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import ua.sviatkuzbyt.randomcube.data.repositories.WordsRepository


internal class WordsRepositoryTest{
    private lateinit var appContext: Context
    @Before
    fun setup() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun checkSaveData(): Unit = runBlocking{
        launch {
            val wordsRepository = WordsRepository(appContext)
            wordsRepository.addWord("321")
            val list = wordsRepository.getWords()
            Assert.assertEquals(mutableListOf("321", "123"), list)
        }
    }

    @Test
    fun checkRemoveData(): Unit = runBlocking{
        launch {
            val wordsRepository = WordsRepository(appContext)
            wordsRepository.deleteWord(1)
            val list = wordsRepository.getWords()
            Assert.assertEquals(mutableListOf(Words(id=3, word="321")), list)
        }
    }

    @Test
    fun checkAutoSetId(): Unit = runBlocking{
        launch {
            val wordsRepository = WordsRepository(appContext)
            wordsRepository.addWord("First")
            println(wordsRepository.getWords().toString())
        }
    }

    @Test
    fun clearDB(): Unit = runBlocking {
        launch {
            val wordsRepository = WordsRepository(appContext)
            wordsRepository.clear()
            wordsRepository.addWord("ds")
            Assert.assertEquals(1, wordsRepository.getId())
        }
    }

}