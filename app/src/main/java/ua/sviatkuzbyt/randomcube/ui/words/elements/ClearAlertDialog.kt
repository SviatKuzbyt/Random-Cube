package ua.sviatkuzbyt.randomcube.ui.words.elements

import android.app.AlertDialog
import android.content.Context
import androidx.core.content.res.ResourcesCompat
import ua.sviatkuzbyt.randomcube.R
import ua.sviatkuzbyt.randomcube.ui.words.WordsViewModel

class ClearAlertDialog(private val context: Context, private val viewModel: WordsViewModel) {
    private lateinit var alertDialogBuild: AlertDialog.Builder
    private lateinit var alertDialogPlay: AlertDialog

    init {
        builtAlertDialog()
    }

    private fun builtAlertDialog(){
        alertDialogBuild = AlertDialog.Builder(context)
        setContent()
        setButtons()
        createDialog()
    }
    private fun setContent(){
        alertDialogBuild.setTitle(context.getString(R.string.clear_tittle))
        alertDialogBuild.setMessage(context.getString(R.string.clear_message))
    }

    private fun setButtons(){
        alertDialogBuild.setPositiveButton(context.getString(R.string.yes)){ _, _ ->
            clearList()
        }
        alertDialogBuild.setNegativeButton(context.getString(R.string.no)){ dialog, _ ->
            dialog.cancel()
        }
    }
    private fun clearList(){
        viewModel.clearAllData()
    }

    private fun createDialog(){
        alertDialogPlay = alertDialogBuild.create()
        setBackground()
    }
    private fun setBackground(){
        val background = ResourcesCompat.getDrawable(
            context.resources, R.drawable.background_alert_dialog, context.theme
        )
        alertDialogPlay.window?.setBackgroundDrawable(background)
    }

    fun showDialog(){
        alertDialogPlay.show()
    }
}