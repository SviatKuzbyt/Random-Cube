package ua.sviatkuzbyt.randomcube.ui.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.cardview.widget.CardView
import androidx.datastore.preferences.preferencesDataStore
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView
import ua.sviatkuzbyt.randomcube.R
import ua.sviatkuzbyt.randomcube.ui.categories.CategoriesFragment
import ua.sviatkuzbyt.randomcube.ui.numbers.NumbersFragment
import ua.sviatkuzbyt.randomcube.ui.words.WordsFragment

val Context.intRangeDataStore by preferencesDataStore(name = "intRangeDataStore")

class MainActivity : AppCompatActivity() {
    lateinit var settingsContainer: CardView
    lateinit var bottomNavigationView: BottomNavigationView
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.v("contextInMain", this.toString())

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        settingsContainer = findViewById(R.id.settings_container)
        settingsContainer.setBackgroundResource(R.drawable.background_setting_container)

        bottomNavigationView = findViewById(R.id.bottomNavigationView)
        bottomNavigationView.setOnItemSelectedListener {
            val targetFragment = getTargetId(it.itemId)
            if(viewModel.isSelectedTargetItemOnNavigationBar(it.itemId)){
                replaceFragment(targetFragment)
                viewModel.setTargetItemOnNavigationBar(it.itemId)
            }
            return@setOnItemSelectedListener true
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
}