package com.app.shukdash.Presenters;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by danielT on 23/08/2015.
 */
public class PlayerDetailsPresenter extends Activity{
    Context playerDContext;
    ISaveTeamName iSaveTeamName;


    public PlayerDetailsPresenter(ISaveTeamName iSaveTeamName ,Context context){
        this.playerDContext = context;
        this.iSaveTeamName = iSaveTeamName;
    }

    public void onButtonTeamNameClick( String teamName){

       // save teamname to a shared preference
        SharedPreferences spTeamName = playerDContext.getSharedPreferences("teamName", MODE_PRIVATE);
        SharedPreferences.Editor editor = spTeamName.edit();

        editor.putString("TeamName", teamName);
        editor.commit();

        iSaveTeamName.teamNameSaved();
    }

}
