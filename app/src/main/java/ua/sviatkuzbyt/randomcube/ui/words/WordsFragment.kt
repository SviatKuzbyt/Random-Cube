package ua.sviatkuzbyt.randomcube.ui.words

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ua.sviatkuzbyt.randomcube.R
import ua.sviatkuzbyt.randomcube.data.wordsDataBase.Words
import ua.sviatkuzbyt.randomcube.ui.words.elements.ClearAlertDialog
import ua.sviatkuzbyt.randomcube.ui.words.elements.WordsAdapter

class WordsFragment : Fragment() {
    lateinit var viewModel: WordsViewModel
    lateinit var editTextAdd: EditText
    lateinit var recycleWords: RecyclerView
    lateinit var wordsAdapter: WordsAdapter
    lateinit var textClear: TextView
    lateinit var clearAlertDialog: ClearAlertDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[WordsViewModel::class.java]
        return inflater.inflate(R.layout.fragment_words, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextAdd = view.findViewById(R.id.editTextAdd)
        editTextAdd.setOnEditorActionListener { _, _, _ ->
            viewModel.addWord(getAddEditText())
            clearAddEditText()
            true
        }

        clearAlertDialog = ClearAlertDialog(requireActivity(), viewModel)
        textClear = view.findViewById(R.id.textClear)
        textClear.setOnClickListener {
            clearAlertDialog.showDialog()
        }

        recycleWords = view.findViewById(R.id.recycleWords)

        viewModel.listWords.observe(viewLifecycleOwner){
            when(viewModel.getModeChangeList()){
                1 -> {
                    addListItem(it.size)
                }
                2 -> {
                    val removedPositions = viewModel.getRemovedPosition()
                    removeListItem(removedPositions, it.size)
                }
                3 ->{
                    val oldListSize = viewModel.getOldListSize()
                    removeAllListItems(oldListSize)
                }
                else ->{
                    setRecycleAdapter(it)
                }
            }
            viewModel.clearChangeMode()
            replaceTextClear(it)
        }
    }

    private fun getAddEditText() = editTextAdd.text.toString()
    private fun clearAddEditText(){
        editTextAdd.setText("")
    }

    private fun addListItem(size: Int){
        wordsAdapter.notifyItemInserted(0)
        wordsAdapter.notifyItemRangeChanged(0, size)
    }

    private fun removeListItem(removedPositions: Int, size: Int){
        wordsAdapter.notifyItemRemoved(removedPositions)
        wordsAdapter.notifyItemRangeChanged(removedPositions, size)
    }

    private fun removeAllListItems(oldListSize: Int){
        wordsAdapter.notifyItemRangeRemoved(0, oldListSize)
        wordsAdapter.notifyItemRangeChanged(0, oldListSize)
    }

    private fun setRecycleAdapter(list: MutableList<Words>){
        recycleWords.layoutManager = LinearLayoutManager(activity)
        wordsAdapter = WordsAdapter(list, viewModel)
        recycleWords.adapter = wordsAdapter
    }

    private fun replaceTextClear(list: MutableList<Words>){
        if (list.isEmpty())
            hideTextClear()
        else if(!textClear.isVisible)
            showTextClear()
    }
    private fun showTextClear(){
        textClear.visibility = View.VISIBLE
    }
    private fun hideTextClear(){
        textClear.visibility = View.GONE
    }
}