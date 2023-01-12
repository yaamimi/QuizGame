package com.example.quizapplication.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import com.example.quizapplication.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        NavController.OnDestinationChangedListener { controller, destination, arguments ->
            title = when (destination.id) {
                R.id.welcomeFragment -> getString(R.string.welcome_title)
                R.id.chooseThemeFragment -> getString(R.string.theme_title)
                R.id.quizFragment -> getString(R.string.quiz_title)
                R.id.scoreFragment -> getString(R.string.score_title)
                else -> getString(R.string.app_name)
            }
        }
    }



    override fun onBackPressed() {
    }
}