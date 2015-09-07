package com.app.shukdash.Views;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.app.shukdash.Models.GetParseData;
import com.app.shukdash.Models.ShukDashDB;
import com.app.shukdash.Presenters.ShukDashPresenter;
import com.example.shukdash.R;

public class ShukDash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuk_dash);

        Intent i = getIntent();
        String teamName = i.getStringExtra("TeamName");

        ShukDashPresenter presenter = new ShukDashPresenter(getApplicationContext());
      //  GetParseData data;
      //  data = new GetParseData(c);



      //  ShukDashDB db = new ShukDashDB(c);
      //  db.onUpgrade(  ,1,2);
       // presenter.initCatData();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shuk_dash, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
