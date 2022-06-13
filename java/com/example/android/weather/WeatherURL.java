package com.example.android.weather;

public class WeatherURL {
    private static final String DOMAIN =
            "https://api.weatherapi.com";

    private static final String RESPONSE_FORMAT = "/v1/forecast.json?";
    private static final String API_KEY = "37f8f64c96ba4f0fbe0163838210804";
    private static final String SUFFIX = "&days=1&aqi=no&alerts=no";

    private final String mLocation;

    public WeatherURL(String location){
        this.mLocation = location;
    }

    public String getUrl(){
        return (DOMAIN + RESPONSE_FORMAT + "key=" + API_KEY + "&q=" + mLocation + SUFFIX);
    }

}
