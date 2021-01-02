package com.tafh.covid_19app.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.tafh.covid_19app.R
import com.tafh.covid_19app.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // hidden status bar
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

//        getData()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        navController = navHostFragment.findNavController()

        binding.apply {
            bottomNavigationView.setupWithNavController(navController)
        }


    }


//    private fun getData() {
//        ApiService.endpoint.getIndonesia().enqueue(object : Callback<List<IndonesiaResponse>> {
//            override fun onResponse(
//                call: Call<List<IndonesiaResponse>>,
//                response: Response<List<IndonesiaResponse>>
//            ) {
//                Log.d("MainActivity", "Response success")
//                if (response.isSuccessful) {
//                    val indonesiaResponse: List<IndonesiaResponse> = response.body()!!
//                    setResponse( indonesiaResponse )
//                }
//            }
//
//            override fun onFailure(call: Call<List<IndonesiaResponse>>, t: Throwable) {
//
//                Log.d("MainActivity", "Response failure")
//            }
//
//
//        })
//    }
//
//    private fun setResponse(indonesiaResponse: List<IndonesiaResponse>) {
//        val response = indonesiaResponse[0]
//
//        Log.d("MainActivity", "positive: ${response.positif}, recover: ${response.sembuh}, death: ${response.meninggal}")
//
//    }
}