package com.example.birthdayapp.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.birthdayapp.data.BirthdayRepository
import com.example.birthdayapp.ui.components.BirthdayCard


@Composable
fun BirthdaysInMonthScreen(navController: NavController, month: Int) {
    val peopleInMonth = BirthdayRepository.getAllBirthdays()
        .filter { it.getBirthDate().monthValue == month }
        .sortedBy { it.getBirthDate().dayOfMonth }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(onClick = { navController.popBackStack() }) {
            Text("Назад")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Дни рождения в ${monthToName(month)}",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(12.dp))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(peopleInMonth) { person ->
                BirthdayCard(person)
            }
        }
    }
}