package com.example.android.weather;

import android.app.ProgressDialog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MyViewModel extends ViewModel {

    private final DataUtility du = new DataUtility();
    private final LiveData<CurrentCondition> liveData = du.getRepo();
    private final LiveData<ArrayList<HourCondition>> hourRepo = du.getHourRepo();

    public LiveData<CurrentCondition> getData(){
        return liveData;
    }

    public LiveData<ArrayList<HourCondition>> getHourRepo(){
        return hourRepo;
    }

    public void searchResult(String url){
        du.searchCondition(url);
    }

}
