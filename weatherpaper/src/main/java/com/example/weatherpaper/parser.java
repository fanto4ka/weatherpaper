package com.example.weatherpaper;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;


/**
 * Created by Fanta on 09.01.14.
 */
public class Parser extends AsyncTask<Void, Void, Void> {

    ServiceHandler serviceHandler;

    private JSONObject data = null; //JSON response

    private JSONArray current_condition = null;
    private JSONArray nearest_area = null;
    private JSONArray areaName = null;
    private JSONArray country = null;
    private JSONArray region = null;
    private JSONArray weatherDesc = null;

    private String response;
    private String cloudcover;  //облачность
    private String humidity;   //влажность
    private String pressure;   //давление
    private String temp_C;     //температура, цельсий
    private String weatherCode; //код погоды
    private String windsppedKmph; //скорость ветра, км\ч
    private String windspeedMiles; //скорость ветра, мили



    Context mContext;

    public Parser(Context context){
        this.mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog

    }

    @Override
    protected Void doInBackground(Void... arg0) {

        Looper.prepare();
        serviceHandler = new ServiceHandler(mContext);
        response = serviceHandler.ServiceHandlerCall();

        if(response != null){
            try{
                data = new JSONObject(response);
                current_condition = data.getJSONObject("data").getJSONArray("current_condition");

                nearest_area = data.getJSONObject("data").getJSONArray("nearest_area");

                areaName = nearest_area.getJSONObject(0).getJSONArray("areaName");
                country = nearest_area.getJSONObject(0).getJSONArray("country");
                region = nearest_area.getJSONObject(0).getJSONArray("region");

                cloudcover = current_condition.getJSONObject(0).getString("cloudcover");
                humidity = current_condition.getJSONObject(0).getString("humidity");
                pressure = current_condition.getJSONObject(0).getString("pressure");
                temp_C = current_condition.getJSONObject(0).getString("temp_C");
                weatherCode = current_condition.getJSONObject(0).getString("weatherCode");
                windsppedKmph = current_condition.getJSONObject(0).getString("windsppedKmph");
                windspeedMiles = current_condition.getJSONObject(0).getString("windspeedMiles");



                Looper.getMainLooper().quit();

            }
            catch(JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    protected void onPostExecute(String result) {
        /*super.onPostExecute(result);
        Log.d("Out:", weatherCode);
        String response2 = ""; */




    }
}
