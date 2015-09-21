package com.app.shukdash.Views;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shukdash.Models.GetParseData;
import com.app.shukdash.Models.ShukDashDB;
import com.app.shukdash.Presenters.InstructionsPresenter;
import com.example.shukdash.R;
import com.parse.ParseException;

public class Instructions extends AppCompatActivity {

    InstructionsPresenter instructionPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);

        instructionPresenter = new InstructionsPresenter(getApplicationContext());
        TextView instructionsTitle = (TextView)findViewById(R.id.txtVInstructionsHeader);

        Intent i = getIntent();
       final String teamName = i.getStringExtra("TeamName");

        instructionsTitle.setText("Welcome Team "+teamName);


        Button pressToStart = (Button)findViewById(R.id.btnPressStart);
        pressToStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Instructions.this, ShukDash.class);
                i.putExtra("TeamName", teamName);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_instructions, menu);
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
        if (id == R.id.action_deleteGame) {

            Log.i("ShukDash DeleteGame", "Click menu delete");

            Toast.makeText(this, "Delete Game Details", Toast.LENGTH_SHORT).show();

            ShukDashDB db = new ShukDashDB(this);

           db.deleteTable();
            //db.resetAnswersSettings();
            Log.i("ShukDash DeleteGame", "db table deleted");
            Toast.makeText(this, "DB Deleted", Toast.LENGTH_SHORT).show();

           // SQLiteDatabase checkDB =db.getReadableDatabase();
           //  db.onCreate(checkDB);
            GetParseData gpd = new GetParseData(this);
            try{
                gpd.parseQuery();
            }
            catch(ParseException e){
                Log.i("ShukDash ", "parse exception "+e.toString());
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
