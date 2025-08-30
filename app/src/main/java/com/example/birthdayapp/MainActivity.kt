package com.example.birthdayapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.birthdayapp.data.BirthdayRepository
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Cake
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material.icons.outlined.ChevronRight

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { BirthdayListScreen() }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BirthdayListScreen() {
    val context = LocalContext.current
    val birthdays = BirthdayRepository.getUpcoming()

    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM")

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Дни рождения") },
                actions = {
                    IconButton(
                        onClick = {
                            val intent = Intent(context, com.example.birthdayapp.activity.MonthActivity::class.java)
                            context.startActivity(intent)
                        }
                    ) {
                        Icon(Icons.Outlined.CalendarMonth, contentDescription = "Открыть список месяцев")
                    }
                }
            )
        }
    ) { padding ->
        if (birthdays.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("В ближайший месяц дней рождения нет")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(birthdays) { b ->
                    BirthdayCard(
                        name = b.name,
                        date = b.birth,
                        dateFormatter = dateFormatter
                    )
                }
            }
        }
    }
}

@Composable
private fun BirthdayCard(
    name: String,
    date: LocalDate,
    dateFormatter: DateTimeFormatter
) {
    val today = LocalDate.now()
    val next = nextBirthday(date, today)
    val daysLeft = java.time.temporal.ChronoUnit.DAYS.between(today, next).toInt()

    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Cake,
                contentDescription = null,
                modifier = Modifier.size(28.dp)
            )
            Spacer(Modifier.width(12.dp))
            Column(Modifier.weight(1f)) {
                Text(name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                Spacer(Modifier.height(2.dp))
                Text(
                    "${date.format(dateFormatter)}  •  через $daysLeft ${daysWord(daysLeft)}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null
            )
        }
    }
}

private fun nextBirthday(birth: LocalDate, from: LocalDate): LocalDate {
    val thisYear = birth.withYear(from.year)
    val target = if (!thisYear.isBefore(from)) thisYear else thisYear.plusYears(1)
    // простая обработка 29 февраля: переносим на 28 в невисокосный год
    return if (birth.monthValue == 2 && birth.dayOfMonth == 29 && !target.isLeapYear) {
        target.withDayOfMonth(28)
    } else target
}

private fun daysWord(n: Int): String {
    // русские склонения: 1 день, 2/3/4 дня, 5+ дней
    val n10 = n % 10
    val n100 = n % 100
    return when {
        n10 == 1 && n100 != 11 -> "день"
        n10 in 2..4 && (n100 < 12 || n100 > 14) -> "дня"
        else -> "дней"
    }
}
