package ua.sviatkuzbyt.randomcube.ui.words.elements

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ua.sviatkuzbyt.randomcube.R
import ua.sviatkuzbyt.randomcube.data.wordsDataBase.Words
import ua.sviatkuzbyt.randomcube.ui.words.WordsViewModel

class WordsAdapter(private val dataSet: MutableList<Words>, private val viewModel: WordsViewModel) :
    RecyclerView.Adapter<WordsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textWord: TextView
        val deleteElement: View

        init {
            textWord = view.findViewById(R.id.textWord)
            deleteElement = view.findViewById(R.id.deleteElement)
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.words_recycle_view, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.textWord.text = dataSet[position].word
        viewHolder.deleteElement.setOnClickListener {
            viewModel.deleteWord(position)
            Log.v("removedPositions", "position in adapter - $position")
        }
    }

    override fun getItemCount() = dataSet.size
}
