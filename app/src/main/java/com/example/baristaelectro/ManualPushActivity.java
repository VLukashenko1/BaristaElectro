package com.example.baristaelectro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.baristaelectro.ReportMaker.ManualReport.ManualReportDataCollector;

import java.util.List;

public class ManualPushActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_push);

        ManualReportDataCollector.getInstance().downloadListWithDate();

        ManualReportDataCollector.getInstance().getLiveDataListWithDate().observe(this, value ->{
            if (value!= null){
                showList(value);
            }
            else System.out.println("NULL");
        });
    }

    private void showList(List<String> dateList){
        String[] data = new String[dateList.size()];
        dateList.toArray(data);
        ListView listView = findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);

    }
}