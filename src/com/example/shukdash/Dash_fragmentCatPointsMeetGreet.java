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

public class Dash_fragmentCatPointsMeetGreet extends Fragment {
	static final int RESULT_FROM_CODE=2 ;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

			View viewMeetGreet = inflater.inflate(R.layout.dash_fragment_cat_points_meet_greet, container, false);
		
		return viewMeetGreet;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		
		OnClickListener fragmentMGClick1 = (new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getActivity(), DashEntryActivity.class);
				i.putExtra("cat", 3);
				i.putExtra("task", 1);
				i.putExtra("points", 1);
				startActivityForResult(i, RESULT_FROM_CODE);
			}
		});

		RelativeLayout relMGHolder1 = (RelativeLayout) view.findViewById(R.id.relholderMG1);
		relMGHolder1.setOnClickListener(fragmentMGClick1);
		
		OnClickListener fragmentMGClick2 = (new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getActivity(), DashEntryActivity.class);
				i.putExtra("cat", 3);
				i.putExtra("task", 2);
				i.putExtra("points", 1);
				startActivityForResult(i, RESULT_FROM_CODE);
			}
		});

		RelativeLayout relMGHolder2 = (RelativeLayout) view.findViewById(R.id.relholderMG2);
		relMGHolder2.setOnClickListener(fragmentMGClick2);
		
		
		OnClickListener fragmentMGClick3 = (new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getActivity(), DashEntryActivity.class);
				i.putExtra("cat", 3);
				i.putExtra("task", 3);
				i.putExtra("points", 1);
				startActivityForResult(i, RESULT_FROM_CODE);
			}
		});

		RelativeLayout relMGHolder3 = (RelativeLayout) view.findViewById(R.id.relholderMG3);
		relMGHolder3.setOnClickListener(fragmentMGClick3);
		
		
		OnClickListener fragmentMGClick4 = (new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getActivity(), DashEntryActivity.class);
				i.putExtra("cat", 3);
				i.putExtra("task", 4);
				i.putExtra("points", 1);
				startActivityForResult(i, RESULT_FROM_CODE);
			}
		});

		RelativeLayout relMGHolder4 = (RelativeLayout) view.findViewById(R.id.relholderMG4);
		relMGHolder4.setOnClickListener(fragmentMGClick4);
		
		
		OnClickListener fragmentMGClick5 = (new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getActivity(), DashEntryActivity.class);
				i.putExtra("cat", 3);
				i.putExtra("task", 5);
				i.putExtra("points", 1);
				startActivityForResult(i, RESULT_FROM_CODE);
			}
		});

		RelativeLayout relMGHolder5 = (RelativeLayout) view.findViewById(R.id.relholderMG5);
		relMGHolder5.setOnClickListener(fragmentMGClick5);
		
		
	
	
	OnClickListener fragmentMGClick6 = (new OnClickListener() {

		@Override
		public void onClick(View v) {

			Intent i = new Intent(getActivity(), DashEntryActivity.class);
			i.putExtra("cat", 3);
			i.putExtra("task", 1);
			i.putExtra("points", 1);
			startActivityForResult(i, RESULT_FROM_CODE);
		}
	});

	RelativeLayout relMGHolder6 = (RelativeLayout) view.findViewById(R.id.relholderMG6);
	relMGHolder6.setOnClickListener(fragmentMGClick6);
	
	OnClickListener fragmentMGClick7 = (new OnClickListener() {

		@Override
		public void onClick(View v) {

			Intent i = new Intent(getActivity(), DashEntryActivity.class);
			i.putExtra("cat", 3);
			i.putExtra("task", 1);
			i.putExtra("points", 1);
			startActivityForResult(i, RESULT_FROM_CODE);
		}
	});

	RelativeLayout relMGHolder7 = (RelativeLayout) view.findViewById(R.id.relholderMG7);
	relMGHolder7.setOnClickListener(fragmentMGClick7);
	
	OnClickListener fragmentMGClick8 = (new OnClickListener() {

		@Override
		public void onClick(View v) {

			Intent i = new Intent(getActivity(), DashEntryActivity.class);
			i.putExtra("cat", 3);
			i.putExtra("task", 8);
			i.putExtra("points", 1);
			startActivityForResult(i, RESULT_FROM_CODE);
		}
	});

	RelativeLayout relMGHolder8 = (RelativeLayout) view.findViewById(R.id.relholderMG8);
	relMGHolder8.setOnClickListener(fragmentMGClick8);
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
		String name = "ImgVdashCardCompletedTickMeetGreet"+j;
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
