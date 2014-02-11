package com.example.weatherpaper;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;

/**
 * Created by Fanta on 14.01.14.
 */
public class WallService extends Service {

    ServiceHandler serviceHandler;
    String response;

    public void onCreate() {

    }

    public int onStartCommand(Intent intent, int flags, int startId){

        SharedPreferences SP = getSharedPreferences("prefs",MODE_PRIVATE);
        String prefCity = SP.getString("city", "");
        serviceHandler = new ServiceHandler(WallService.this);
        response = serviceHandler.ServiceHandlerCall();
        //Parser Parser = new Parser(WallService.this);
        //Parser.execute();

        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent intent) {

        return null;
    }

}