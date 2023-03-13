package com.example.baristaelectro.ReportMaker;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class ReportBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Runnable task = () -> {
            ReportMaker reportMaker = new ReportMaker();
            reportMaker.getDataFromFirebase(context);
        };
        new Thread(task).run();
    }

}