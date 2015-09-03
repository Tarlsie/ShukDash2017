package com.example.shukdash;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;


public class Dash extends Activity {
	
	FragmentTransaction ft ;
	FragmentManager fm ;
	Dash_fragmentCatPoints catpointsFragment;
	Dash_fragmentCatPointsSD catpointsFragmentSD1;
	Dash_fragmentCatPointsMeetGreet mg1;
	Dash_fragment_cat_points_shuktionary shuk1;
	Dash_fragment_catPointsGR8 gr8_1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dash);
		catpointsFragment = new Dash_fragmentCatPoints();
		catpointsFragmentSD1 = new Dash_fragmentCatPointsSD();
		mg1 = new Dash_fragmentCatPointsMeetGreet();
		shuk1 = new Dash_fragment_cat_points_shuktionary();
		gr8_1 = new Dash_fragment_catPointsGR8();
		
		fm = getFragmentManager();
		ft = fm.beginTransaction();
		
		Dash_points_time_display dashpointsTime = new Dash_points_time_display();
		ft.add(R.id.DashGridLayScores, dashpointsTime);
		ft.add(R.id.FragmentPerforming,catpointsFragment);
		ft.addToBackStack(null);
		
			
		TextView catPoints = (TextView)findViewById(R.id.dashCardCatgeory);
		TextView SD1= (TextView)findViewById(R.id.dashCardCatgeorySD);
		TextView MG1= (TextView)findViewById(R.id.dashCardCatgeoryMeetGreet);
		TextView Shuktionary= (TextView)findViewById(R.id.dashCardCatgeoryShuk);
		TextView GR8= (TextView)findViewById(R.id.dashCardCatgeoryGR8);
		
		//onclick Performing Arts
		OnClickListener catPointsOnClick = new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				Fragment fragDis = getFragmentManager().findFragmentById(R.id.FragmentPerforming);
				if (fragDis!=null)
				{
				
			
				}
				FragmentTransaction fragmenttrans = getFragmentManager().beginTransaction(); 
			
				if (catpointsFragmentSD1!=null){
					//fragmenttrans.remove(gr8_1);
					//fragmenttrans.remove(shuk1);
				//	fragmenttrans.remove(mg1);
					fragmenttrans.remove(catpointsFragmentSD1);
						fragmenttrans.add(R.id.FragmentPerforming, new Dash_fragmentCatPoints() );
						//fragmenttrans.addToBackStack(null);
						fragmenttrans.commit();
				}
					else if (mg1!=null){
						fragmenttrans.remove(mg1);
						fragmenttrans.add(R.id.FragmentPerforming, new Dash_fragmentCatPoints() );
					//	fragmenttrans.addToBackStack(null);
						
					}
					else if (shuk1!=null){
						fragmenttrans.remove(shuk1);
						fragmenttrans.add(R.id.FragmentPerforming, new Dash_fragmentCatPoints() );
					//	fragmenttrans.addToBackStack(null);
						
					}
					else if (gr8_1!=null){
						fragmenttrans.remove(gr8_1);
						fragmenttrans.add(R.id.FragmentPerforming, new Dash_fragmentCatPoints() );
					//	fragmenttrans.addToBackStack(null);
					}
					
				}
					
		};
		
	
		catPoints.setOnClickListener(catPointsOnClick);
		
		//onclick ShukDash special
		OnClickListener SD1OnClick = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentTransaction fragTransSD = getFragmentManager().beginTransaction();
				if (catpointsFragment!=null){
						
					ft.remove(catpointsFragment);
				}
				 if (mg1!=null){
					 ft.remove(mg1);
				}
				 if (shuk1!=null){
					 ft.remove(shuk1);
				}
				 if (gr8_1!=null){
					 ft.remove(gr8_1);
					
				}
				
				 fragTransSD.add(R.id.FragmentPerforming, new Dash_fragmentCatPointsSD() );
				 fragTransSD.addToBackStack(null);
						
			}
		};
		
		SD1.setOnClickListener(SD1OnClick);
		
		//onclick meet greet
				OnClickListener mgOnClick = new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						FragmentTransaction fragTransMG = getFragmentManager().beginTransaction();
						
						if (catpointsFragment!=null){
							
							fragTransMG.remove(catpointsFragment);
						}
						
						 if (catpointsFragmentSD1!=null){
								
							 fragTransMG.remove(catpointsFragmentSD1);
						}
						
						 if (shuk1!=null){
							 fragTransMG.remove(shuk1);
						}
						 if (gr8_1!=null){
							 fragTransMG.remove(gr8_1);
							
						}
							
					fragTransMG.add(R.id.FragmentPerforming, new Dash_fragmentCatPointsMeetGreet() );
					fragTransMG.addToBackStack(null);
						fragTransMG.commit();
					}
				};
				
				MG1.setOnClickListener(mgOnClick);
				//onclick shuktionary
				OnClickListener shukOnClick = new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						FragmentTransaction fragTransMG = getFragmentManager().beginTransaction();
						
						
						if (catpointsFragment!=null){
							
							fragTransMG.remove(catpointsFragment);
						}
						
						if (catpointsFragmentSD1!=null){
								
							fragTransMG.remove(catpointsFragmentSD1);
						}
						if (mg1!=null){
							fragTransMG.remove(mg1);
						}
						
						 if (gr8_1!=null){
							 fragTransMG.remove(gr8_1);
							
						}
							
						
						 fragTransMG.add(R.id.FragmentPerforming, new Dash_fragment_cat_points_shuktionary() );
						 fragTransMG.addToBackStack(null);
						 fragTransMG.commit();
												
					}
				};
				
				Shuktionary.setOnClickListener(shukOnClick);
				
				//onclick gr8
					OnClickListener gr8OnClick = new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						
						FragmentTransaction fragTransShuk = getFragmentManager().beginTransaction();
						
						if (catpointsFragment!=null){
							
							fragTransShuk.remove(catpointsFragment);
						}
						
						if (catpointsFragmentSD1!=null){
								
							fragTransShuk.remove(catpointsFragmentSD1);
						}
						if (mg1!=null){
							fragTransShuk.remove(mg1);
						}
						
						if (shuk1!=null){
							fragTransShuk.remove(shuk1);
						}
							
						fragTransShuk.add(R.id.FragmentPerforming, new Dash_fragment_catPointsGR8() );
						fragTransShuk.addToBackStack(null);
						fragTransShuk.commit();
												
					}
				};
				
				GR8.setOnClickListener(gr8OnClick);
				ft.commit();
		}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
        
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dash, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_dash, container,
					false);
			return rootView;
		}
	}
}


/*
 * 
 * 
		
		
				*/
