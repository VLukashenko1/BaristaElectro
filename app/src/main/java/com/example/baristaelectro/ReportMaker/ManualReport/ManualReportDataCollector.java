package com.example.baristaelectro.ReportMaker.ManualReport;

import androidx.lifecycle.MutableLiveData;

import com.example.baristaelectro.DataCollector.Data;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ManualReportDataCollector {
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static final ManualReportDataCollector holder = new ManualReportDataCollector();
    public static ManualReportDataCollector getInstance(){
        return holder;
    }

    private MutableLiveData<List<String>> listWithDate = new MutableLiveData<>();
    public MutableLiveData<List<String>> getLiveDataListWithDate(){
        return listWithDate;
    }

    public void downloadListWithDate(){
        db.collection(Data.CollectionName).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<Date> dateList = new ArrayList<>();
                for (DocumentSnapshot document:queryDocumentSnapshots) {
                    try {
                        dateList.add(Data.dateFormat.parse(document.getId()));
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    prepareDateList(dateList);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void prepareDateList(List<Date> dateList) throws ParseException {
        Collections.sort(dateList);

        List<String> newList = new ArrayList<>();
        for (Date data:dateList){
            newList.add(Data.dateFormat.format(data));
        }
        listWithDate.setValue(newList);
    }
}
