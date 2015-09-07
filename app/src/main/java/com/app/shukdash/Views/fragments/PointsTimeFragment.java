package com.app.shukdash.Views.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.shukdash.Models.ShukDashDB;
import com.example.shukdash.R;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PointsTimeFragment extends Fragment {

    public PointsTimeFragment() {
    }
    int timeCounter = 7199;
    TextView time2;
    int totalPointsNum, totalTasksAnswered, tasksRemaining;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_points_time, container, false);

        ShukDashDB db = new ShukDashDB(getActivity());

      //  TextView totalPoints = (TextView)v.findViewById(R.id.txtVTotalPoints);
        totalPointsNum = 0;
        totalTasksAnswered = 0;
        tasksRemaining=0;

        totalPointsNum = db.getAllPointsTotal();
       // Log.i("ShukDash", "PointsTimeFragment get all points from DB: "+totalPointsNum);
        //totalPoints.setText("Total Points = " + totalPointsNum);

        List<Integer> getAllIsCompleted = db.getAllIsCompleted();
        for(int i =0; i<getAllIsCompleted.size();i++){

            int a = getAllIsCompleted.get(i);
                if (a==1){
                    totalTasksAnswered = totalTasksAnswered+a;
                }

        }

        tasksRemaining = getAllIsCompleted.size()-totalTasksAnswered;

        TextView taskComp = (TextView) v.findViewById(R.id.TxtVDashTasksCompletedDisplay);
        taskComp.setText(String.valueOf(totalTasksAnswered));
        taskComp.setTextColor(Color.BLACK);

        TextView taskUnComp = (TextView) v.findViewById(R.id.TxtVDashTasksRemainingDisplay);
        taskUnComp.setText(String.valueOf(tasksRemaining));
        taskUnComp.setTextColor(Color.BLACK);

        TextView points = (TextView) v.findViewById(R.id.TxtVDashTotalPotentialPointsDisplay);
        points.setText(String.valueOf(totalPointsNum));
        points.setTextColor(Color.BLACK);

        time2 = (TextView)v.findViewById(R.id.TxtVDashTimeRemainingDisplay);
        CountDownTimer count = new CountDownTimer(7200000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // TODO Auto-generated method stub

                int hours = timeCounter/3600;
                int mins = (timeCounter/60);
                if (hours>=1){
                    mins -=60;
                }
                int secs = timeCounter%60;
                //Log.i("timeresult", hours +" "+mins+"  "+secs);
                String timeresult;

                if (hours<1)
                    timeresult = "0"+Integer.toString(hours)+ ":";
                else
                    timeresult = Integer.toString(hours)+ ":";
                if(mins < 10)
                    timeresult += "0" + Integer.toString(mins) + ":";
                else
                    timeresult += Integer.toString(mins) + ":";

                if(secs < 10)
                    timeresult +=  "0" + Integer.toString(secs);
                else
                    timeresult +=  Integer.toString(secs);

                time2.setText(timeresult);
                timeCounter--;
            }

            @Override
            public void onFinish() {
                // TODO Auto-generated method stub
                time2.setText("Finished");
            }
        };
        count.start();



        return  v;
    }

    @Override
    public void onResume() {
        super.onResume();

        SharedPreferences getTimerData = getActivity().getSharedPreferences("timerDetails", Context.MODE_PRIVATE);
        timeCounter = getTimerData.getInt("timeCounter", 7199);

    }

    @Override
    public void onPause() {
        super.onPause();

        SharedPreferences saveTimerPref = getActivity().getSharedPreferences("timerDetails", Context.MODE_PRIVATE);

        SharedPreferences.Editor edit = saveTimerPref.edit();
        edit.putInt("timeCounter", timeCounter );
        edit.commit();


    }
}


/*
SharedPreferences prefs=null ;

        prefs = getActivity().getApplicationContext().getSharedPreferences("category1", Context.MODE_APPEND);
        int catPoints = prefs.getInt("catPointsTotal", 0);
        totalPointsNum = totalPointsNum+catPoints;
        prefs = getActivity().getApplicationContext().getSharedPreferences("category2", Context.MODE_APPEND);
        catPoints = prefs.getInt("catPointsTotal", 0);
        totalPointsNum = totalPointsNum+catPoints;
        prefs = getActivity().getApplicationContext().getSharedPreferences("category3", Context.MODE_APPEND);
        catPoints = prefs.getInt("catPointsTotal", 0);
        totalPointsNum = totalPointsNum+catPoints;
        prefs = getActivity().getApplicationContext().getSharedPreferences("category4", Context.MODE_APPEND);
        catPoints = prefs.getInt("catPointsTotal", 0);
        totalPointsNum = totalPointsNum+catPoints;
        prefs = getActivity().getApplicationContext().getSharedPreferences("category5", Context.MODE_APPEND);
        catPoints = prefs.getInt("catPointsTotal", 0);
        totalPointsNum = totalPointsNum+catPoints;
        prefs = getActivity().getApplicationContext().getSharedPreferences("category6", Context.MODE_APPEND);
        catPoints = prefs.getInt("catPointsTotal", 0);
        totalPointsNum = totalPointsNum+catPoints;

 */