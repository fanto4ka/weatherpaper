package com.example.weatherpaper;

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
public class parser extends AsyncTask<Void, Void, Void> {

    private String city = "";
    String weatherCode ="";

    public void setCity (String city){
        this.city = city;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // Showing progress dialog

    }

    @Override
    protected Void doInBackground(Void... arg0) {

        JSONArray jArr = null;
        Looper.prepare();
        ServiceHandler sh = new ServiceHandler();
        String response = sh.ServiceHandlerCall();

        if(response != null){
            try{
                JSONObject jString = new JSONObject(response);
                jArr = jString.getJSONObject("data").getJSONArray("current_condition");
                JSONObject jObj = jArr.getJSONObject(0);
                String weatherCode = jObj.getString("weatherCode");
                String response2 = "";
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
