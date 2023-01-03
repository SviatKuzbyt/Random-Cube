package ua.sviatkuzbyt.randomcube.ui.words

import android.os.Bundle
import android.util.Log
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this)[WordsViewModel::class.java]
        return inflater.inflate(R.layout.fragment_words, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editTextAdd = view.findViewById(R.id.editTextAdd)
        textClear = view.findViewById(R.id.textClear)

        recycleWords = view.findViewById(R.id.recycleWords)
        recycleWords.layoutManager = LinearLayoutManager(activity)

        viewModel.listWords.observe(viewLifecycleOwner){
            when(viewModel.getModeChangeList()){
                1 -> {
                    wordsAdapter.notifyItemInserted(0)
                    wordsAdapter.notifyItemRangeChanged(0, it.size)
                }
                2 -> {
                    val removedPositions = viewModel.getRemovedPosition()
                    wordsAdapter.notifyItemRemoved(removedPositions)
                    wordsAdapter.notifyItemRangeChanged(removedPositions, it.size)
                }
                3 ->{
                    val oldListSize = viewModel.getOldListSize()
                    wordsAdapter.notifyItemRangeRemoved(0, oldListSize)
                    wordsAdapter.notifyItemRangeChanged(0, oldListSize)
                }
                else ->{
                    wordsAdapter = WordsAdapter(it, viewModel)
                    recycleWords.adapter = wordsAdapter
                }
            }
            Log.v("clearList", wordsAdapter.itemCount.toString())
            viewModel.clearChangeMode()
            replaceTextClear(it)
        }

        editTextAdd.setOnEditorActionListener { _, _, _ ->
            viewModel.addWord(editTextAdd.text.toString())
            editTextAdd.setText("")
            true
        }

        val clearAlertDialog = ClearAlertDialog(requireActivity(), viewModel)
        textClear.setOnClickListener {
            clearAlertDialog.showDialog()
        }
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