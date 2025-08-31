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
import com.example.birthdayapp.model.Person
import com.example.birthdayapp.ui.components.BirthdayCard

@Composable
fun BirthdayScreen(navController: NavController) {
    val people: List<Person> = BirthdayRepository.getUpcomingBirthdays()

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Button(
            onClick = { navController.navigate("month_list") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Выбрать месяц")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(people) { person ->
                BirthdayCard(person)
            }
        }
    }
}