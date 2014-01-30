package com.example.weatherpaper;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;

/**
 * Created by Fanta on 14.01.14.
 */
public class WallService extends Service {

    public void onCreate() {

    }

    public int onStartCommand(Intent intent, int flags, int startId){

        SharedPreferences SP = getSharedPreferences("prefs",MODE_PRIVATE);
        String prefCity = SP.getString("city", "");
        parser parser = new parser();
        parser.setCity(prefCity);
        parser.execute();

        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent intent) {

        return null;
    }

}