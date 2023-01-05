package ua.sviatkuzbyt.randomcube.ui.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ua.sviatkuzbyt.randomcube.R
import ua.sviatkuzbyt.randomcube.data.repositories.CategoryData
import ua.sviatkuzbyt.randomcube.ui.makeToastError

class CategoriesFragment : Fragment() {
    private lateinit var viewModel: CategoriesViewModel
    private lateinit var categoryRecycle: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryRecycle = view.findViewById(R.id.categoryRecycle)

        viewModel.categoryList.observe(viewLifecycleOwner) {
            try {
                if (viewModel.getChangeListMode() == 1){
                    replaceSelectedItems()
                    viewModel.clearChangeListMode()
                }
                else
                    setRecycleAdapter(it)
            } catch (e: Exception){
                makeToastError(R.string.error_load_list, requireActivity())
            }

        }
    }

    private fun replaceSelectedItems(){
        categoriesAdapter.notifyItemChanged(
            viewModel.getLastSelectedItem()) //remove LastSelected
        categoriesAdapter.notifyItemChanged(
            viewModel.getTargetSelectItem()) //add TargetSelect
    }

    private fun setRecycleAdapter(list: Array<CategoryData>){
        categoryRecycle.layoutManager = LinearLayoutManager(activity)
        categoriesAdapter = CategoriesAdapter(list, requireActivity(), viewModel)
        categoryRecycle.adapter = categoriesAdapter
    }
}