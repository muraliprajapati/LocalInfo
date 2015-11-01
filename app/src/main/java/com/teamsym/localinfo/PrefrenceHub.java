package com.teamsym.localinfo;

import android.content.Context;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Murali on 01/11/2015.
 */
public class PrefrenceHub {
    static PrefrenceHub prefrenceHub;
    Context context;
    ArrayList<Prefrence> prefrenceList = new ArrayList<>();
    String[] array;


    PrefrenceHub(Context context){
        this.context = context;
        array = context.getResources().getStringArray(R.array.prefrence_name);
        //array = new String[]{"Government","FED","Seed","tool","Tips","fertilizer","dairy"};
        for (int i = 0;i<array.length;i++){
            Prefrence prefrence = new Prefrence();
            prefrence.setPrefrenceTitle(array[i]);
            prefrence.setIsChecked(false);
            prefrenceList.add(prefrence);
        }

    }


    public static PrefrenceHub getPrefrenceHub(Context context){
        if(prefrenceHub == null){
            prefrenceHub = new PrefrenceHub(context);
        }
        //prefrenceHub = new PrefrenceHub(context.getApplicationContext());
        return prefrenceHub;
    }

    public ArrayList<Prefrence> getPrefrenceList() {
        return prefrenceList;
    }

    public void addPrefrences(){

    }
}
