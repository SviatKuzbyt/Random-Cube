package ua.sviatkuzbyt.randomcube.ui.categories

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.sviatkuzbyt.randomcube.R
import ua.sviatkuzbyt.randomcube.data.random.selectedCategory
import ua.sviatkuzbyt.randomcube.data.repositories.CategoryRepositories

class CategoriesViewModel: ViewModel() {

    private val _categoryList = arrayOf(
        CategoryRepositories.CategoryData(R.drawable.yes_no_ic, R.string.yes_no, true),
        CategoryRepositories.CategoryData(R.drawable.cube_red_ic, R.string.cube, false),
        CategoryRepositories.CategoryData(R.drawable.ball_ic, R.string.ball, false)
    )
    val categoryList = MutableLiveData(_categoryList)
    private var selectedItem = 0

    fun updateSelectedItem(id: Int){
        _categoryList[selectedItem].isSelected = false
        _categoryList[id].isSelected = true
        selectedItem = id
        categoryList.value = _categoryList
        selectedCategory = id
    }
}