package com.example.weatherpaper;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Looper;
import android.telephony.cdma.CdmaCellLocation;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by Fanta on 11.01.14.
 */
public class ServiceHandler{

    private String uri = "";
    private String response = "";
    private String latitude;
    private String longitude;

    Parser parser;

    LocationManager mLocManager;

    Locator locator;

    Location location;

    Context mContext;

    public ServiceHandler(Context context){
        this.mContext = context;
    }

    public String ServiceHandlerCall(){


        locator = new Locator(mContext);
        //mLocManager = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);
        //location = mLocManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        //locator.GetLocation();
        latitude = String.valueOf(locator.GetLatitude())/*.substring(0,5)*/;
        longitude = String.valueOf(locator.GetLongitude())/*.substring(0,5)*/;

        uri = "http://api.worldweatheronline.com/free/v1/weather.ashx?key=gk8bxhpt8vajvwda59dtqzj9&q="+latitude+","+longitude+"&fx=no&includeLocation=yes&format=json";

        Thread http = new Thread(new Runnable(){
            @Override
            public void run() {
        try{

            // Looper.myLooper().quit();
            // Looper.prepare();

                    DefaultHttpClient defhttp = new DefaultHttpClient();
                    HttpGet get = new HttpGet(uri);

                    HttpResponse resp = defhttp.execute(get);

                    //Looper.getMainLooper().quit();
                    response= EntityUtils.toString(resp.getEntity());




        parser = new Parser(mContext,response);
        parser.execute();

        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    } );
        http.start();
        return response;
    }
}
