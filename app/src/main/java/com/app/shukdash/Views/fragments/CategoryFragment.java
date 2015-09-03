package com.app.shukdash.Views.fragments;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.app.shukdash.Models.ShukDashDB;
import com.example.shukdash.R;
import com.app.shukdash.Views.TasksDisplay;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class CategoryFragment extends Fragment {

    public CategoryFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_category, container, false);

        ListView catLists = (ListView)v.findViewById(R.id.lstVShukDashCats);
        List<String> data = new ArrayList<String>();
        List<String> tasks = new ArrayList<String>();

        String[] catNames = getResources().getStringArray(R.array.catName);
        ShukDashDB db = new ShukDashDB(getActivity().getApplicationContext());
        List<String> catNameData = db.getCatNames();

        List<String> numOfTasksData = db.getNumOfTasks();
        List<String[]> isAnsweredData = db.getIsCompleted();
        Log.i("ShukDash", "CategoryFragment Display isAnsweredData "+ isAnsweredData.size());
        for (int i =0; i<isAnsweredData.size(); i++){

            String[] d= isAnsweredData.get(i);
            String d1 = d[0];
            String d2 = d[1];

            Log.i("ShukDash", "CategoryFragment Display isAnsweredData "+ d1 +" "+d2);
        }
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
        ShukCatListViewAdapter lvData = new ShukCatListViewAdapter(getActivity().getApplicationContext(), R.layout.shukdashcategorieslistview,
                catNameData  , numOfTasksData, inflater );
        Log.i("Parse Category Fragment", "adapter start");

        catLists.setAdapter(lvData);
        Log.i("Parse Category Fragment", "adapter start");

        catLists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent (getActivity().getApplicationContext(), TasksDisplay.class );
                i.putExtra("Category", position+1);
                startActivity(i);
            }
        });



        return v;
    }


    private class ShukCatListViewAdapter extends ArrayAdapter<String>{

        public Context c;
        public List<String> alName;
        public List<String> alTasks;
        LayoutInflater inflater;
        int layoutResourceID;

        public ShukCatListViewAdapter(Context context, int layoutResourceID,  List<String> catName, List<String> tasks, LayoutInflater inflater) {
            super(context,layoutResourceID);
            c = context;
            alName=catName;
            alTasks = tasks;
            this.inflater = inflater;
            this.layoutResourceID = layoutResourceID;
        }

        public int getCount() {
            return alName.size();
        }



        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView==null){
                convertView = inflater.inflate(R.layout.shukdashcategorieslistview, null);
            }
            Log.i("Parse ListArrayAdapter ", "position is " + String.valueOf(position));

            TextView txtVNames = (TextView) convertView.findViewById(R.id.txtVShukDashCatListCatName);

            txtVNames.setText(alName.get(position));
            ((TextView)convertView.findViewById(R.id.txtVShukDashCatListNumOfTasks)).setText(alTasks.get(position));

            return convertView;
        }



    }
}



/*


final ListView jobsTodayListView = (ListView) view.findViewById(R.id.jobsToDoListView);
        jobsListDB = new PestDBData(getActivity());
        Cursor jobsToday = jobsListDB.getDailyLogJobsList();

        final JobsDoneTodayCursorAdapter jobsAdapter = new JobsDoneTodayCursorAdapter(getActivity(),jobsToday, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);

        jobsTodayListView.setAdapter(jobsAdapter);

        jobsTodayListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) jobsTodayListView.getItemAtPosition(position);

                String startTime, address, address1, address2, address3 ,conditions,orderInstruct, serviceInstruct, phoneNum, serviceType, orderNum, locationAccNum;

                startTime = cursor.getString(cursor.getColumnIndex("appointment_time"));
                serviceType = cursor.getString(cursor.getColumnIndex("service_type"));
                address1 = cursor.getString(cursor.getColumnIndexOrThrow("place"));
                address2 = cursor.getString(cursor.getColumnIndexOrThrow("location1"));
                address3= cursor.getString(cursor.getColumnIndexOrThrow("service_location"));
                address= address1+"\n "+address2+"\n "+address3;
                Log.i("Tech details In Out address: ", address);
               // conditions = cursor.getString(cursor.getColumnIndexOrThrow("building_phone_number"));
                orderInstruct= cursor.getString(cursor.getColumnIndexOrThrow("order_instructions"));
                serviceInstruct= cursor.getString(cursor.getColumnIndexOrThrow("service_instructions"));
                phoneNum = cursor.getString(cursor.getColumnIndexOrThrow("building_phone_number"));
                orderNum = cursor.getString(cursor.getColumnIndexOrThrow("order_number"));
                locationAccNum = cursor.getString(cursor.getColumnIndexOrThrow("serviceLocAccNum"));

                //
                // Set Intent location dependant on the ServiceType.
                // then send all data about the appointment with the intent to the new activity
                //

                Intent pressListView;
                SharedPreferences saveWorkLog;


                if (serviceType.equalsIgnoreCase("KNOCK")) {
                    pressListView = new Intent(getActivity(), PestConsultLog.class);

                    saveWorkLog = getActivity().getSharedPreferences("KnockLastSaved",Context.MODE_PRIVATE );

               /* }

                else if(serviceType.equalsIgnoreCase("LIST")) {
                    pressListView = new Intent(getActivity(), PestConsultLog.class);
                    saveWorkLog = getActivity().getSharedPreferences("ListLastSaved", Context.MODE_PRIVATE);

                */
/*
}

        else if(serviceType.equalsIgnoreCase("BED BUGS")) {
        pressListView = new Intent(getActivity(), IndivCustomer.class);
        saveWorkLog = getActivity().getSharedPreferences("BedBugsLastSaved",Context.MODE_PRIVATE );

        }
        else {
        pressListView = new Intent(getActivity(), SpecialRequest.class);
        saveWorkLog = getActivity().getSharedPreferences("SpecialRequestLastSaved",Context.MODE_PRIVATE );
        }

        pressListView.putExtra("startTime", startTime);
        pressListView.putExtra("address1", address1);
        pressListView.putExtra("address2", address2);
        pressListView.putExtra("address3", address3);
        pressListView.putExtra("serviceType", serviceType);
        pressListView.putExtra("orderInstruct", orderInstruct);
        pressListView.putExtra("serviceInstruct", serviceInstruct);
        pressListView.putExtra("phoneNum", phoneNum);
        pressListView.putExtra("orderNum", orderNum);
        pressListView.putExtra("locationAccNum", locationAccNum);
        pressListView.putExtra("fromIntent", "TechDetails");

        SharedPreferences.Editor editor = saveWorkLog.edit();
        editor.putString("startTime", startTime);
        editor.putString("address1", address1);
        editor.putString("address2", address2);
        editor.putString("address3", address3);
        editor.putString("serviceType", serviceType);
        editor.putString("orderInstruct", orderInstruct);
        editor.putString("serviceInstruct", serviceInstruct);
        editor.putString("phoneNum", phoneNum);
        editor.putString("orderNum", orderNum);
        editor.putString("locationAccNum", locationAccNum);
        //editor.commit();
        editor.apply();
        startActivity(pressListView);
        }

        });



 */