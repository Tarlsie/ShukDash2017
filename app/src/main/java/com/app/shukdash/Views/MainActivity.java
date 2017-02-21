package com.app.shukdash.Views;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.app.shukdash.Interfaces.ICheckParseData;
import com.app.shukdash.Presenters.MainActivityPresenter;
import com.example.shukdash.R;


public class MainActivity extends AppCompatActivity implements ICheckParseData {

    Context context;
    SharedPreferences category1;
    MainActivityPresenter presenter;
    RelativeLayout relLMain;
    CoordinatorLayout coordLMain;

    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Shukdash MainActivity", "onCreate started");
        context = getApplicationContext();

            //ToDo: Do I need to check if the firebase db is created ot enabled

        relLMain = (RelativeLayout)findViewById(R.id.relLMain);
        coordLMain = (CoordinatorLayout)findViewById(R.id.CoordLMain);
        presenter = new MainActivityPresenter(this, context);

        Log.i("Shukdash MainActivity", "onCreate before try Firebase");

        presenter.checkFBDataBase();
        //TODO: check callback for cancelled or failed data call!!!
        ///// what happens if the return callback has an exception - how do we retry to download the data???? /////////

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
        Log.i("Result from Interface", "toast");
     //   Toast.makeText(this, "There has been an error accessing the database please close the app!",Toast.LENGTH_LONG).show();
        Log.i("Result from Interface", "snackbar");
        Snackbar recheck = Snackbar.make(coordLMain,"Check Your Wifi and restart" , Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Log.i("Result from Interface", "snackbar on click");
                    presenter.checkFBDataBase();

                }
                catch (Exception e){
                    Log.i("SnackBar Exception", e.toString());
                }
            }
        });
        Log.i("Result from Interface", "snackbar show");
        recheck.show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Shukdash MainActivity", "onStart started");

        Intent i = getIntent();
        if (i !=null){
            boolean fromPlayer = i.getBooleanExtra("FromPlayerDetailsActivity", false);
            Log.i("Shukdash MainActivity", "onStart isfromplayer "+fromPlayer);
            if (fromPlayer){

                Handler waiting = new Handler();
                waiting.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        Log.i("Shukdash MainActivity", "onStart handler runnable waiting started");
                   //     finish();
                        //could use finish() instead of the startactivity - however if another app had been used then finish would send it back to that one not carry on!!
                        Intent returnToPlayerDetails = new Intent(MainActivity.this, PlayerDetails.class);
                        Log.i("Shukdash MainActivity", "onStart startactivity return to player details activity");
                        startActivity(returnToPlayerDetails);

                    }
                }, 2500);
                Log.i("Shukdash MainActivity", "onStart handler waiting finished");

            }
        }
        /////////////////////////////////////
        //  TODO:check here if this activity was started from a backpress button and automatically forward to the next activity
        /////////////////////////////////////

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Shukdash MainActivity", "onResume started");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Shukdash MainActivity", "onPause started");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Shukdash MainActivity", "onStop started");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Shukdash MainActivity", "onDestroy started");
    }
}



/*   try {
            Log.i("Shukdash MainActivity", "onCreate inside try firebase");
            presenter.checkFBDataBase();
        }
        catch (FirebaseException e){
            Log.i("main activity", "Firebase call to presenter checkfirebase() exception");
        }
    */