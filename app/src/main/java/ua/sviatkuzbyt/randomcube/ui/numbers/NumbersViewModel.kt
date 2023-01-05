package ua.sviatkuzbyt.randomcube.ui.numbers

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.sviatkuzbyt.randomcube.data.dataStore.IntRangeDataStore

class NumbersViewModel(application: Application): AndroidViewModel(application) {
    val startNumber = MutableLiveData<Int>()
    val endNumber = MutableLiveData<Int>()
    private val intRangeDataStore = IntRangeDataStore(application)

    init {
        viewModelScope.launch(Dispatchers.IO){
            getStartNumber()
            getEndNumber()
        }
    }

    private suspend fun getStartNumber(){
        startNumber.postValue(intRangeDataStore.getStartNumber())
    }
    private suspend fun getEndNumber(){
        endNumber.postValue(intRangeDataStore.getEndNumber())
    }

    fun setStartNumber(number: Int) {
        startNumber.value = number
        viewModelScope.launch(Dispatchers.IO){
            intRangeDataStore.setStartNumber(number)
        }
    }

    fun setEndNumber(number: Int){
        endNumber.value = number
        viewModelScope.launch(Dispatchers.IO) {
            intRangeDataStore.setEndNumber(number)
        }
    }
}