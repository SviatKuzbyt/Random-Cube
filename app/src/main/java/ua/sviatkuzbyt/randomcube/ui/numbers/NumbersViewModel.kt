package ua.sviatkuzbyt.randomcube.ui.numbers

import android.app.Application
import android.util.Log
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

    fun setStartNumber(number: Int) = viewModelScope.launch(Dispatchers.IO){
        startNumber.postValue(number)
        intRangeDataStore.setStartNumber(number)

    }

     fun setEndNumber(number: Int){
        viewModelScope.launch(Dispatchers.IO) {
            endNumber.postValue(number)
            intRangeDataStore.setEndNumber(number)
        }
    }
}