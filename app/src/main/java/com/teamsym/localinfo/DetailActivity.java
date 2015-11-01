package com.teamsym.localinfo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Murali on 01/11/2015.
 */
public class DetailActivity extends AppCompatActivity implements ListView.OnItemClickListener {
    public static final String phoneNumber = "09248000020";

    ListView listView;
    PrefrenceHub prefrenceHub;
    ArrayList<Prefrence> list;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        preferences = this.getSharedPreferences(Keys.PREFRENCE_FILE_NAME, Context.MODE_PRIVATE);
        listView = (ListView) findViewById(R.id.detailActivityListView);
        prefrenceHub = PrefrenceHub.getPrefrenceHub(this);
        list = prefrenceHub.getPrefrenceList();
        PrefrenceAdapter adapter = new PrefrenceAdapter(this, 0, list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        String value = list.get(i).getPrefrenceTitle();
        if(preferences.getBoolean(Keys.KEY_MODE_SMS,true)){


            Toast.makeText(getApplicationContext(),value + "request sent. You will shortly get information about" + value,Toast.LENGTH_SHORT).show();
            String pinode = preferences.getString(Keys.KEY_PINCODE,"395007");
            String stateName = preferences.getString(Keys.KEY_STATE,"Gujarat");
            String sms = "9727841741 " + value + pinode + stateName;
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phoneNumber, null, sms, null, null);
        }
        else{

            if(value.equalsIgnoreCase("Commodity price")){
                Intent intent = new Intent(getApplicationContext(),DataActivity.class);
                intent.putExtra("Key","commodity");
                startActivity(intent);
            }

            if(value.equalsIgnoreCase("Weather Information")){
                Intent intent = new Intent(getApplicationContext(),DataActivity.class);
                intent.putExtra("Key", "weather");
                startActivity(intent);
            }

        }

    }

    class PrefrenceAdapter extends ArrayAdapter<Prefrence> {

        Context context;

        public PrefrenceAdapter(Context context, int resource, List<Prefrence> list) {
            super(context, resource, list);

            this.context = context;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //super.getView(position, convertView, parent);
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            if (convertView == null) {
                convertView = inflater.inflate(R.layout.single_row_detail_activity, parent, false);
            }
            Prefrence prefrence = list.get(position);
            TextView textView = (TextView)convertView.findViewById(R.id.textViewDetailActivity);
            textView.setText(prefrence.getPrefrenceTitle());

            return convertView;

        }
    }
}