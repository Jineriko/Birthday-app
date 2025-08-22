package com.example.birthdayapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.birthdayapp.repository.BirthdayRepository
import android.content.Intent
import androidx.compose.material3.Button
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BirthdayListScreen()
        }
    }

}

@Composable
fun BirthdayListScreen() {
    val birthdays = BirthdayRepository.getUpcoming()
    val context = LocalContext.current

    Column {
        // выводим список карточками
        for (b in birthdays) {
            androidx.compose.material3.Card(
                modifier = androidx.compose.ui.Modifier
                    .padding(8.dp),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            ) {
                Column(modifier = androidx.compose.ui.Modifier.padding(16.dp)) {
                    Text(
                        text = b.name,
                        style = androidx.compose.material3.MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = "${b.birth.dayOfMonth}.${b.birth.monthValue}",
                        style = androidx.compose.material3.MaterialTheme.typography.bodyMedium
                    )
                }
            }
        }

        // кнопка перехода в CalendarActivity (Java)
        Button(onClick = {
            val intent = Intent(context, com.example.birthdayapp.activity.CalendarActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Открыть календарь")
        }
    }
}