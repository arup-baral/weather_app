package com.example.android.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class HourActivity extends AppCompatActivity {

    private ListView listView;

    private HourAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hour);

        listView = findViewById(R.id.hour_condition_list_view);

        adapter = new HourAdapter(this, new ArrayList<>());

        listView.setAdapter(adapter);

        MyViewModel viewModel = new ViewModelProvider(this).get(MyViewModel.class);
        viewModel.getHourRepo().observe(this, new Observer<ArrayList<HourCondition>>() {
            @Override
            public void onChanged(ArrayList<HourCondition> hourConditions) {
                adapter.clear();
                if(hourConditions != null && !hourConditions.isEmpty()) {
                    adapter.addAll(hourConditions);
                }
            }
        });
    }
}