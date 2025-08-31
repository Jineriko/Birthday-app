package com.example.birthdayapp.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.birthdayapp.model.Person

@Composable
fun BirthdayCard(person: Person) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Имя: ${person.name}", style = MaterialTheme.typography.titleMedium)
            Text(text = "Дата рождения: ${person.birthDate}")
            Text(text = "Возраст: ${person.age} лет")
        }
    }
}