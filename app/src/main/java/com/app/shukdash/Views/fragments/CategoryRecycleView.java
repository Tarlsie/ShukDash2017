package com.app.shukdash.Views.fragments;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.app.shukdash.Models.CategoryRecyclerAdapter;
import com.app.shukdash.Models.ShukDashDB;
import com.app.shukdash.Views.TasksDisplay;
import com.example.shukdash.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielT on 30/10/2015.
 */


//add this to he fragment

public class CategoryRecycleView extends Fragment {

    public CategoryRecycleView () {
    }

    public ShukDashDB db ;

    public List<String> catNameData;
    public List<String> numOfTasksData;
    public List<String[]> isAnsweredData ;
    public RecyclerView catRCV;
    public CategoryRecyclerAdapter recyclData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_category, container, false);

        catRCV = (RecyclerView)v.findViewById(R.id.recyclerVShukDashCats);
        List<String> data = new ArrayList<String>();
        List<String> tasks = new ArrayList<String>();
        String[] catNames = getResources().getStringArray(R.array.catName);

        db = new ShukDashDB(getActivity().getApplicationContext());

        catNameData = db.getCatNames();
        numOfTasksData = db.getNumOfTasks();
        isAnsweredData = db.getIsCompletedOrderedByCat();

        Log.i("ShukDash", "CategoryFragment Display isAnsweredData " + isAnsweredData.size());
        for (int i =0; i<isAnsweredData.size(); i++){

            String[] d= isAnsweredData.get(i);
            String d1 = d[0];
            String d2 = d[1];

            Log.i("ShukDash", "CategoryFragment Display isAnsweredData "+ d1 +" "+d2);
        }

        int[] dataReturned = exportTasksToInts(numOfTasksData);
        /*Log.i("Parse Category Fragment", "catNames string[] length " + String.valueOf(catNames.length));
        for (int i =0; i<catNames.length; i++){

            String d = catNames[i].toString();
            Log.i("Parse Category Fragment", "catNames string[] "+d);
            data.add(catNames[i]);
        }

        String[] numTasks = getResources().getStringArray(R.array.NumOfTasks);
        Log.i("Parse Category Fragment", "tasks string[] length " + String.valueOf(numTasks.length));
        for(int i =0; i<numTasks.length; i++){
            String t = numTasks[i].toString();
            Log.i("Parse Category Fragment", "tasks string[] "+t);
            tasks.add(t);
        }
        */

        Log.i("Parse Category Fragment", "adapter start");
        recyclData = new CategoryRecyclerAdapter(getActivity().getApplicationContext(),
                catNameData  , numOfTasksData,isAnsweredData,dataReturned);
        Log.i("Parse Category Fragment", "adapter start");

        catRCV.setAdapter(recyclData);
        Log.i("Parse Category Fragment", "adapter start");

        /*
        catRCV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getActivity().getApplicationContext(), TasksDisplay.class);
                i.putExtra("Category", position + 1);
                startActivity(i);
            }
        });

*/

        return v;
    }

    public int[] exportTasksToInts (List<String> data){

        int[] tasksdata = new int[6];

        for (int i =0; i<data.size(); i++){

            tasksdata[i] = Integer.valueOf(data.get(i));
            Log.i("ShukDash CategoryFragment", "function exporttasks data : position: "+ i + " value "+tasksdata[i]);

        }



        return tasksdata;

    }
}
