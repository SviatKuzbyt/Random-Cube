package ua.sviatkuzbyt.randomcube.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.sviatkuzbyt.randomcube.R
import ua.sviatkuzbyt.randomcube.data.repositories.MainRepository

class MainViewModel(application: Application): AndroidViewModel(application) {
    val textOnCube = MutableLiveData<String>()
    private val mainRepository = MainRepository(application)

    fun getRandomValue(id: Int) = viewModelScope.launch(Dispatchers.IO){
        textOnCube.postValue(
            mainRepository.getRandomValue(id)
        )
    }

}