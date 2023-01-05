package ua.sviatkuzbyt.randomcube.data.dataStore

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import ua.sviatkuzbyt.randomcube.data.repositories.NumbersRepository

@RunWith(AndroidJUnit4::class)
internal class NumbersRepositoryTest{
    private lateinit var appContext: Context
    @Before
    fun setup() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun checkStartIntFromDataStore(): Unit = runBlocking {
        launch {
            val numbersRepository = NumbersRepository(appContext)
            numbersRepository.setStartNumber(12)
            Assert.assertEquals(12, numbersRepository.getStartNumber())
        }
    }

    @Test
    fun checkEndIntFromDataStore(): Unit = runBlocking {
        launch {
            val numbersRepository = NumbersRepository(appContext)
            numbersRepository.setEndNumber(120)
            Assert.assertEquals(120, numbersRepository.getEndNumber())
        }
    }
}