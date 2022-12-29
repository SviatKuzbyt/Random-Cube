package ua.sviatkuzbyt.randomcube.data.random

import android.content.Context
import kotlinx.coroutines.delay
import ua.sviatkuzbyt.randomcube.data.dataStore.IntRangeDataStore
import kotlin.random.Random

class RandomNumber(context: Context) {
    private val intRangeDataStore = IntRangeDataStore(context)
    private var startNumber = 0
    private var endNumber = 0

    suspend fun getNumbers(){
        delay(100)
        startNumber = intRangeDataStore.getStartNumber()
        endNumber = intRangeDataStore.getEndNumber()
    }

    fun getRandom() = Random.nextInt(startNumber, endNumber + 1)
}