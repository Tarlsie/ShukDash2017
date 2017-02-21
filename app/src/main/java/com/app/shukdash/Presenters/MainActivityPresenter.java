package com.app.shukdash.Presenters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.app.shukdash.Interfaces.ICheckParseData;
import com.app.shukdash.Models.GetFireBaseData;
import com.google.firebase.FirebaseException;

import java.util.Map;

/**
 * Created by danielT on 09/08/2015.
 */
public class MainActivityPresenter extends Activity{

    public Context context;
    private ICheckParseData data;

    public MainActivityPresenter(ICheckParseData parseData, Context context) {
    this.data = parseData;
    this.context = context;
    }
    public boolean getFireBaseDataFromServer() throws FirebaseException{

        boolean success = false;
        Log.i("Parse init", "initialise getparsedata object");
        GetFireBaseData gfbd = new GetFireBaseData(context);
        success = gfbd.getFBData();
        return success;
    }

    public void checkFBDataBase(){

        boolean success = false;

        Log.i("Parse init", "initialise getparsedata object");

        GetFireBaseData gfbd = new GetFireBaseData(context);

        int isDBExist = gfbd.isDatabaseExist();

        ///////// if isDBExist = 0 -> DB does not exist, must download and create a new one////////////
        if (isDBExist==0) {
            Log.i("ShukDash", "DB DOES NOT exist");
            success = gfbd.getFBData();
            Log.i("Parse init", "end of getparsedata object");
        }
        ///////// if isDBExist = 1 -> DB does exist, check if iy cotains data////////////
        else{
            Log.i("ShukDash", "DB exists ");
            boolean isDBData = gfbd.isDataBaseContainData(isDBExist);
        //////// if isDBExist = 1 -> DB does exist, does not contain data - download data/////////
            if(!isDBData){
                Log.i("ShukDash", "DB exists BUT does NOT contain data");
                success = gfbd.getFBData();
                Log.i("Parse init", "end of getparsedata object");
            }
            //////// if isDBExist = 1 -> DB does exist, and cotains data/////////
            else {
                success = true;
                Log.i("ShukDash", "DB exists AND contains Data");
            }
        }


        //check the log to see that firebase data has been saved and can be accessed from shared preferences
        // this can be removed later
        SharedPreferences getPrefs = context.getSharedPreferences("category3", Context.MODE_PRIVATE);

        Map<String, ?> prefsData = getPrefs.getAll();
        int prefSize = getPrefs.getInt("catLength",1);
        Log.i("Parse Shared Prefs", "Data Size is: " +String.valueOf(prefSize));
        for (int i =0 ; i<prefSize;i++) {
            String name = getPrefs.getString("Name"+i, "");
            String descript = getPrefs.getString("Description"+i, "");

            Log.i("Parse Shared Prefs", "Results are: " + name + " " + descript);
        }

        if (success){
            Log.i("Presenter FireBase Cat", "if success");
            data.onSuccess();
        }
        else if (!success){
            Log.i("Presenter Firebase Cat", "if error");
            data.onError();
        }
    }

}

    /*  private InitDashData dataFromParse;
    List<InitDashData> cat1 = new ArrayList<InitDashData>();
    List<InitDashData> cat2 = new ArrayList<InitDashData>();
    List<InitDashData> cat3 = new ArrayList<InitDashData>();
    List<InitDashData> cat4 = new ArrayList<InitDashData>();
    List<InitDashData> cat5 = new ArrayList<InitDashData>();
    List<InitDashData> cat6 = new ArrayList<InitDashData>();
    List<InitDashData> initDataFromServer = new ArrayList<InitDashData>();

    //  ShukDashDB shukDB = new ShukDashDB(this);
                //SQLiteDatabase checkDB =shukDB.getReadableDatabase();
                // shukDB.onCreate(checkDB);
    */