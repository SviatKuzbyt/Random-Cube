package ua.sviatkuzbyt.randomcube.ui.categories

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import ua.sviatkuzbyt.randomcube.R
import ua.sviatkuzbyt.randomcube.data.repositories.CategoryData

class CategoriesAdapter(
    private val dataSet: Array<CategoryData>,
    private val context: Context,
    private val viewModel: CategoriesViewModel) :
    RecyclerView.Adapter<CategoriesAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageCategory: View
        val textCategory: TextView
        init {
            imageCategory = view.findViewById(R.id.imageCategory)
            textCategory = view.findViewById(R.id.textCategory)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.category_recycle_view, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val tittle = context.getString(dataSet[position].label)
        viewHolder.textCategory.text = tittle
        val image = ResourcesCompat.getDrawable(context.resources, dataSet[position].image, null)
        viewHolder.imageCategory.background = image

        if (dataSet[position].isSelected)
            setBlueBackground(viewHolder.itemView)
        else setTransparentBackground(viewHolder.itemView)

        viewHolder.itemView.setOnClickListener {
            viewModel.updateSelectedItem(position)
        }
    }

    private fun setBlueBackground(itemView: View){
        itemView.background = ContextCompat.getDrawable(
                context, R.drawable.background_selected_item_category)
    }
    private fun setTransparentBackground(itemView: View){
        itemView.setBackgroundColor(
            ContextCompat.getColor(context, R.color.transparent))
    }

    override fun getItemCount() = dataSet.size
}