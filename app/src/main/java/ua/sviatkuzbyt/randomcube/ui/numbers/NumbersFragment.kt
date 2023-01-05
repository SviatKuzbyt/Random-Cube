package ua.sviatkuzbyt.randomcube.ui.numbers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import ua.sviatkuzbyt.randomcube.R
import ua.sviatkuzbyt.randomcube.ui.hideKeyboardFrom
import ua.sviatkuzbyt.randomcube.ui.makeToastError

class NumbersFragment : Fragment() {
    private lateinit var editTextStartRange: EditText
    private lateinit var editTextEndRange: EditText
    private lateinit var viewModel: NumbersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[NumbersViewModel::class.java]
        return inflater.inflate(R.layout.fragment_numbers, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextStartRange = view.findViewById(R.id.editTextStartRange)
        observeStartNumber()
        editTextStartRange.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus)
                setStartNumberWhenHasFocus()
        }

        editTextEndRange = view.findViewById(R.id.editTextEndRange)
        observeEndNumber()
        editTextEndRange.setOnFocusChangeListener { _, hasFocus ->
            if (!hasFocus)
                setEndNumberWhenHasFocus()
        }
        editTextEndRange.setOnEditorActionListener { _, _, _ ->
            editTextEndRange.clearFocus()
            true
        }
    }

    private fun observeStartNumber(){
        viewModel.startNumber.observe(requireActivity()) {
            try {
                editTextStartRange.setText(it.toString())
            } catch (e: Exception){
                makeToastError(R.string.error_read_num, requireActivity())
            }

        }
    }

    private fun observeEndNumber(){
        viewModel.endNumber.observe(requireActivity()){
            try {
                editTextEndRange.setText(it.toString())
            } catch (e: Exception){
                makeToastError(R.string.error_write_num, requireActivity())
            }

        }
    }

    private fun setStartNumberWhenHasFocus(){
        try {
            viewModel.setStartNumber(editTextStartRange.text.toString().toInt())
        }
        catch (e: Exception){
            makeToastError(R.string.error_write_num, requireActivity())
        }
    }

    private fun setEndNumberWhenHasFocus(){
        try {
            viewModel.setEndNumber(editTextEndRange.text.toString().toInt())
            hideKeyboardFrom(requireActivity(), editTextEndRange)
        } catch (e: Exception){
            makeToastError(R.string.error_write_num, requireActivity())
        }
    }
}