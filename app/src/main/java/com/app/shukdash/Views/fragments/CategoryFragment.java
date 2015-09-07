package com.app.shukdash.Views.fragments;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
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
        List<String[]> isAnsweredData = db.getIsCompletedOrderedByCat();

        Log.i("ShukDash", "CategoryFragment Display isAnsweredData "+ isAnsweredData.size());
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
        ShukCatListViewAdapter lvData = new ShukCatListViewAdapter(getActivity().getApplicationContext(), R.layout.shukdashcategorieslistview,
                catNameData  , numOfTasksData,isAnsweredData,dataReturned, inflater );
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
        List<String[]> isAnsweredData;
        int[] dataReturned;
        LayoutInflater inflater;
        int layoutResourceID;

        public ShukCatListViewAdapter(Context context, int layoutResourceID,  List<String> catName,
                                      List<String> tasks, List<String[]> isAnsweredData,int[] dataReturned, LayoutInflater inflater) {
            super(context,layoutResourceID);
            c = context;
            alName=catName;
            alTasks = tasks;
            this.inflater = inflater;
            this.layoutResourceID = layoutResourceID;
            this.isAnsweredData = isAnsweredData;
            this.dataReturned = dataReturned;
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
           // Log.i("Parse ListArrayAdapter ", "position is " + String.valueOf(position));

            TextView txtVNames = (TextView) convertView.findViewById(R.id.txtVShukDashCatListCatName);

            txtVNames.setText(alName.get(position));
            ((TextView)convertView.findViewById(R.id.txtVShukDashCatListNumOfTasks)).setText(alTasks.get(position)+" Tasks");

            TextView txtVtasksToDo = (TextView)convertView.findViewById(R.id.txtVShukDashCatListTasksToDoNum);
            TextView txtVtasksDone = (TextView)convertView.findViewById(R.id.txtVShukDashCatListTasksCompletedNum);

//could also use alTasks and convert to ints and use this

//maybe display the number that are completed and the number still to be done in this category  To Do : ? Done: ? for the moment
//this section should display a check box for each mission within each category
// in the listview this should be updated as each task is completed

            // check if this type of update will be possible!!

            /*
            THis following section coudl also be performed in the OnCreateView() above.
            There the program could work out each of the numbers for done and todo and add them to an array list
            which is then used to give values to the textview boxes in this getview() function
             */

            int counterToDoT1=0;
            int counterDoneT1=0;
            int counterToDoT2=0;
            int counterDoneT2=0;
            int counterToDoT3=0;
            int counterDoneT3=0;
            int counterToDoT4=0;
            int counterDoneT4=0;
            int counterToDoT5=0;
            int counterDoneT5=0;
            int counterToDoT6=0;
            int counterDoneT6=0;

            for (int i =0; i<isAnsweredData.size();i++){

              //  Log.i("Shukdash categoryfrag", "int i = "+i);
                if (i<3)
                {

                    int j = Integer.valueOf(isAnsweredData.get(i)[1]);
                    Log.i("Shukdash categoryfrag", "int i = "+i +" "+" int j = "+j);

                    if (j == 0)
                    {
                       Log.i("Shukdash categoryfrag", "if j=0 "+counterToDoT1);
                        counterToDoT1++;
                       Log.i("Shukdash categoryfrag", "if j=0 "+counterToDoT1);
                    }
                    counterDoneT1 =3-counterToDoT1;
                    Log.i("Shukdash categoryfrag", "int counterDoneT1 = "+counterDoneT1 +" "+" int counterToDoT1 = "+counterToDoT1);
                }


                else if (i>2 && i<=5)
                {

                    int j = Integer.valueOf(isAnsweredData.get(i)[1]);
                  //  Log.i("Shukdash categoryfrag", "int i = "+i +" "+" int j = "+j);

                    if (j == 0)
                    {
                     //   Log.i("Shukdash categoryfrag", "if j=0");
                        counterToDoT2++;
                    }
                    counterDoneT2 =3-counterToDoT2;
                   // Log.i("Shukdash categoryfrag", "int counterDoneT2 = "+counterDoneT2 +" "+" int counterToDoT2 = "+counterToDoT2);
                }

                else if (i>5 && i<=14)
                {

                    int j = Integer.valueOf(isAnsweredData.get(i)[1]);
                   // Log.i("Shukdash categoryfrag", "int i = "+i +" "+" int j = "+j);

                    if (j == 0)
                    {
                       // Log.i("Shukdash categoryfrag", "if j=0");
                        counterToDoT3++;
                    }
                    counterDoneT3 =9-counterToDoT3;
                  //  Log.i("Shukdash categoryfrag", "int counterDoneT3 = "+counterDoneT3 +" "+" int counterToDoT3 = "+counterToDoT3);
                }

                else if (i>14 && i<=18)
                {

                    int j = Integer.valueOf(isAnsweredData.get(i)[1]);
                  //  Log.i("Shukdash categoryfrag", "int i = "+i +" "+" int j = "+j);

                    if (j == 0)
                    {
                    //    Log.i("Shukdash categoryfrag", "if j=0");
                        counterToDoT4++;
                    }
                    counterDoneT4 =4-counterToDoT4;
                  //  Log.i("Shukdash categoryfrag", "int counterDoneT4 = "+counterDoneT4 +" "+" int counterToDoT4 = "+counterToDoT4);
                }

                else if (i>18 && i<=28)
                {

                    int j = Integer.valueOf(isAnsweredData.get(i)[1]);
                 //   Log.i("Shukdash categoryfrag", "int i = "+i +" "+" int j = "+j);

                    if (j == 0)
                    {
                   //     Log.i("Shukdash categoryfrag", "if j=0");
                        counterToDoT5++;
                    }
                    counterDoneT5 =10-counterToDoT5;
                   // Log.i("Shukdash categoryfrag", "int counterDoneT5 = "+counterDoneT5 +" "+" int counterToDoT5 = "+counterToDoT5);
                }

                else if (i>28 && i<=33)
                {

                    int j = Integer.valueOf(isAnsweredData.get(i)[1]);
                   // Log.i("Shukdash categoryfrag", "int i = "+i +" "+" int j = "+j);

                    if (j == 0)
                    {
                   //     Log.i("Shukdash categoryfrag", "if j=0");
                        counterToDoT6++;
                    }
                    counterDoneT6 =5-counterToDoT6;
                  //  Log.i("Shukdash categoryfrag", "int counterDoneT6 = "+counterDoneT6 +" "+" int counterToDoT6 = "+counterToDoT6);
                }


            }


            switch (position){
                case 0:
                    Log.i("Shukdash categoryfrag", "switch counterDoneT1 = "+counterDoneT1 +" "+" int counterToDoT1 = "+counterToDoT1);
                    txtVtasksToDo.setText(String.valueOf(counterToDoT1));
                    txtVtasksDone.setText(String.valueOf(counterDoneT1));
                    break;
                case 1:
                    txtVtasksToDo.setText(String.valueOf(counterToDoT2));
                    txtVtasksDone.setText(String.valueOf(counterDoneT2));
                    break;
                case 2:
                    txtVtasksToDo.setText(String.valueOf(counterToDoT3));
                    txtVtasksDone.setText(String.valueOf(counterDoneT3));
                    break;
                case 3:
                    txtVtasksToDo.setText(String.valueOf(counterToDoT4));
                    txtVtasksDone.setText(String.valueOf(counterDoneT4));
                    break;
                case 4:
                    txtVtasksToDo.setText(String.valueOf(counterToDoT5));
                    txtVtasksDone.setText(String.valueOf(counterDoneT5));
                    break;
                case 5:
                    txtVtasksToDo.setText(String.valueOf(counterToDoT6));
                    txtVtasksDone.setText(String.valueOf(counterDoneT6));
                    break;
            }
/*
            int numberOfLoops = newdata.size();
           // RelativeLayout relLayCatlistView = (RelativeLayout)convertView.findViewById(R.id.relLayCatListView);
            CheckedTextView[] checkTXV = new CheckedTextView[newdata.size()];

            LinearLayout linLayChkTxtVContainer = (LinearLayout)convertView.findViewById(R.id.linLayCheckedTextViewContainer);

          //  Log.i("ShukDash", "numberOfLoops "+ numberOfLoops);
            for(int l=0;l<numberOfLoops;l++){
            //    Log.i("ShukDash", "CheckedTXTV started "+l);
                checkTXV[l] = new CheckedTextView(getContext());
                checkTXV[l].setId(l);
                checkTXV[l].setTag("checkTxtV" + l);
                checkTXV[l].setText("Task " + l + 1);
                checkTXV[l].setTextSize(20);
                checkTXV[l].setTextColor(Color.BLACK);
                int isAnswer = newdata.get(l).intValue();
              //  Log.i("ShukDash", "CheckedTXTV started "+l + " isAnswer "+isAnswer);

               // TypedValue value = new TypedValue();
              //  getContext().getTheme().resolveAttribute(android.R.attr.listChoiceIndicatorMultiple, value, true);
               // checkTXV[l].setCheckMarkDrawable(value.resourceId);
                LinearLayout.LayoutParams linLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                linLayoutParams.setMargins(20,0,0,0);

                if (isAnswer==0){

                    checkTXV[l].setChecked(false);
                }
                else if(isAnswer==1){
                    checkTXV[l].setChecked(true);
                }
                Log.i("ShukDash", "CheckedTXTV added "+l);
                checkTXV[l].setLayoutParams(linLayoutParams);
                linLayChkTxtVContainer.addView(checkTXV[l]);
            }

*/

            return convertView;
        }



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