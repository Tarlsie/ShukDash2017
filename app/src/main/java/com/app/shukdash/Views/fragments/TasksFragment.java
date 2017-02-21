package com.app.shukdash.Views.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.app.shukdash.Models.CatDetailsData;
import com.app.shukdash.Models.ShukDashDB;
import com.example.shukdash.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class TasksFragment extends ListFragment {

    public TasksFragment() {
    }
    List<CatDetailsData> dataFromDB;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_tasks, container, false);
        ListView fragmentTasksListV = (ListView)v.findViewById(R.id.TasksFragmentListView);
        int num=1;
        Intent i = getActivity().getIntent();
        num = i.getIntExtra("Category",1); //changed to 1 from 0 to ensure data is supplied if intent does exist

       //get details for specific category from db
        ShukDashDB db = new ShukDashDB(getActivity());
        Cursor c = db.getTaskListDetails(num);

        dataFromDB = new ArrayList<>();
        while (c.moveToNext()){

            CatDetailsData data = new CatDetailsData();
            data.setCatLength(c.getInt(2));
            data.setNumTasks(c.getInt(0));
            //data.setCategoryName(c.getString(1));
            data.setDescription(c.getString(3));
            data.setPoints(c.getInt(4));
            //data.setAnswer(c.getString(5));
           // data.setIsAnswered(c.getInt(6));
           // data.setIsTextAnswer(c.getInt(7));
           // data.setIsPhotoAnswer(c.getInt(8));

            dataFromDB.add(data);

        }

        TasksArrayAdapter taskslistAdapter = new TasksArrayAdapter(getContext(), R.layout.task_description,dataFromDB );

        fragmentTasksListV.setAdapter(taskslistAdapter);

        return v;
    }

    private class TasksArrayAdapter extends ArrayAdapter<CatDetailsData> {

        Context context;
        public TasksArrayAdapter(Context context, int resource, List<CatDetailsData> data) {
            super(context, resource, data);
            this.context=context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

           // LayoutInflater inflater = (LayoutInflater) context
            return super.getView(position, convertView, parent);
        }

        @Override
        public long getItemId(int position) {
            return super.getItemId(position);
        }
    }
}
