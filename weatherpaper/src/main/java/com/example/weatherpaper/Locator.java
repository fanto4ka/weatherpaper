package com.example.weatherpaper;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import java.util.List;

/**
 * Created by skozyrev on 2/4/14.
 */
public class Locator implements LocationListener{

    private final Context mContext;
    boolean isInternet = false;
    boolean isGPS = false;
    boolean canGetLocation = false;
    protected LocationManager locationManager;
    private static final long distance = 1000;
    private static final long update_time = 1000 * 60 * 3;

    Location location;
    private double latitude;
    private double longitude;

    public Locator(Context context){
        this.mContext = context;
        try{
            locationManager = (LocationManager)mContext.getSystemService(Context.LOCATION_SERVICE);

            //check GPS
            isGPS = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            //check Internet
            isInternet = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if(!isInternet && !isGPS){
            }
            else{
                if (!isGPS){
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0,this);
                    if (locationManager != null){
                        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    }
                    if (location!=null){
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                    }
                }
                else {
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,0,0,this);
                    if (locationManager != null){
                        location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    }
                    if (location!=null){
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                    }
                }
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    protected Location GetLocation(){
     return this.location;
    }

    public double GetLatitude(){
        return latitude;

    }

    public double GetLongitude(){
        return longitude;
    }

    @Override
    public void onLocationChanged(Location location) {
        location.getLatitude();
        location.getLongitude();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }
}
