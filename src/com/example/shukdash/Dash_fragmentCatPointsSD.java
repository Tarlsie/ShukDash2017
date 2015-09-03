package com.example.shukdash;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Dash_fragmentCatPointsSD extends Fragment {
	static final int RESULT_FROM_CODE=2 ;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View catPointsSD = inflater.inflate(R.layout.dash_fragment_cat_points_sd, container,false);
		
		return catPointsSD;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		
		super.onViewCreated(view, savedInstanceState);
	
		OnClickListener fragmentSDClick1 = (new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				Intent i = new Intent(getActivity(), DashEntryActivity.class);
				i.putExtra("cat", 2);
				i.putExtra("task", 1);
				i.putExtra("points", 5);
				startActivityForResult(i, RESULT_FROM_CODE);
			}
		});

		RelativeLayout relSDHolder1 = (RelativeLayout) view.findViewById(R.id.relholderSD1);
		relSDHolder1.setOnClickListener(fragmentSDClick1);
		
		OnClickListener fragmentSDClick2 = (new OnClickListener() {

			@Override
			public void onClick(View v) {
				//	DashPassData data = new DashPassData(4, 1, 2);
				Intent i = new Intent(getActivity(), DashEntryActivity.class);
				i.putExtra("cat", 2);
				i.putExtra("task", 2);
				i.putExtra("points", 5);
				startActivityForResult(i, RESULT_FROM_CODE);
			}
		});

		RelativeLayout relSDHolder2 = (RelativeLayout) view.findViewById(R.id.relholderSD2);
		relSDHolder2.setOnClickListener(fragmentSDClick2);
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		getActivity();
		
		Bundle results = data.getExtras();
		String tasknum = results.getString("task");
		Log.e("data in onactivityresult", tasknum);
		
		String resultTask= data.getStringExtra("task");
		//int i=Integer.parseInt(tasknum);
		int j=Integer.parseInt(resultTask);
		String name = "ImgVdashCardCompletedTickSD"+j;
		int id = getResources().getIdentifier(name, "id", "com.example.shukdash");
		Log.e("Return resultCode:", String.valueOf(resultCode));
		
		if (requestCode==RESULT_FROM_CODE){
	
		if(resultCode ==Activity.RESULT_CANCELED){
			Log.i("Return ActivityForREsult", data.getStringExtra("Message"));
			Log.i("Return ActivityForREsult", data.getStringExtra("cat")+", "+data.getStringExtra("task")+", "+data.getStringExtra("points"));
			ImageView tick = (ImageView) getActivity().findViewById(id);
			tick.setVisibility(View.INVISIBLE);
		}
		
		else if(resultCode == Activity.RESULT_OK){
			//set tick to appear
			// add save to db 
			//use shared preferences to save data
			
			Log.i("Return ActivityForResult", data.getStringExtra("Message"));
			Log.i("Return ActivityForResult", data.getStringExtra("cat")+", "+data.getStringExtra("task")+", "+data.getStringExtra("points"));
			ImageView tick = (ImageView) getActivity().findViewById(id);
			tick.setVisibility(View.VISIBLE);
		}
		}
	}
	
}
	
