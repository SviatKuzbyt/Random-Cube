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

@RunWith(AndroidJUnit4::class)
internal class IntRangeDataStoreTest{
    private lateinit var appContext: Context
    @Before
    fun setup() {
        appContext = InstrumentationRegistry.getInstrumentation().targetContext
    }

    @Test
    fun checkStartIntFromDataStore(): Unit = runBlocking {
        launch {
            val intRangeDataStore = IntRangeDataStore(appContext)
            intRangeDataStore.setStartNumber(12)
            Assert.assertEquals(12, intRangeDataStore.getStartNumber())
        }
    }

    @Test
    fun checkEndIntFromDataStore(): Unit = runBlocking {
        launch {
            val intRangeDataStore = IntRangeDataStore(appContext)
            intRangeDataStore.setEndNumber(120)
            Assert.assertEquals(120, intRangeDataStore.getEndNumber())
        }
    }
}