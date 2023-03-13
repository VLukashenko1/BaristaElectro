package com.example.baristaelectro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.WindowManager;
import android.widget.Button;

import com.example.baristaelectro.DataCollector.DrinkModel;
import com.example.baristaelectro.DataCollector.ResultSaver;
import com.example.baristaelectro.ReportMaker.ManualReport.ManualReportDataCollector;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    ResultSaver resultSaver = new ResultSaver();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON); //do not off screen

        recyclerView = findViewById(R.id.rv);

        rvFiller();

        Button sendButton = findViewById(R.id.sendButton);
        sendButton.setOnClickListener(view -> {
            resultSaver.saveCheckedDrinks();
            rvFiller();
        });
        sendButton.setOnLongClickListener(view -> {
            startActivity(new Intent(this, ManualPushActivity.class));
            return false;
        });

        //askAccessToOverlay();
    }

    private void rvFiller(){
        ListAdapter listAdapter = new ListAdapter(this, getInitList());
        recyclerView.setAdapter(listAdapter);

    }
    private List<DrinkModel> getInitList() {
        List<DrinkModel> drinkModelList = new ArrayList<>();

        drinkModelList.add(new DrinkModel("Кола", getDrawable(R.drawable.cola)));
        drinkModelList.add(new DrinkModel("Фанта", getDrawable(R.drawable.fanta)));
        drinkModelList.add(new DrinkModel("Спрайт", getDrawable(R.drawable.sprite)));
        drinkModelList.add(new DrinkModel("Апельсиновий сік", getDrawable(R.drawable.juice)));
        drinkModelList.add(new DrinkModel("Лате", getDrawable(R.drawable.latte)));
        drinkModelList.add(new DrinkModel("Капучино", getDrawable(R.drawable.capucino)));
        drinkModelList.add(new DrinkModel("Американо з молоком", getDrawable(R.drawable.amerikanomilk)));
        drinkModelList.add(new DrinkModel("Американо", getDrawable(R.drawable.amerikano)));
        drinkModelList.add(new DrinkModel("Флет вайт", getDrawable(R.drawable.flatwhite)));
        drinkModelList.add(new DrinkModel("Еспресо", getDrawable(R.drawable.espresso)));
        drinkModelList.add(new DrinkModel("Подвійний еспресо", getDrawable(R.drawable.doubleespreso)));
        drinkModelList.add(new DrinkModel("Чай чорний", getDrawable(R.drawable.blacktea)));
        drinkModelList.add(new DrinkModel("Чай зелений", getDrawable(R.drawable.greentea)));

        return drinkModelList;
    }
    private void askAccessToOverlay(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                // TODO: 01.02.2023 make alert dialog
                startActivity(intent);
            }
        }
    }
}