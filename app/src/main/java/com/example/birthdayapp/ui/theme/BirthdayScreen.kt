package com.example.birthdayapp.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.birthdayapp.data.BirthdayRepository
import java.time.LocalDate

@Composable
fun BirthdayScreen() {
    val allBirthdays = BirthdayRepository.getAllBirthdays()
    val today = LocalDate.now()

    val upcomingBirthdays = allBirthdays.filter { person ->
        val birthDateThisYear = person.date.withYear(today.year)

        val daysUntilBirthday = ChronoUnit.DAYS.between(today, birthDateThisYear)

        when {
            daysUntilBirthday in 0..30 -> true // в ближайшие 30 дней
            // если ДР уже прошло в этом году – проверим дату в следующем
            daysUntilBirthday < 0 -> {
                val nextYearBirthday = person.date.withYear(today.year + 1)
                val daysNextYear = ChronoUnit.DAYS.between(today, nextYearBirthday)
                daysNextYear in 0..30
            }
            else -> false
        }
    }

    LazyColumn {
        items(upcomingBirthdays) { person ->
            BirthdayCard(person)
        }
    }
}