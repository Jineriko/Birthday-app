package com.example.birthdayapp.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.birthdayapp.data.BirthdayRepository
import com.example.birthdayapp.model.Person

@Composable
fun BirthdayScreen() {
    val people: List<Person> = BirthdayRepository.getUpcomingBirthdays()

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(people) { person ->
            BirthdayCard(person)
        }
    }
}

@Composable
fun BirthdayCard(person: Person) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = person.getName(), style = MaterialTheme.typography.titleMedium)
            Text(text = "Дата: ${person.getDateString()}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Исполняется: ${person.getUpcomingAge()} лет", style = MaterialTheme.typography.bodySmall)
        }
    }
}