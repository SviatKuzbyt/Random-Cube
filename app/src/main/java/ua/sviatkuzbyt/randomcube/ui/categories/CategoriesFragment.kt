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
import ua.sviatkuzbyt.randomcube.ui.categories.elements.CategoriesAdapter

class CategoriesFragment : Fragment() {

    lateinit var viewModel: CategoriesViewModel
    lateinit var categoryRecycle: RecyclerView
    lateinit var categoriesAdapter: CategoriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
        return inflater.inflate(R.layout.fragment_categories, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        categoryRecycle = view.findViewById(R.id.categoryRecycle)
        categoryRecycle.layoutManager = LinearLayoutManager(activity)

        viewModel.categoryList.observe(viewLifecycleOwner){
            categoriesAdapter = CategoriesAdapter(it, requireActivity(), viewModel)
            categoryRecycle.adapter = categoriesAdapter
        }
    }
}