package com.example.android.weather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_DATA = "information";

    private TextView curr_temp;
    private TextView max_temp;
    private TextView min_temp;
    private TextView cond;
    private TextView week_day, time;
    private TextView app_temp, vis, air_p, uv, humidity, wv, wd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        curr_temp = findViewById(R.id.current_temp_text_view);
        max_temp = findViewById(R.id.max_temp_text_view);
        min_temp = findViewById(R.id.min_temp_text_view);
        cond = findViewById(R.id.current_condition_text_view);
        week_day = findViewById(R.id.weekday_text_view);
        time = findViewById(R.id.current_time_text_view);
        app_temp = findViewById(R.id.aprn_temp_text_view);
        vis = findViewById(R.id.visibility_text_view);
        air_p = findViewById(R.id.air_press_text_view);
        uv = findViewById(R.id.uv_text_view);
        humidity = findViewById(R.id.humidity_text_view);
        wv = findViewById(R.id.wind_text_view);
        wd = findViewById(R.id.wind_dir_text_view);

        LinearLayout mainView = findViewById(R.id.main_background);
        AnimationDrawable animationDrawable = (AnimationDrawable) mainView.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();
        animationDrawable.setOneShot(false);

        Intent intent = getIntent();
        CurrentCondition currentCondition = (CurrentCondition) intent.getSerializableExtra(EXTRA_DATA);
        setText(currentCondition);

        Button hour_cond = findViewById(R.id.hour_condition_link);
        hour_cond.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, HourActivity.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.location_setting) {
            Intent intent = new Intent(MainActivity.this, SearchActivity.class);
            intent.putExtra("flag", true);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            super.finish();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.finish();
    }

    private void setText(CurrentCondition data){
        curr_temp.setText(String.valueOf(data.getCurrent_temp()));
        max_temp.setText(String.valueOf(data.getMax_temp()));
        min_temp.setText(String.valueOf(data.getMin_temp()));
        cond.setText(data.getCondition());
        week_day.setText(data.getWeek_day());
        time.setText(getTime().trim());
        app_temp.setText(String.valueOf(data.getAppTemp()));
        vis.setText(String.valueOf(data.getVisibility()));
        air_p.setText(String.valueOf(data.getAirPress()));
        uv.setText(String.valueOf(data.getUV()));
        humidity.setText(String.valueOf(data.getHumidity()));
        wv.setText(String.valueOf(data.getWindVel()));
        wd.setText(data.getWindDir());
    }

    private String getTime(){
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(calendar.getTime());
    }
}






































