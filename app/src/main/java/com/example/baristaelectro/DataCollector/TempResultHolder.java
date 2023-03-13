package com.example.baristaelectro.DataCollector;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.List;

public class TempResultHolder {

    private static final TempResultHolder holder = new TempResultHolder();
    public static TempResultHolder getInstance() {
        return holder;
    }

    private MutableLiveData<List<DrinkModel>> listMutableLiveData = new MutableLiveData<>();
    public MutableLiveData<List<DrinkModel>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    private MutableLiveData<HashMap<String, Integer>> resultHashMap = new MutableLiveData<>();
    public MutableLiveData<HashMap<String, Integer>> getResultHashMap(){
        return resultHashMap;
    }

}

