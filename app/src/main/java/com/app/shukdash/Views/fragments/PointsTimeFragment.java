package com.app.shukdash.Views.fragments;

import android.app.Activity;
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
import android.widget.Toast;

import com.app.shukdash.Models.ShukDashDB;
import com.app.shukdash.Presenters.OnSaveGameUpdateData;
import com.app.shukdash.Presenters.onSaveUpdateFragment;
import com.example.shukdash.R;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * A placeholder fragment containing a simple view.
 */
public class PointsTimeFragment extends Fragment implements Observer, onSaveUpdateFragment{
    ShukDashDB db;

    public PointsTimeFragment() {

    }
    private Activity mActivity;
    OnSaveGameUpdateData dataValues ;
    int timeCounter = 7199;
    TextView time2;
    int totalPointsNum, totalTasksAnswered, tasksRemaining;
    TextView points, taskComp, taskUnComp;
    List<Integer> getAllIsCompleted;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dataValues = new OnSaveGameUpdateData();
        // PointsTimeFragment ptf = new PointsTimeFragment();
        dataValues.addObserver(this);
        Log.i("ShukDash Observer", " POintsTimeFragment Constructor" );
        View v = inflater.inflate(R.layout.fragment_points_time, container, false);

        //Bundle data = getArguments();
        //data.get("");

        db = new ShukDashDB(getActivity());

       // ShukDashDB db = new ShukDashDB(getActivity());

      //  TextView totalPoints = (TextView)v.findViewById(R.id.txtVTotalPoints);
        totalPointsNum = 0;
        totalTasksAnswered = 0;
        tasksRemaining=0;

        totalPointsNum = db.getAllPointsTotal();
       // Log.i("ShukDash", "PointsTimeFragment get all points from DB: "+totalPointsNum);
        //totalPoints.setText("Total Points = " + totalPointsNum);

        getAllIsCompleted = db.getAllIsCompleted();
        for(int i =0; i<getAllIsCompleted.size();i++){

            int a = getAllIsCompleted.get(i);
                if (a==1){
                    totalTasksAnswered = totalTasksAnswered+a;
                }

        }

        tasksRemaining = getAllIsCompleted.size()-totalTasksAnswered;

         taskComp = (TextView) v.findViewById(R.id.TxtVDashTasksCompletedDisplay);
        taskComp.setText(String.valueOf(totalTasksAnswered));
        taskComp.setTextColor(Color.BLACK);

         taskUnComp = (TextView) v.findViewById(R.id.TxtVDashTasksRemainingDisplay);
        taskUnComp.setText(String.valueOf(tasksRemaining));
        taskUnComp.setTextColor(Color.BLACK);

         points = (TextView) v.findViewById(R.id.TxtVDashTotalPotentialPointsDisplay);
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

    public void updatePointsDisplay(Object pointsData){

        int newPoints = (Integer)pointsData;
        int PointsTotal = totalPointsNum+newPoints;

        points.setText(String.valueOf(PointsTotal));
    }

    public  void updateTasksDisplay (Object tasksData){
            int tasksCompleted = (Integer)tasksData;

        if (tasksCompleted==1){

            int newTasksCompleted = totalTasksAnswered+ tasksCompleted;
            taskComp.setText(String.valueOf(newTasksCompleted));

            int newTasksRemaining = tasksRemaining-1;
            taskUnComp.setText(String.valueOf(newTasksRemaining));

        }

    }

///////////
    //use the bundle for storing this data NOT a shared preference
    ////////////
    @Override
    public void onResume() {
        super.onResume();
        totalPointsNum = 0;
        totalTasksAnswered = 0;
        tasksRemaining=0;

        SharedPreferences getTimerData = this.getActivity().getSharedPreferences("timerDetails", Context.MODE_PRIVATE);
        timeCounter = getTimerData.getInt("timeCounter", 7199);

        db = new ShukDashDB(getActivity());
        totalPointsNum = db.getAllPointsTotal();
        points.setText(String.valueOf(totalPointsNum));

        getAllIsCompleted = db.getAllIsCompleted();

        for(int i =0; i<getAllIsCompleted.size();i++){

            int a = getAllIsCompleted.get(i);
            if (a==1){
                totalTasksAnswered = totalTasksAnswered+a;
            }

        }

        tasksRemaining = getAllIsCompleted.size()-totalTasksAnswered;
        taskUnComp.setText(String.valueOf(tasksRemaining));
        taskComp.setText(String.valueOf(totalTasksAnswered));
    }

    @Override
    public void onPause() {

        super.onPause();

        Log.i("ShukDash PointsTimeFragment", "onPause");
        SharedPreferences saveTimerPref = this.getActivity().getSharedPreferences("timerDetails", Context.MODE_PRIVATE);
        Log.i("ShukDash PointsTimeFragment", "onPause set shared pref");
        SharedPreferences.Editor edit = saveTimerPref.edit();
        Log.i("ShukDash PointsTimeFragment", "shared pref editor");
        if(timeCounter<0) {
            edit.clear();
        }
        else {
            edit.putInt("timeCounter", timeCounter);
        }
        Log.i("ShukDash PointsTimeFrag", "shared pref commit");
        edit.commit();
        Log.i("ShukDash PointsTimeFragment", "shared pref complete");

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }



    @Override
    public void update(Observable observable, Object data) {

        Log.i("ShukDash Observer ", "Update data = " + data);
        Toast.makeText(getActivity(),"Update data = "+data.toString(), Toast.LENGTH_LONG).show();
        if(observable.equals("points")){
            updatePointsDisplay(data);
        }

        else if(observable.equals("tasks")){
            updateTasksDisplay(data);
        }
    }

    @Override
    public void updateFragment() {
        onPause();
        onResume();
    }
}


