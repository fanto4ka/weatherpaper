package com.example.weatherpaper;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Fanta on 14.01.14.
 */
public class WallService extends Service {

    public void onCreate() {

    }

    public int onStartCommand(Intent intent, int flags, int startId){

        parser parser = new parser();
        parser.setCity();
        parser.execute();

        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent intent) {

        return null;
    }

}