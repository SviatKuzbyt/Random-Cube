package ua.sviatkuzbyt.randomcube.data.repositories

import android.content.Context
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

internal class MainRepositoryTest{
    private lateinit var appContext: Context
    private lateinit var mainRepository: MainRepository
    @Before
    fun setup() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
        mainRepository = MainRepository(appContext)
    }

    @Test
    fun checkGettingRandomNumber(): Unit = runBlocking{
        launch {
            val number = mainRepository.getRandomValue(1)
            println("RESULT: $number")
        }
    }

}