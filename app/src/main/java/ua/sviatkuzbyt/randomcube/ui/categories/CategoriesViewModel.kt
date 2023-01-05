package ua.sviatkuzbyt.randomcube.ui.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.sviatkuzbyt.randomcube.R
import ua.sviatkuzbyt.randomcube.data.random.selectedCategory
import ua.sviatkuzbyt.randomcube.data.repositories.CategoryData

class CategoriesViewModel: ViewModel() {
    private val _categoryList = arrayOf(
        CategoryData(R.drawable.yes_no_ic, R.string.yes_no, true),
        CategoryData(R.drawable.cube_red_ic, R.string.cube, false),
        CategoryData(R.drawable.ball_ic, R.string.ball, false)
    )
    val categoryList = MutableLiveData(_categoryList)
    private var lastSelectedItem = 0
    private var targetSelectItem = 0
    private var changeListMode = 0

    fun getLastSelectedItem() = lastSelectedItem
    fun getTargetSelectItem() = targetSelectItem

    fun getChangeListMode() = changeListMode
    fun clearChangeListMode(){
        changeListMode = 0
    }

    fun updateSelectedItem(id: Int){
        removeSelectedItem()
        updateLastSelectedItem()
        updateTargetSelectItem(id)
        addSelectedItem()
        postList()
        selectedCategory = id
    }

    private fun removeSelectedItem(){
        _categoryList[targetSelectItem].isSelected = false
    }
    private fun addSelectedItem(){
        _categoryList[targetSelectItem].isSelected = true
    }
    private fun updateLastSelectedItem(){
        lastSelectedItem = targetSelectItem
    }
    private fun updateTargetSelectItem(position: Int){
        targetSelectItem = position
    }

    private fun postList(){
        changeListMode = 1
        categoryList.value = _categoryList
    }
}