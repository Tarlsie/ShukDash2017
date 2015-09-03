package com.app.shukdash.Views.fragments;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shukdash.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class TasksFragment extends ListFragment {

    public TasksFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tasks, container, false);

        Intent i = getActivity().getIntent();
        int num = i.getIntExtra("Category",0);


        return v;
    }
}
