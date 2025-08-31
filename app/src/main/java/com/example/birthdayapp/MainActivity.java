//package com.example.birthdayapp;
//
//import android.os.Bundle;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.birthdayapp.data.BirthdayRepository;
//import com.example.birthdayapp.model.Person;
//import com.example.birthdayapp.adapter.BirthdayAdapter;
//
//
//import java.util.List;
//
//public class MainActivity extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        RecyclerView recyclerView = findViewById(R.id.recyclerViewBirthdays);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        List<Person> people = BirthdayRepository.getUpcoming();
//        BirthdayAdapter adapter = new BirthdayAdapter(people);
//        recyclerView.setAdapter(adapter);
//    }
//}
