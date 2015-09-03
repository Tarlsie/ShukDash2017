package com.app.shukdash;

import java.text.SimpleDateFormat;

import android.os.CountDownTimer;
import android.widget.TextView;

public class ClockCountdown extends CountDownTimer {

	public ClockCountdown(long millisInFuture, long countDownInterval) {
		super(millisInFuture, countDownInterval);
		// TODO Auto-generated constructor stub
	}

	long milliSecs=720000;
	SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");	
	TextView timeDisplay;
	
	
	@Override
	public void onTick(long millisUntilFinished) {
		// TODO Auto-generated method stub
		timeDisplay.setText(timeFormat.format(millisUntilFinished));
	}

	@Override
	public void onFinish() {
		// TODO Auto-generated method stub
		timeDisplay.setText(timeFormat.format(0));
	}
	
	
}

/*timer = new Timer();
timerDisplay = (TextView) v.findViewById(R.id.TxtVDashTimeRemainingDisplay);
timer.scheduleAtFixedRate(new TimerTask() {
	
	@Override
	public void run() {
		//Calendar cal1;
		// TODO Auto-generated method stub
		handler.post(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");	
				String time = timeFormat.format(cal.getTime());
			//	Log.i("time", time);


				Calendar cal1 = Calendar.getInstance();
				cal.add(Calendar.HOUR, 2);
				String time1 =cal1.toString(); 
			//	Log.i("time1", time1);
				
			//	DateFormat d1 = new DateFormat();
				//d1.getTimeFormat(getActivity());
				
				
				//d1 = timeFormat.parse(cal1.toString());
				if(timeCounter!=0){
				cal1.add(Calendar.SECOND, -1);
				}
				String time3 = timeFormat.format(cal1.getTime());
				timerDisplay.setText(time +"  "+String.valueOf(timeCounter)+"  "+time3);
				timeCounter--;
			
				/*Log.i("time1 - 1sec", cal1.toString());
				Date time2= null;
				try {
					time2 = timeFormat.parse(cal1.toString());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Log.i("time2", time2.toString());
				
			}
		
		});
	}
}, 0, 1000);

// set the chronometer to count down for 2 hrs.
		// TextView timer =
		// (TextView)v.findViewById(R.id.TxtVDashTimeRemainingDisplay);
		// timer.setText("120:00");
		// 7200 secs
*/

/*SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
 * 
 * Date d1= null;
try {
	d1 = timeFormat.parse(timeresult);
} catch (ParseException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
//Date d1 = (Date)Time.valueOf(timeresult);
String timeResultFormat =timeFormat.format(d1);
*/
