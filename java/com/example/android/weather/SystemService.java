package com.example.android.weather;

import android.content.Context;

public interface SystemService {

    /**
     * method for checking connection status
     *
     * @param context -> activityContext
     * @return -> bool dataType
     */
    boolean connectivityService(Context context);
}
