package com.tafh.covid_19app.ui

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.tafh.covid_19app.R
import com.tafh.covid_19app.data.repository.CovidRepository
import com.tafh.covid_19app.databinding.ActivityMainBinding

class CovidActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    private lateinit var viewModel: CovidViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON) // hidden status bar

        viewModel = ViewModelProvider(this, CovidViewModelFactory(CovidRepository())).get(CovidViewModel::class.java)

        viewModel.getCovidIndonesia()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        setSupportActionBar(findViewById(R.id.toolbar))

        val appBarConfiguration = AppBarConfiguration.Builder(
                R.id.kasusFragment, R.id.informasiFragment, R.id.bantuanFragment
        ).build()
        setupActionBarWithNavController(navController, appBarConfiguration)

        binding.apply {
            bottomNavigationView.setupWithNavController(navController)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}