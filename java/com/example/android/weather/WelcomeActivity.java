package com.example.android.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class WelcomeActivity extends AppCompatActivity {

    private ImageView img;
    private TextView txt;
    private Button btn;

    private ConnectivityService connectivityService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        /* removing StatusBar */
        View view = getWindow().getDecorView();
        view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        connectivityService = new ConnectivityService();

        img = findViewById(R.id.welcome_img_view);
        txt = findViewById(R.id.welcome_text_view);
        btn = findViewById(R.id.try_again_button);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                utilityFunction();
            }
        }, 2500);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                utilityFunction();
            }
        });
    }

    private void utilityFunction() {
        // Checking internet connectivity
        if (!connectivityService.connectivityService(this)) {
            img.setImageResource(R.drawable.no_connection);
            txt.setText(R.string.no_internet_connection);
            txt.setTextSize(16);
            btn.setVisibility(View.VISIBLE);
        } else {
            img.setVisibility(View.GONE);
            txt.setVisibility(View.GONE);
            btn.setVisibility(View.GONE);
            Intent intent = new Intent(WelcomeActivity.this, SearchActivity.class);
            startActivity(intent);
            finish();
        }
    }
}