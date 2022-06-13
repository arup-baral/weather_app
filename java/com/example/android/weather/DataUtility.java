package com.example.android.weather;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class DataUtility {

    private static final MutableLiveData<CurrentCondition> myRepo = new MutableLiveData<>();
    private static final MutableLiveData<ArrayList<HourCondition>> hourRepo = new MutableLiveData<>();
    private Thread myThread;

    static Calendar calendar = Calendar.getInstance();

    public LiveData<CurrentCondition> getRepo(){
        return myRepo;
    }

    public LiveData<ArrayList<HourCondition>> getHourRepo(){
        return hourRepo;
    }

    public void searchCondition(String url){
        Runnable runnable = () -> fetchDataFromURL(url);

        if(myThread != null){
            myThread.interrupt();
        }
        myThread = new Thread(runnable);
        myThread.start();
    }

    private static URL Url;

    private static void fetchDataFromURL(String url_s){
        Url = createURL(url_s);
        String jsonResponse = "";
        try{
            jsonResponse = makeHttpRequest(Url);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        extractJsonResponse(jsonResponse);
    }

    private static URL createURL(String url){
        URL Url = null;
        try{
            Url = new URL(url);
        }
        catch(MalformedURLException e){
            e.printStackTrace();
        }
        return Url;
    }

    static byte[] data = new byte[1024];

    private static String makeHttpRequest(URL url) throws IOException{
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        String jsonResponse = "";
        try{
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            inputStream = urlConnection.getInputStream();
            jsonResponse = readFromStream(inputStream);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        finally{
            if(urlConnection != null){
                urlConnection.disconnect();
            }
            if(inputStream != null){
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private static String readFromStream(InputStream inputStream){
        StringBuilder sb = new StringBuilder();
        if(inputStream == null){
            return null;
        }
        try{
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader br = new BufferedReader(inputStreamReader);
            String s = br.readLine();
            while(s != null){
                sb.append(s);
                s = br.readLine();
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return sb.toString();
    }

    private static void extractJsonResponse(String jsonResponse){
        CurrentCondition data;
        ArrayList<HourCondition> list = new ArrayList<>();
        try{
            JSONObject root = new JSONObject(jsonResponse);
            JSONObject current = root.getJSONObject("current");
            int curr_temp = (int) current.getDouble("temp_c");
            JSONObject forecast = root.getJSONObject("forecast");
            JSONArray forecast_today = forecast.getJSONArray("forecastday");
            JSONObject item = forecast_today.getJSONObject(0);
            JSONObject day = item.getJSONObject("day");
            int max_temp = (int) day.getDouble("maxtemp_c");
            int min_temp = (int) day.getDouble("mintemp_c");
            JSONObject condition = day.getJSONObject("condition");
            String cond = condition.getString("text");
            String week_day = Day(Url);
            int aprn_temp = (int) day.getDouble("avgtemp_c");
            int visibility = (int) current.getDouble("vis_km");
            int air_p = (int) current.getDouble("pressure_mb");
            int uv = (int) current.getDouble("uv");
            int humidity = (int) current.getDouble("humidity");
            int wVel = (int) current.getDouble("wind_kph");
            String wDir = current.getString("wind_dir");
            int isDay = current.getInt("is_day");
            data = new CurrentCondition(curr_temp, max_temp, min_temp, cond, week_day, aprn_temp, visibility, air_p, uv, humidity, wVel, wDir, isDay);
            myRepo.postValue(data);

            JSONArray hour = item.getJSONArray("hour");
            for(int i=0;i<hour.length();i++){
                JSONObject x = hour.getJSONObject(i);
                int temp = (int) x.getDouble("temp_c");
                JSONObject con = x.getJSONObject("condition");
                String c = con.getString("text");
                String img = con.getString("icon");
                img = "https:" + img;
                int windVel = (int) x.getDouble("wind_kph");
                String windDir = x.getString("wind_dir");
                int pressure = (int) x.getDouble("pressure_mb");
                double precipitation = x.getDouble("precip_in");
                int humidity_item = x.getInt("humidity");
                int cloud = x.getInt("cloud");
                int chill = (int) x.getDouble("windchill_c");
                int dew = (int) x.getDouble("dewpoint_c");
                String rain_chance = x.getString("chance_of_rain");
                int visibility_item = (int) x.getDouble("vis_km");
                int UV = (int) x.getDouble("uv");
                String time = x.getString("time");
                time = time.trim().substring(11);
                String date = date();
                list.add(new HourCondition(temp, windVel, pressure, humidity_item, rain_chance, UV, visibility_item, dew,
                        chill, cloud, precipitation, date, time, c, windDir, img));
            }
            hourRepo.postValue(list);
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    }

    private static String Day(URL url) {
        String day = "";
        try{
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            String d = conn.getHeaderField("Date");
            day = d.trim().substring(0, 16);
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return day;
    }

    private static String date(){
        @SuppressLint("SimpleDateFormat") SimpleDateFormat sdf = new SimpleDateFormat("MM dd, yyyy");
        return sdf.format(calendar.getTime());
    }
}



























