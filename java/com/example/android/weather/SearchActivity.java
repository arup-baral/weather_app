package com.example.android.weather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {

    private SearchView searchView;
    private ProgressDialog pd;

    private static final String SHARED_PREFERENCE = "my_preference";
    private static final String LOCATION = "location";
    private static String DATA = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchView = findViewById(R.id.search_location_view);
        Button search_button = findViewById(R.id.search_button_view);
        Button save_button = findViewById(R.id.save_button_view);

        pd = new ProgressDialog(this);

        Intent intent = getIntent();
        boolean callFromMainActivity = intent.getBooleanExtra("flag", false);

        if(isLocationAvailable() && !callFromMainActivity){
            View view = getWindow().getDecorView();
            view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            searchView.setVisibility(View.GONE);
            search_button.setVisibility(View.GONE);
            save_button.setVisibility(View.GONE);
            findViewById(R.id.text_view).setVisibility(View.GONE);
            searchData(DATA);
            return;
        }

        save_button.setOnClickListener(view -> saveLocation());

        search_button.setOnClickListener(view -> searchData(searchView.getQuery().toString().trim()));
    }

    private void searchData(String location){
        if(location == null){
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            ad.setTitle("Warning ! ");
            ad.setMessage("Please enter valid location ");
            ad.setCancelable(false);

            ad.setPositiveButton("ok", (dialogInterface, i) -> {
                dialogInterface.dismiss();
                searchView.requestFocus();
            });

            return;
        }
        WeatherURL strUrl = new WeatherURL(location);
        MyViewModel viewModel = new ViewModelProvider(SearchActivity.this).get(MyViewModel.class);
        pd.setTitle("Loading...");
        pd.setMessage("Weather Data...  ");
        pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        pd.setIndeterminate(false);
        pd.setCancelable(false);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        viewModel.searchResult(strUrl.getUrl());
        viewModel.getData().observe(this, currentCondition -> {
                Intent intent = new Intent(SearchActivity.this, MainActivity.class);
                intent.putExtra("information", currentCondition);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                pd.cancel();
                super.finish();
        });
    }

    private void saveLocation(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Save the location for future use
        String location = searchView.getQuery().toString().trim();
        if(location.length()!=0){
            editor.putString(LOCATION, location);
        }
        else{
            Toast.makeText(this, "Location field is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        editor.apply();
        Toast.makeText(this, "Location is saved for future... ", Toast.LENGTH_LONG).show();
    }

    private String loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCE, MODE_PRIVATE);
        return sharedPreferences.getString(LOCATION, "");
    }

    private boolean isLocationAvailable(){
        String location = loadData();
        if(location != null && location.length() != 0){
            DATA = location;
            return true;
        }
        return false;
    }
}



























