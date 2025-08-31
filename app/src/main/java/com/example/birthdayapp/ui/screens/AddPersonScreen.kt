package com.example.birthdayapp.ui.screens

import androidx.compose.runtime.Composable

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.birthdayapp.data.BirthdayRepository
import com.example.birthdayapp.model.Person
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun AddPersonScreen(navController: NavController) {
    var name by remember { mutableStateOf("") }
    var birthDateText by remember { mutableStateOf("") }
    var error by remember { mutableStateOf<String?>(null) }

    val dateFormat = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        Button(onClick = { navController.popBackStack() }) {
            Text("Назад")
        }

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Имя") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = birthDateText,
            onValueChange = { birthDateText = it },
            label = { Text("Дата рождения (дд.мм.гггг)") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        error?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
            Spacer(modifier = Modifier.height(8.dp))
        }

        Button(
            onClick = {
                try {
                    val birthDate = LocalDate.parse(birthDateText, dateFormat)
                    if (name.isBlank()) {
                        error = "Имя не может быть пустым"
                        return@Button
                    }
                    BirthdayRepository.addPerson(Person(name.trim(), birthDate))
                    navController.popBackStack()
                } catch (e: Exception) {
                    error = "Некорректный формат даты"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Сохранить")
        }
    }
}