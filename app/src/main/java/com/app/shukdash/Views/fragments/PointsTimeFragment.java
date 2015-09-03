package com.app.shukdash.Views.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shukdash.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class PointsTimeFragment extends Fragment {

    public PointsTimeFragment() {
    }
    int timeCounter = 7199;
    TextView time2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_points_time, container, false);



        TextView totalPoints = (TextView)v.findViewById(R.id.txtVTotalPoints);
        int totalPointsNum = 0;
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

        totalPoints.setText("TOtal Points = "+totalPointsNum);

        TextView taskComp = (TextView) v.findViewById(R.id.TxtVDashTasksCompletedDisplay);
        taskComp.setText("24");

        TextView taskUnComp = (TextView) v.findViewById(R.id.TxtVDashTasksRemainingDisplay);
        taskUnComp.setText("0");

        TextView points = (TextView) v.findViewById(R.id.TxtVDashTotalPotentialPointsDisplay);
        points.setText("0");


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
}
