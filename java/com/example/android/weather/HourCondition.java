package com.example.android.weather;

public class HourCondition {

    private final int iTemp, iWindVel, iPressure, iHumidity, iUV, iVisibility, iDew, iChill, iCloud;
    private final double iPrecipitation;
    private final String iDate, iTime, iCondition, iWindDir, iImageSrc, iChanceRain;

    public HourCondition(int iTemp, int iWindVel, int iPressure, int iHumidity, String iChanceRain,
                         int iUV, int iVisibility, int iDew, int iChill, int iCloud,
                         double iPrecipitation, String iDate, String iTime, String iCondition,
                         String iWindDir, String iImageSrc) {
        this.iTemp = iTemp;
        this.iWindVel = iWindVel;
        this.iPressure = iPressure;
        this.iHumidity = iHumidity;
        this.iChanceRain = iChanceRain;
        this.iUV = iUV;
        this.iVisibility = iVisibility;
        this.iDew = iDew;
        this.iChill = iChill;
        this.iCloud = iCloud;
        this.iPrecipitation = iPrecipitation;
        this.iDate = iDate;
        this.iTime = iTime;
        this.iCondition = iCondition;
        this.iWindDir = iWindDir;
        this.iImageSrc = iImageSrc;
    }

    public int getTemp() {
        return iTemp;
    }

    public int getWindVel() {
        return iWindVel;
    }

    public int getPressure() {
        return iPressure;
    }

    public int getHumidity() {
        return iHumidity;
    }

    public String getChanceRain() {
        return iChanceRain;
    }

    public int getUV() {
        return iUV;
    }

    public int getVisibility() {
        return iVisibility;
    }

    public int getDew() {
        return iDew;
    }

    public int getChill() {
        return iChill;
    }

    public int getCloud() {
        return iCloud;
    }

    public double getPrecipitation() {
        return iPrecipitation;
    }

    public String getDate() {
        return iDate;
    }

    public String getTime() {
        return iTime;
    }

    public String getCondition() {
        return iCondition;
    }

    public String getWindDir() {
        return iWindDir;
    }

    public String getImageSrc() {
        return iImageSrc;
    }
}
