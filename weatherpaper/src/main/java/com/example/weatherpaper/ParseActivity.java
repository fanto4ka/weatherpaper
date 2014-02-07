package com.example.weatherpaper;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class ParseActivity extends Activity {

    final String CITY = "city";
    final String prefs = "prefs";
    Looper myLooper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse);


        final EditText city_val=(EditText)findViewById(R.id.city);
        city_val.setOnFocusChangeListener(new View.OnFocusChangeListener(){
            public void onFocusChange(View view, boolean hasFocus){
                if (!hasFocus){
                    String city = city_val.getText().toString();
                    SharedPreferences SP = getSharedPreferences(prefs,MODE_PRIVATE);
                    SharedPreferences.Editor  ed = SP.edit();
                    ed.putString(CITY, city);
                    ed.commit();
                    String prefCity = SP.getString(CITY, "");


                    parser parser = new parser();
                    parser.setCity(prefCity);
                    parser.execute();

                }
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.parse, menu);
        return true;
    }
    
}
