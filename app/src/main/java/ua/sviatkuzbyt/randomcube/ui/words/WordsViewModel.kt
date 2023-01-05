package ua.sviatkuzbyt.randomcube.ui.words

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ua.sviatkuzbyt.randomcube.data.wordsDataBase.DataBaseRepository
import ua.sviatkuzbyt.randomcube.data.wordsDataBase.Words

class WordsViewModel(application: Application): AndroidViewModel(application) {
    val listWords = MutableLiveData<MutableList<Words>>()
    private val _listWords = mutableListOf<Words>()
    private val dataBaseRepository = DataBaseRepository(application)
    private var modeChangeList = 0
    private var positionRemove = 0
    private var oldListSize = 0

    init {
        viewModelScope.launch(Dispatchers.IO){
            val loadList = dataBaseRepository.getWords()
            _listWords.addAll(loadList)
            postList(0)
        }
    }

    private fun postList(mode: Int){
        modeChangeList = mode
        listWords.postValue(_listWords)
    }

    fun addWord(word: String){
        viewModelScope.launch(Dispatchers.IO){
            dataBaseRepository.addWord(word)
            addItemToList(word)
            postList(1)
        }
    }
    private fun addItemToList(word: String){
        _listWords.add(0, Words(getLastIdFromDB(), word))
    }
    private fun getLastIdFromDB(): Int{
        return dataBaseRepository.getId()
    }

    fun deleteWord(position: Int){
        positionRemove = position
        removeItemFromDB(_listWords[position].id)
        removeItemFromList(position)
        postList(2)
    }
    private fun removeItemFromDB(id: Int) =
        viewModelScope.launch(Dispatchers.IO){
        dataBaseRepository.deleteWord(id)
    }
    private fun removeItemFromList(position: Int){
        _listWords.removeAt(position)
    }

    fun clearAllData(){
        clearDataInDB()
        setOldListSize()
        _listWords.clear()
        postList(3)
    }
    private fun clearDataInDB() =
        viewModelScope.launch(Dispatchers.IO){
        dataBaseRepository.clear()
    }
    private fun setOldListSize(){
        oldListSize = _listWords.size
    }

    fun getModeChangeList() = modeChangeList

    fun getRemovedPosition() = positionRemove

    fun getOldListSize() = oldListSize

    fun clearChangeMode(){
        modeChangeList = 0
    }
}