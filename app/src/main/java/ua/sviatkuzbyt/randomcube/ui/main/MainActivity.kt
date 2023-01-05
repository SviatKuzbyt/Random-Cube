package ua.sviatkuzbyt.randomcube.ui.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ua.sviatkuzbyt.randomcube.R
import ua.sviatkuzbyt.randomcube.ui.hideKeyboardFrom
import ua.sviatkuzbyt.randomcube.ui.makeToastError

val Context.intRangeDataStore by preferencesDataStore(name = "intRangeDataStore")

class MainActivity() : AppCompatActivity() {
    lateinit var viewModel: MainViewModel
    lateinit var mainLayout: ConstraintLayout
    lateinit var settingsContainer: CardView
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var cube: CardView
    lateinit var textOnCube: TextView
    private lateinit var navController: NavController
    private lateinit var cubeAnimation: CubeAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainLayout = findViewById(R.id.mainLayout)

        settingsContainer = findViewById(R.id.settings_container)
        settingsContainer.setBackgroundResource(R.drawable.background_setting_container)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        navController = findNavController(R.id.fragmentContainerSettings)
        bottomNavigationView.setupWithNavController(navController)

        cube = findViewById(R.id.cube)
        textOnCube = findViewById(R.id.textOnCube)
        cubeAnimation = CubeAnimation(cube, textOnCube)

        cube.setOnClickListener {
            hasFocus()
            getRandom()
            cubeAnimation.play()
        }
        viewModel.textOnCube.observe(this){
            textOnCube.text = it
        }
    }

    private fun getRandom(){
        try {
            viewModel.getRandomValue(getCurrentFragment())
        } catch (e: Exception){
            makeToastError(getString(R.string.error_random), this)
        }
    }

    private fun hasFocus(){
        if(mainLayout.hasFocus()){
            mainLayout.clearFocus()
            hideKeyboardFrom(this, mainLayout)
        }
    }
    private fun getCurrentFragment() = navController.currentDestination!!.id
}