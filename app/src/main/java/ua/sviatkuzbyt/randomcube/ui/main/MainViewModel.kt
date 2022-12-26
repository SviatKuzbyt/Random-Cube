package ua.sviatkuzbyt.randomcube.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import ua.sviatkuzbyt.randomcube.R
import ua.sviatkuzbyt.randomcube.data.repositories.MainRepository

class MainViewModel(application: Application): AndroidViewModel(application) {
    val textOnCube = MutableLiveData<String>()

    private var _textOnCube = ""
    private var targetItemOnNavigationBar = R.id.numbers_menu
    private val mainRepository = MainRepository(application)

    fun isSelectedTargetItemOnNavigationBar(itemId: Int): Boolean{
        return targetItemOnNavigationBar == itemId
    }
    fun setTargetItemOnNavigationBar(itemId: Int){
        targetItemOnNavigationBar = itemId
    }

}