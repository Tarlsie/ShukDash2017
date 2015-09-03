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

public class Dash_fragment_catPointsGR8 extends Fragment {
	static final int RESULT_FROM_CODE=2 ;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.dash_gr8layout, container,false);
		return v;
	}
	

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		OnClickListener fragmentGR8Click1 = (new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getActivity(), DashEntryActivity.class);
				i.putExtra("cat", 5);
				i.putExtra("task", 1);
				i.putExtra("points", 3);
				startActivityForResult(i, RESULT_FROM_CODE);
			}
		});
		
		RelativeLayout relHolderGR8_1 = (RelativeLayout) view.findViewById(R.id.relholderGR8_1);
		relHolderGR8_1.setOnClickListener(fragmentGR8Click1);
		
		OnClickListener fragmentGR8Click2 = (new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getActivity(), DashEntryActivity.class);
				i.putExtra("cat", 5);
				i.putExtra("task", 2);
				i.putExtra("points", 3);
				startActivityForResult(i, RESULT_FROM_CODE);
			}
		});
		
		RelativeLayout relHolderGR8_2 = (RelativeLayout) view.findViewById(R.id.relholderGR8_2);
		relHolderGR8_2.setOnClickListener(fragmentGR8Click2);
		
		OnClickListener fragmentGR8Click3 = (new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getActivity(), DashEntryActivity.class);
				i.putExtra("cat", 5);
				i.putExtra("task", 3);
				i.putExtra("points", 3);
				startActivityForResult(i, RESULT_FROM_CODE);
			}
		});
		
		RelativeLayout relHolderGR8_3 = (RelativeLayout) view.findViewById(R.id.relholderGR8_3);
		relHolderGR8_3.setOnClickListener(fragmentGR8Click3);
		
		OnClickListener fragmentGR8Click4 = (new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getActivity(), DashEntryActivity.class);
				i.putExtra("cat", 5);
				i.putExtra("task", 4);
				i.putExtra("points", 3);
				startActivityForResult(i, RESULT_FROM_CODE);
			}
		});
		
		RelativeLayout relHolderGR8_4 = (RelativeLayout) view.findViewById(R.id.relholderGR8_4);
		relHolderGR8_4.setOnClickListener(fragmentGR8Click4);
		
		OnClickListener fragmentGR8Click5 = (new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getActivity(), DashEntryActivity.class);
				i.putExtra("cat", 5);
				i.putExtra("task", 5);
				i.putExtra("points", 3);
				startActivityForResult(i, RESULT_FROM_CODE);
			}
		});
		
		RelativeLayout relHolderGR8_5 = (RelativeLayout) view.findViewById(R.id.relholderGR8_5);
		relHolderGR8_5.setOnClickListener(fragmentGR8Click5);
		
		OnClickListener fragmentGR8Click6 = (new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getActivity(), DashEntryActivity.class);
				i.putExtra("cat", 5);
				i.putExtra("task", 6);
				i.putExtra("points", 3);
				startActivityForResult(i, RESULT_FROM_CODE);
			}
		});
		
		RelativeLayout relHolderGR8_6 = (RelativeLayout) view.findViewById(R.id.relholderGR8_6);
		relHolderGR8_6.setOnClickListener(fragmentGR8Click6);
		
		OnClickListener fragmentGR8Click7 = (new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getActivity(), DashEntryActivity.class);
				i.putExtra("cat", 5);
				i.putExtra("task", 7);
				i.putExtra("points", 3);
				startActivityForResult(i, RESULT_FROM_CODE);
			}
		});
		
		RelativeLayout relHolderGR8_7 = (RelativeLayout) view.findViewById(R.id.relholderGR8_7);
		relHolderGR8_7.setOnClickListener(fragmentGR8Click7);
		
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
		String name = "ImgVdashCardCompletedTickGR8_"+j;
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
