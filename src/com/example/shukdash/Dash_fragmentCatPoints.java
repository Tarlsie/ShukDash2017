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

public class Dash_fragmentCatPoints extends Fragment {
	static final int RESULT_FROM_CODE=2 ;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View catPoints = inflater.inflate(
				R.layout.activity_dash_fragment_cat_points, container, false);

		return catPoints;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

		OnClickListener fragmentClick = (new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getActivity(), DashEntryActivity.class);
				i.putExtra("cat", 1);
				i.putExtra("task", 1);
				i.putExtra("points", 4);
				startActivityForResult(i, RESULT_FROM_CODE);
			}
		});

		RelativeLayout relHolder1 = (RelativeLayout) view.findViewById(R.id.relholderPA1);
		relHolder1.setOnClickListener(fragmentClick);

		OnClickListener fragmentClick2 = (new OnClickListener() {

			@Override
			public void onClick(View v) {
				//	DashPassData data = new DashPassData(4, 1, 2);
				Intent i = new Intent(getActivity(), DashEntryActivity.class);
				i.putExtra("cat", 1);
				i.putExtra("task", 2);
				i.putExtra("points", 4);
				startActivityForResult(i, RESULT_FROM_CODE);
			}
		});

		RelativeLayout relHolder2 = (RelativeLayout) view.findViewById(R.id.relholderPA2);
		relHolder2.setOnClickListener(fragmentClick2);
		
		OnClickListener fragmentClick3 = (new OnClickListener() {

			@Override
			public void onClick(View v) {
				//	DashPassData data = new DashPassData(4, 1, 2);
				Intent i = new Intent(getActivity(), DashEntryActivity.class);
				i.putExtra("cat", 1);
				i.putExtra("task", 3);
				i.putExtra("points", 4);
				startActivityForResult(i, RESULT_FROM_CODE);
			}
		});

		RelativeLayout relHolder3 = (RelativeLayout) view.findViewById(R.id.relholderPA3);
		relHolder3.setOnClickListener(fragmentClick3);
		
		
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
		String name = "ImgVdashCardCompletedTickPA"+j;
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
