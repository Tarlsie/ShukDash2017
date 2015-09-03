package com.app.shukdash.Presenters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.app.shukdash.Models.CatDetailsData;
import com.app.shukdash.Models.GetParseData;
import com.example.shukdash.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielT on 24/08/2015.
 */
public class ShukDashPresenter extends Activity {

    Context c;
    public ShukDashPresenter(Context c){
        this.c = c;
    }

    public ArrayList<String> getCatDataFromStringArray(){

        ArrayList<String> data = new ArrayList<String>();

        String[] cat1 = getResources().getStringArray(R.array.catName);

        data.add(cat1.toString());

        return data;
    }
/*
    public void initCatData() {
        GetParseData data;
        data = new GetParseData(c);

        List<CatDetailsData> dataReturned = data.getCategoryDetails();
        int in = dataReturned.size();
        Log.i("Parse catDetails", "size " + String.valueOf(in));

        SharedPreferences sharedPrefsCatDetails = c.getSharedPreferences("CategoryDetails", MODE_PRIVATE);
        SharedPreferences.Editor edit = sharedPrefsCatDetails.edit();
        Log.i("Parse catDetails", "shared prefs declared");
        for (CatDetailsData d : dataReturned) {
            //for( int i =0; i<categDetails.size(); i++ ) {
            // CatDetailsData details = new CatDetailsData();
            //details.setCategoryCode(detaildata.getCategoryCode());
            // details.setCategoryName(detaildata.getCategoryName());
            // details.setNumTasks(detaildata.getNumTasks());
            Log.i("Parse catDetails", "for loop");

            String catCode = d.getCategoryCode();
            String catName = d.getCategoryName();
            String numOfTasks = d.getNumTasks();


            edit.putString("categoryNum", catCode);
            edit.putString("categoryName", catName);
            edit.putString("NumOfTasks", numOfTasks);

        }
        edit.commit();
    }
    */
}
