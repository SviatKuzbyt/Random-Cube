package ua.sviatkuzbyt.randomcube.data.random

import android.content.Context
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
internal class RandomNumberTest{
    private lateinit var appContext: Context
    @Before
    fun setup() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun checkWork(): Unit = runBlocking{
        launch {
            val randomNumber = RandomNumber(appContext)
            randomNumber.getNumbers()
            println("Рандомне чис: ${randomNumber.getRandom()}")
        }
    }
}