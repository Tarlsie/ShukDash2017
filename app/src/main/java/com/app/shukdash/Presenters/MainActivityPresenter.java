package com.app.shukdash.Presenters;



import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.shukdash.Models.GetParseData;
import com.app.shukdash.Models.InitDashData;
import com.app.shukdash.Models.ShukDashDB;
import com.app.shukdash.Views.ShukDash;
import com.parse.ParseException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by danielT on 09/08/2015.
 */
public class MainActivityPresenter extends Activity{

    public Context context;
    private ICheckParseData data;
  //  private InitDashData dataFromParse;
    List<InitDashData> cat1 = new ArrayList<InitDashData>();
    List<InitDashData> cat2 = new ArrayList<InitDashData>();
    List<InitDashData> cat3 = new ArrayList<InitDashData>();
    List<InitDashData> cat4 = new ArrayList<InitDashData>();
    List<InitDashData> cat5 = new ArrayList<InitDashData>();
    List<InitDashData> cat6 = new ArrayList<InitDashData>();
    List<InitDashData> initDataFromServer = new ArrayList<InitDashData>();

    public MainActivityPresenter(ICheckParseData parseData, Context context) {
    this.data = parseData;
    this.context = context;
    }

    public void checkParse() throws ParseException {
        boolean success = false;


        Log.i("Parse init", "initialise getparsedata object");


        GetParseData gpd = new GetParseData(context);
        boolean isDBExist = gpd.isDatabaseExist();
        //parseQuery will retrieve the data from the parse server and store the results in six seperate shared preferences to be referenced when filling in the
        //missions cards

        if (!isDBExist) {
            Log.i("ShukDash", "DB DOES NOT exist");
            success = gpd.parseQuery();
            Log.i("Parse init", "end of getparsedata object");
        }

        else{
            Log.i("ShukDash", "DB exists ");
            boolean isDBData = gpd.isDataBaseContainData();

            if(!isDBData){
                Log.i("ShukDash", "DB exists BUT does NOT contain data");
              //  ShukDashDB shukDB = new ShukDashDB(this);
               //SQLiteDatabase checkDB =shukDB.getReadableDatabase();
               // shukDB.onCreate(checkDB);
                success = gpd.parseQuery();
                Log.i("Parse init", "end of getparsedata object");
            }
            else {
                success = true;
                Log.i("ShukDash", "DB exists AND contains Data");
            }
        }


        //check the log to see that parse data has been saved and can be accessed from shared preferences
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
            Log.i("Presenter Parse Cat", "if success");
            data.onSuccess();
        }
        else if (!success){
            Log.i("Presenter Parse Cat", "if error");
            data.onError();
        }
    }

}

/*
Log.i("Parse Cat Lists","init");
        for( int i = 0; i<cat1.size();i++)

        {
        Log.i("Parse Cat Lists", "init " + String.valueOf(i));

        SharedPreferences.Editor editor = category1.edit();
        editor.putString("Name", cat1.get(i).getCategoryName()); //catName
        editor.putString("Description", cat1.get(i).getDescription()); //info.getDescription()
        editor.putString("taskNum", String.valueOf(cat1.get(i).getTaskNumber())); //info.getTaskNumber());6
        editor.putString("Points", String.valueOf(cat1.get(i).getPoints()));  //info.getPoints());5
        editor.commit();

        Log.i("Parse Cat Lists", "Cat1: " + cat1.get(i).getDescription() + " " + cat1.get(i).getCategoryName() + " " + String.valueOf(cat1.get(i).getTaskNumber() + " " + String.valueOf(cat1.get(i).getPoints())));
        }


   synchronized (gpd) {
            try {


                Log.i("Parse try", "gpd");
                initDataFromServer =
                Log.i("Parse wait", "gpd");
                gpd.wait();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        Log.i("Parse return ", "gpd");
            if (initDataFromServer.isEmpty()) {
                Log.i("Parse init1", "is empty");
            }
       }
       // Handler hand = new Handler();


     //   Runnable wait = new Runnable() {
       //     @Override
       //     public void run() {

                 Log.i("Presenter Parse Cat", "Waiting 5 secs");
        Iterator it;
        it = initDataFromServer.iterator();
        while(it.hasNext())
        {Log.i("Presenter Parse Cat", "for loop");
            for(int i = 0; i<initDataFromServer.size(); i++){

                if( initDataFromServer.get(i).getCategoryCode().intValue() == (i+1))
                {Log.i("Presenter Parse Cat", "begining shared pref");
                    SharedPreferences sharedPrefs = getSharedPreferences("Cat"+(i+1), Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = sharedPrefs.edit();

                    edit.putString("Name", initDataFromServer.get(i).getCategoryName());
                    edit.commit();

                    Log.i("Presenter Parse Cat", "end shared pref");
                }
            }
      //  }


      //      }
        };
       // hand.postDelayed(wait, 1000);
       // int size1 = initDataFromServer.size();
       // Log.i("Parse init", "size1: " + String.valueOf(size1));

       // SharedPreferences category1;
      //  category1 = getSharedPreferences("Category1Data" , Context.MODE_PRIVATE);



        */

/*
        Thread getparse = new Thread(new Runnable() {
            @Override
            public void run() {
                GetParseData gpd = new GetParseData(context);
               try{
                   Log.i("Parse init", "parse query started");
                   gpd.parseQuery();
                   Log.i("Parse init", "parse query returned");
               }
               catch(ParseException e){
                   e.printStackTrace();
               }
                Log.i("Parse init", "parse query completed");

            }
        });

        getparse.start();

*/