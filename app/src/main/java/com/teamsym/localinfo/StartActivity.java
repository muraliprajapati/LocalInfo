package com.teamsym.localinfo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Murali on 01/11/2015.
 */
public class StartActivity extends AppCompatActivity implements View.OnClickListener{
    ImageView smsImageView;
    ImageView internetImageView;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        preferences = this.getSharedPreferences(Keys.PREFRENCE_FILE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();
        smsImageView = (ImageView) findViewById(R.id.smsImageView);
        internetImageView = (ImageView) findViewById(R.id.internetImageView);
        smsImageView.setOnClickListener(this);
        internetImageView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch(id){
            case R.id.smsImageView:
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                editor.putBoolean(Keys.KEY_MODE_SMS,true);
                editor.commit();
                startActivity(intent);
                break;
            case R.id.internetImageView:
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                editor.putBoolean(Keys.KEY_MODE_SMS, false);
                editor.commit();
                startActivity(i);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        editor.remove(Keys.KEY_MODE_SMS);
        editor.commit();
    }
}
