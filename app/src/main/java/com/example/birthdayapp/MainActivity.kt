package com.example.birthdayapp

import android.os.Bundle;

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.birthdayapp.ui.screens.BirthdayScreen
import com.example.birthdayapp.ui.screens.BirthdaysInMonthScreen
import com.example.birthdayapp.ui.screens.MonthListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "birthday_list") {
                    composable("birthday_list") {
                        BirthdayScreen(navController)
                    }
                    composable("month_list") {
                        MonthListScreen(navController)
                    }
                    composable("birthdays_in_month/{month}") { backStackEntry ->
                        val month = backStackEntry.arguments?.getString("month")?.toIntOrNull()
                        if (month != null) {
                            BirthdaysInMonthScreen(navController, month)
                        }
                    }
                }
            }
        }
    }
}