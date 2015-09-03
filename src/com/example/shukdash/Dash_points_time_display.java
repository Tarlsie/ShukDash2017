package com.example.shukdash;

import android.app.Fragment;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class Dash_points_time_display extends Fragment {
	
	int timeCounter = 7199;
	TextView time2;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.dash_points_time_display, container,
				false);

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
					
		return v;
	}

}
