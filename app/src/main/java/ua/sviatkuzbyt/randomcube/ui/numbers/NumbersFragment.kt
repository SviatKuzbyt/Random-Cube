package ua.sviatkuzbyt.randomcube.ui.numbers

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import ua.sviatkuzbyt.randomcube.R
import ua.sviatkuzbyt.randomcube.ui.hideKeyboardFrom

class NumbersFragment : Fragment() {
    private lateinit var editTextStartRange: EditText
    private lateinit var editTextEndRange: EditText
    private lateinit var viewModel: NumbersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
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
        try {
            viewModel.startNumber.observe(requireActivity()){
                editTextStartRange.setText(it.toString())
            }
        } catch (e: Exception){
            makeToastError(getString(R.string.error_read_num))
        }
    }

    private fun observeEndNumber(){
        try {
            viewModel.endNumber.observe(requireActivity()){
                editTextEndRange.setText(it.toString())
            }
        } catch (e: Exception){
            makeToastError(getString(R.string.error_read_num))
        }
    }

    private fun makeToastError(message: String){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }

    private fun setStartNumberWhenHasFocus(){
        try {
            viewModel.setStartNumber(editTextStartRange.text.toString().toInt())
        }
        catch (e: Exception){
            makeToastError(getString(R.string.error_write_num))
        }
    }

    private fun setEndNumberWhenHasFocus(){
        try {
            viewModel.setEndNumber(editTextEndRange.text.toString().toInt())
            hideKeyboardFrom(requireActivity(), editTextEndRange)
        } catch (e: Exception){
            makeToastError(getString(R.string.error_write_num))
        }
    }
}