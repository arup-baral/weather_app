package com.example.android.weather;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HourAdapter extends ArrayAdapter<HourCondition> {

    public HourAdapter(Context context, ArrayList<HourCondition> list){
        super(context, 0, list);
    }

    TextView temp, date, time, cond, wv, wd, pressure, precipitation, humidity, rain, uv, visibility, dew, chill, cloud;
    ImageView img;

    @SuppressLint("SetTextI18n")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;

        if(listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.item_hour, parent, false);
        }

        HourCondition condition = getItem(position);

        temp = listItemView.findViewById(R.id.item_temp);
        date = listItemView.findViewById(R.id.item_date);
        time = listItemView.findViewById(R.id.item_time);
        cond = listItemView.findViewById(R.id.item_condition);
        wv = listItemView.findViewById(R.id.wind_vel);
        wd = listItemView.findViewById(R.id.wind_direction);
        pressure = listItemView.findViewById(R.id.pressure);
        humidity = listItemView.findViewById(R.id.humidity_item);
        precipitation = listItemView.findViewById(R.id.precipitation);
        rain = listItemView.findViewById(R.id.rain_chance);
        uv = listItemView.findViewById(R.id.uv);
        visibility = listItemView.findViewById(R.id.visibility);
        dew = listItemView.findViewById(R.id.dew_point);
        chill = listItemView.findViewById(R.id.windchill);
        cloud = listItemView.findViewById(R.id.cloud);
        img = listItemView.findViewById(R.id.item_img);

        temp.setText(String.valueOf(condition.getTemp()));
        date.setText(condition.getDate());
        time.setText(condition.getTime());
        cond.setText(condition.getCondition());
        wv.setText("Wind speed " + condition.getWindVel() + " kph");
        wd.setText("Wind direction " + condition.getWindDir());
        pressure.setText("Pressure " + condition.getPressure() + " mb");
        precipitation.setText("Precipitation " + condition.getPrecipitation() + " mm");
        humidity.setText("Humidity " + condition.getHumidity() + "%");
        rain.setText("Chances of rain " + condition.getChanceRain() + "%");
        uv.setText("UltraViolet index " + condition.getUV());
        visibility.setText("Visibility " + condition.getVisibility() + " km");
        dew.setText("Dew point " + condition.getDew());
        chill.setText("Windchill " + condition.getChill());
        cloud.setText("Cloud cover " + condition.getCloud() + "%");
        Glide.with(listItemView).load(condition.getImageSrc()).into(img);

        return listItemView;
    }
}































