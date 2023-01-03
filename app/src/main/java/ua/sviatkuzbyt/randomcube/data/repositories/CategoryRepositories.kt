package ua.sviatkuzbyt.randomcube.data.repositories

import ua.sviatkuzbyt.randomcube.R

class CategoryRepositories {

    data class CategoryData(
        val image: Int,
        val label: Int,
        var isSelected: Boolean
    )

}