package com.teamsym.localinfo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by Murali on 01/11/2015.
 */
public class DataActivity extends AppCompatActivity {
    Weather weather;
    ArrayList<String> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        String value = this.getIntent().getStringExtra("Key");
        /*if(value.equals("commodity")){
            Commodity commodity = new Commodity();
            commodity.execute();
        }*/

        weather = new Weather();
        weather.execute();




    }

    public void setData() {
        list = weather.getList();
    }
}
