package com.example.weatherpaper;

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
public class ServiceHandler {
    String uri = "";
    String response = "";
    public String ServiceHandlerCall(String city){

        uri = "http://api.worldweatheronline.com/free/v1/weather.ashx?key=gk8bxhpt8vajvwda59dtqzj9&q="+ city +"&fx=no&format=json";
    try{
        DefaultHttpClient defhttp = new DefaultHttpClient();
        HttpGet get = new HttpGet(uri);
        HttpResponse resp = defhttp.execute(get);
        response= EntityUtils.toString(resp.getEntity());

    }catch (UnsupportedEncodingException e) {
        e.printStackTrace();
    } catch (ClientProtocolException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
        return response;
    }

}
