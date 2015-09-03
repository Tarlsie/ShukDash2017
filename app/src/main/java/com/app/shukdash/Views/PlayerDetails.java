package com.app.shukdash.Views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.shukdash.Presenters.ISaveTeamName;
import com.app.shukdash.Presenters.PlayerDetailsPresenter;
import com.example.shukdash.R;

public class PlayerDetails extends AppCompatActivity implements ISaveTeamName {

    PlayerDetailsPresenter pdPresenter;
    EditText teamName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);

        Button enterTeamName = (Button)findViewById(R.id.btnTeamName);
        teamName = (EditText)findViewById(R.id.edTxtTeamName);
        pdPresenter = new PlayerDetailsPresenter(this, getApplicationContext());

        enterTeamName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                pdPresenter.onButtonTeamNameClick(teamName.getText().toString());
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_player_details, menu);
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

    @Override
    public void teamNameSaved() {
        Intent i = new Intent(PlayerDetails.this, Instructions.class);
        i.putExtra("TeamName", teamName.getText().toString());
        startActivity(i);
    }
}
