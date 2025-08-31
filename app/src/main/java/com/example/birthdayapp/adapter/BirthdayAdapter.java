//package com.example.birthdayapp.adapter;
//
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.birthdayapp.R;
//import com.example.birthdayapp.model.Person;
//
//import java.util.List;
//
//public class BirthdayAdapter extends RecyclerView.Adapter<BirthdayAdapter.BirthdayViewHolder> {
//
//    private final List<Person> people;
//
//    public BirthdayAdapter(List<Person> people) {
//        this.people = people;
//    }
//
//    @NonNull
//    @Override
//    public BirthdayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.item_birhthday, parent, false);
//        return new BirthdayViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull BirthdayViewHolder holder, int position) {
//        Person person = people.get(position);
//        holder.name.setText(person.getName());
//        holder.date.setText("День рождения: " + person.getBirthDate());
//        holder.age.setText("Исполняется: " + person.getUpcomingAge() + " лет");
//    }
//
//    @Override
//    public int getItemCount() {
//        return people.size();
//    }
//
//    static class BirthdayViewHolder extends RecyclerView.ViewHolder {
//        TextView name, date, age;
//
//        public BirthdayViewHolder(@NonNull View itemView) {
//            super(itemView);
//            name = itemView.findViewById(R.id.personName);
//            date = itemView.findViewById(R.id.personDate);
//            age = itemView.findViewById(R.id.personAge);
//        }
//    }
//}