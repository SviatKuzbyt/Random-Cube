package ua.sviatkuzbyt.randomcube.ui.main

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import ua.sviatkuzbyt.randomcube.R
import ua.sviatkuzbyt.randomcube.ui.categories.CategoriesFragment
import ua.sviatkuzbyt.randomcube.ui.hideKeyboardFrom
import ua.sviatkuzbyt.randomcube.ui.main.elements.CubeAnimation
import ua.sviatkuzbyt.randomcube.ui.numbers.NumbersFragment
import ua.sviatkuzbyt.randomcube.ui.words.WordsFragment

val Context.intRangeDataStore by preferencesDataStore(name = "intRangeDataStore")

class MainActivity : AppCompatActivity() {
    lateinit var settingsContainer: CardView
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var viewModel: MainViewModel
    lateinit var mainLayout: ConstraintLayout
    lateinit var cube: CardView
    lateinit var textOnCube: TextView
    private lateinit var cubeAnimation: CubeAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainLayout = findViewById(R.id.mainLayout)

        settingsContainer = findViewById(R.id.settings_container)
        settingsContainer.setBackgroundResource(R.drawable.background_setting_container)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener {
            val targetFragment = getTargetId(it.itemId)
            if(!viewModel.isSelectedTargetItemOnNavigationBar(it.itemId)){
                viewModel.setTargetItemOnNavigationBar(it.itemId)
                replaceFragment(targetFragment)
            }
            return@setOnItemSelectedListener true
        }


        cube = findViewById(R.id.cube)
        textOnCube = findViewById(R.id.textOnCube)

        viewModel.textOnCube.observe(this){
            textOnCube.text = it
        }

        cubeAnimation = CubeAnimation(cube, textOnCube)
        cube.setOnClickListener {
            saveChange()
            getRandom()
            cubeAnimation.play()
        }
    }

    private fun getTargetId(itemId: Int) = when(itemId){
        R.id.numbers_menu -> NumbersFragment()
        R.id.words_menu -> WordsFragment()
        else -> CategoriesFragment()
    }

    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainerSetting, fragment)
        transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
        transaction.commit()
    }

    private fun getRandom(){
        try {
            viewModel.getRandomValue()
        } catch (e: Exception){
            Toast.makeText(this, getString(R.string.error_random), Toast.LENGTH_SHORT).show()
        }
    }

    private fun saveChange(){
        if(mainLayout.hasFocus()){
            mainLayout.clearFocus()
            hideKeyboardFrom(this, mainLayout)
        }
    }
}