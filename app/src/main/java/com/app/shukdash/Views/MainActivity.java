package com.app.shukdash.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.app.shukdash.Models.InitDashData;
import com.app.shukdash.Presenters.ICheckParseData;
import com.app.shukdash.Presenters.MainActivityPresenter;
import com.example.shukdash.R;
import com.parse.Parse;
import com.parse.ParseObject;


public class MainActivity extends AppCompatActivity implements ICheckParseData {

    Context context;
    SharedPreferences category1;
    MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = getApplicationContext();
        // Enable Local Datastore.
        //Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(InitDashData.class);
        Parse.initialize(this, "CQyj2U3KTbShoUMoLuvIfCMZRD3YLMMvckYj4NRB", "ThyYuaCjdeeT0blvElBhXzhS39JRy9xrw8L1yVzu");

        presenter = new MainActivityPresenter(this, context);


        try {

            presenter.checkParse();

        }
        catch (Exception e){
            Log.i("Exception", e.toString());
        }
     //   ArrayAdapter<InitDashData> firstDataList = new ArrayAdapter<InitDashData>(getApplicationContext(),
     //           cat1);
     //   setListAdapter(firstDataList);

        /*
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
*/


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSuccess() {
            Log.i("Result from Interface", "success");
        Handler handler = new Handler();
        Runnable run1 = new Runnable() {
            @Override
            public void run() {
                Log.i("Parse Runnerable", "run");
                Intent i = new Intent(MainActivity.this, PlayerDetails.class);
                startActivity(i);
            }
        };
        handler.postDelayed(run1,1000);
    }

    @Override
    public void onError() {
        Log.i("Result from Interface", "failed");
    }
}
