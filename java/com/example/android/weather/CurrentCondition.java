package com.example.android.weather;

import java.io.Serializable;

public class CurrentCondition implements Serializable {

    private final int current_temp;
    private final int max_temp;
    private final int min_temp;
    private final String condition;
    private final String week_day;
    private final int mAppTemp, mVisibility, mAirPress, mUV, mHumidity, mWindVel;
    private final String mWindDir;
    private final int isDay;

    public CurrentCondition(int current_temp, int max_temp, int min_temp, String condition, String week_day,
                            int mAppTemp, int mVisibility, int mAirPress, int mUV, int mHumidity, int mWindVel,
                            String mWindDir, int day_night) {
        this.current_temp = current_temp;
        this.max_temp = max_temp;
        this.min_temp = min_temp;
        this.condition = condition;
        this.week_day = week_day;
        this.mAppTemp = mAppTemp;
        this.mVisibility = mVisibility;
        this.mAirPress = mAirPress;
        this.mUV = mUV;
        this.mHumidity = mHumidity;
        this.mWindVel = mWindVel;
        this.mWindDir = mWindDir;
        this.isDay = day_night;
        //this.mTime = time;
    }

    public int getCurrent_temp(){
        return current_temp;
    }

    public int getMax_temp(){
        return max_temp;
    }

    public int getMin_temp(){
        return min_temp;
    }

    public String getCondition(){
        return condition;
    }

    public String getWeek_day(){
        return week_day;
    }

    public int getAppTemp() {
        return mAppTemp;
    }

    public int getVisibility() {
        return mVisibility;
    }

    public int getAirPress() {
        return mAirPress;
    }

    public int getUV() {
        return mUV;
    }

    public int getHumidity() {
        return mHumidity;
    }

    public int getWindVel() {
        return mWindVel;
    }

    public String getWindDir() {
        return mWindDir;
    }

    public int getIsDay() {
        return isDay;
    }
}
