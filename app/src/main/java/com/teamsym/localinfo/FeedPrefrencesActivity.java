package com.teamsym.localinfo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Murali on 01/11/2015.
 */
public class FeedPrefrencesActivity extends AppCompatActivity implements ListView.OnItemClickListener, View.OnClickListener {
    ListView prefrenceListView;
    Button nextButton;

    PrefrenceHub prefrenceHub;
    ArrayList<Prefrence> list;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        preferences = getSharedPreferences(Keys.PREFRENCE_FILE_NAME, MODE_PRIVATE);
        editor = preferences.edit();
        setContentView(R.layout.activity_feed_prefrences);
        prefrenceHub = PrefrenceHub.getPrefrenceHub(this);
        list = prefrenceHub.getPrefrenceList();
        nextButton = (Button) findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this);
        prefrenceListView = (ListView) findViewById(R.id.prefrencesListView);

        PrefrenceAdapter prefrenceAdapter = new PrefrenceAdapter(this, 0, list);
        prefrenceListView.setAdapter(prefrenceAdapter);
        prefrenceListView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        CheckBox cb = (CheckBox) adapterView.findViewById(R.id.prefrenceCheckBox);
        String checked_preference_name = cb.getText().toString();
        Toast.makeText(getApplicationContext(), checked_preference_name, Toast.LENGTH_SHORT).show();
        if(cb.isChecked()){
            Toast.makeText(getApplicationContext(), checked_preference_name, Toast.LENGTH_SHORT).show();
            editor.putString(Keys.KEY_PREFERENCE_NAME, checked_preference_name);
            editor.commit();
        }
    }

    @Override
    public void onClick(View view) {
        if(R.id.nextButton == view.getId()){
            Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
            startActivity(intent);
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
                convertView = inflater.inflate(R.layout.single_row, parent, false);
            }
            Prefrence prefrence = list.get(position);
            CheckBox prefrenceCheckBox = (CheckBox)convertView.findViewById(R.id.prefrenceCheckBox);
            prefrenceCheckBox.setChecked(prefrence.isChecked);
            prefrenceCheckBox.setText(prefrence.getPrefrenceTitle());

            return convertView;

        }
    }
}
