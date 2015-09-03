package com.app.shukdash;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shukdash.R;

public class Fragment_Dash extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
	
		View fragDashInstruc = inflater.inflate(R.layout.fragment_dash, container, false);
		return fragDashInstruc;
	}

	
}
