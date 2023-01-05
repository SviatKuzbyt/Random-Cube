package ua.sviatkuzbyt.randomcube.data.random

import android.content.Context
import kotlinx.coroutines.delay
import ua.sviatkuzbyt.randomcube.data.repositories.NumbersRepository
import kotlin.random.Random

var isChangeNumbers = true
class RandomNumber(context: Context) {
    private val numbersRepository = NumbersRepository(context)
    private var startNumber = 0
    private var endNumber = 0

    private suspend fun getNumbers(){
        delay(100)
        startNumber = numbersRepository.getStartNumber()
        endNumber = numbersRepository.getEndNumber()
    }

    suspend fun getRandom(): String{
        if(isChangeNumbers){
            getNumbers()
            isChangeNumbers = false
        }
        return Random.nextInt(startNumber, endNumber + 1).toString()
    }
}