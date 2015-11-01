package com.teamsym.localinfo;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Spinner.OnItemClickListener{

    public static final String PREFRENCE_FILE_NAME = "user_pref";
    Spinner stateDropDownList;
    Spinner languageDropDownList;
    EditText pincodeEditText;

    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        preferences = this.getSharedPreferences(PREFRENCE_FILE_NAME, Context.MODE_PRIVATE);
        editor = preferences.edit();

        stateDropDownList = (Spinner) findViewById(R.id.stateSpinner);
        languageDropDownList = (Spinner) findViewById(R.id.languageSpinner);
        pincodeEditText = (EditText)findViewById(R.id.pincodeEditText);

        ArrayAdapter<CharSequence> stateAdapter = ArrayAdapter.createFromResource(this,R.array.states,android.R.layout.simple_spinner_dropdown_item);
        ArrayAdapter<CharSequence> languageAdapter = ArrayAdapter.createFromResource(this,R.array.languages,android.R.layout.simple_spinner_dropdown_item);
        stateDropDownList.setAdapter(stateAdapter);
        languageDropDownList.setAdapter(languageAdapter);



    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        int id = view.

        switch (id){
            case R.id.stateSpinner:
                String state = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),state,Toast.LENGTH_SHORT).show();
                break;
            case R.id.languageSpinner:
                String language = adapterView.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),language,Toast.LENGTH_SHORT).show();
                break;
            default:
                Toast.makeText(getApplicationContext(),"Nothing",Toast.LENGTH_SHORT).show();
        }
    }
}
