package ua.sviatkuzbyt.randomcube.ui

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast

fun hideKeyboardFrom(context: Context, view: View) {
    val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun makeToastError(text: String, context: Context){
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}