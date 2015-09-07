package com.app.shukdash.Views;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shukdash.Models.CatDetailsData;
import com.app.shukdash.Models.ShukDashDB;
import com.example.shukdash.R;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TasksDisplay extends AppCompatActivity {
    List<CatDetailsData> dataFromDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_display);

        Intent i = getIntent();
        int num = i.getIntExtra("Category", 0);
        Log.i("ShukDash TasksDisplay", "Category number " + String.valueOf(num));

       // Toast.makeText(this, "Category "+num, Toast.LENGTH_LONG).show();
        TextView txtVCatTitle = (TextView)findViewById(R.id.txtVTaskCategoryTitle);

        ShukDashDB db = new ShukDashDB(getApplicationContext());
        Cursor c = db.getTaskListDetails(num);

       // String dataDump = DatabaseUtils.dumpCursorToString(c);
       // Log.i("ShukDash", "Task Display : Cursor Dump to string " + dataDump);



       // SharedPreferences prefs=null ;
       // prefs = getSharedPreferences("category"+num, Context.MODE_APPEND);
        switch (num){
            case 1:

                Log.i("ShukDash", "prefs category "+String.valueOf(num));
                txtVCatTitle.setText("Category :-  Performing Arts");
                break;
            case 2:
               // prefs = getSharedPreferences("category2", Context.MODE_APPEND);
                Log.i("ShukDash", "prefs category "+String.valueOf(num));
                txtVCatTitle.setText("Category :-  Shuk Dash Special");
                break;
            case 3:
              //  prefs = getSharedPreferences("category3", Context.MODE_APPEND);
                Log.i("ShukDash", "prefs category "+String.valueOf(num));
                txtVCatTitle.setText("Category :-  Meet and Greet");
                break;
            case 4:
              //  prefs = getSharedPreferences("category4", Context.MODE_APPEND);
                Log.i("ShukDash", "prefs category "+String.valueOf(num));
                txtVCatTitle.setText("Category :-  Shuk-tionary");
                break;
            case 5:
             //   prefs = getSharedPreferences("category5", Context.MODE_APPEND);
                Log.i("ShukDash", "prefs category "+String.valueOf(num));
                txtVCatTitle.setText("Category :-  GR8 2 Navigate");
                break;
            case 6:
              //  prefs = getSharedPreferences("category6", Context.MODE_APPEND);
                Log.i("ShukDash", "prefs category "+String.valueOf(num));
                txtVCatTitle.setText("Category :-  SNAP");
                break;
            default:
                break;
        }


       dataFromDB = new ArrayList<>();
       while (c.moveToNext()){

           CatDetailsData data = new CatDetailsData();
           data.setCatLength(c.getInt(2));
           data.setNumTasks(c.getInt(0));
           data.setCategoryName(c.getString(1));
           data.setDescription(c.getString(3));
           data.setPoints(c.getInt(4));
           data.setAnswer(c.getString(5));
           data.setIsAnswered(c.getInt(6));

           dataFromDB.add(data);

       }


        int numOfTasks  = dataFromDB.get(0).getCatLength();
        Log.i("ShukDash", "numofTasks "+String.valueOf(numOfTasks));


        List<String> taskNumbers = new ArrayList<>();
        for (int m = 0; m<numOfTasks;m++){

            String a = String.valueOf(m+1);
            Log.i("ShukDash", "numofTasks int value "+a);
            taskNumbers.add(a);
        }

        List<String> descripts = new ArrayList<>();
        //String[] tasks = getResources().getStringArray(R.array.NumOfTasks);
        for(int j=0;j<numOfTasks;j++){

            String d = dataFromDB.get(j).getDescription();
            Log.i("ShukDash", "descripts String value "+d);
            descripts.add(d);
        }

        List<Integer > points = new ArrayList<>();
        for(int k=0;k<numOfTasks;k++){

            int d = dataFromDB.get(k).getPoints();
            Log.i("ShukDash", "points String value "+String.valueOf(d));
            points.add(d);
        }

        List<Integer > isTicked = new ArrayList<>();
        for(int k=0;k<numOfTasks;k++){

            int t = dataFromDB.get(k).getIsAnswered();
            Log.i("ShukDash", "points Boolean value "+String.valueOf(t));
            isTicked.add(t);
        }


        List<String > answers = new ArrayList<String>();
        for(int k=0;k<numOfTasks;k++){

            String t = dataFromDB.get(k).getAnswer();
            Log.i("ShukDash", "answers String value "+t);
            answers.add(t);
        }

        ExpandableListView exLView = (ExpandableListView)findViewById(R.id.expLstTasks);

        ExpandableListAdapter exAdapter = new ExpandableListAdapter(getApplicationContext(),num,  taskNumbers, descripts,answers, points, isTicked);

        exLView.setAdapter(exAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tasks_display, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    /*

     int numOfTasks = prefs.getInt("catLength",0);
        Log.i("ShukDash", "numofTasks "+String.valueOf(numOfTasks));

        List<String> numbers = new ArrayList<String>();
        for (int l =0;l<numOfTasks;l++){
            String a = String.valueOf(l+1);
            Log.i("ShukDash", "numofTasks String value "+a);
            numbers.add(a);
        }
    String d = prefs.getString("Description" + j, "");
     int d = prefs.getInt("Points" + k, 0);
    boolean t = prefs.getBoolean("Ticked" + k, false);
    String t = prefs.getString("Answer" + k, "");
     */

    public class ExpandableListAdapter extends BaseExpandableListAdapter{

        private Context c;
        private  List<String> taskNum;
        private List<String> description;
        private List<Integer> points;
        private List<Integer > isTicked;
        private List<String > answers;
        private int num;
        boolean correctAnswer;
        int pointsTotal;
        ShukDashDB db ;

        public ExpandableListAdapter(Context c,  int num, List<String> numbers,  List<String> description,
                                     List<String > answers,  List<Integer> points, List<Integer > isTicked){
            this.c =c;
            this.taskNum = numbers;
            this.description = description;
            this.points = points;
            this.num = num;
            this.isTicked = isTicked;
            this.answers = answers;
            db=new ShukDashDB(getApplicationContext());
        }


        @Override
        public int getGroupCount() {
            return taskNum.size();
        }

        @Override
        public int getChildrenCount(int groupPosition) {
            return 1;
        }

        @Override
        public Object getGroup(int groupPosition) {
            return this.description.get(groupPosition);
        }

        @Override
        public Object getChild(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        @Override
        public long getChildId(int groupPosition, int childPosition) {
            return childPosition;
        }

        @Override
        public boolean hasStableIds() {
            return true;
        }

        @Override
        @TargetApi(16)
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

            if(convertView==null){
                LayoutInflater inflater = (LayoutInflater) this.c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.task_description, null);
            }

            Log.i("ShukDash", "numbers has " + String.valueOf(taskNum.size()));
            Log.i("ShukDash", "descriptions has " + String.valueOf(description.size()));
            Log.i("ShukDash", "points has " + String.valueOf(points.size()));
            Log.i("ShukDash", "groupPosition is " + String.valueOf(groupPosition));

            TextView taskNumber = (TextView)convertView.findViewById(R.id.txtVTaskNumber);
            taskNumber.setText(taskNum.get(groupPosition));

            TextView pointsTxtV = (TextView)convertView.findViewById(R.id.txtVPoints);
            pointsTxtV.setText(points.get(groupPosition).toString() + " Points ");

            TextView descriptionTxtView = (TextView)convertView.findViewById(R.id.txtVDescription);
            descriptionTxtView.setText(description.get(groupPosition));

            long packedPosition = ExpandableListView.getPackedPositionForGroup(groupPosition);
            int positionType = ExpandableListView.getPackedPositionType(packedPosition);
            Log.i("Shukdash", "packedposition " +packedPosition + "position type "+positionType);


            /////////////////
            //set the colour of each parent list that is already been completed
            // this doesnt seem to work properly!!! not sure why???
            ////////////////////
          /*  List<String[]> isAnsweredData = db.getIsCompletedByCatCode(num);

            for (int i =0; i<isAnsweredData.size(); i++){
                Log.i("ShukDash", "TasksDisplay asAnsweredData.size = "+isAnsweredData.size() + " "+i +" "+isAnsweredData.get(i)[1]);
                int d = Integer.valueOf(isAnsweredData.get(i)[1]);

                if (d==1){
                    Log.i("ShukDash", "d =1");
                    convertView.setBackground(getResources().getDrawable(R.drawable.abc_item_background_holo_light));
                   // convertView.setBackgroundColor(getResources().getColor(R.color.accent_material_light));
                }
               // else if (d==1)
               //     Log.i("ShukDash", "d =0");
               //     convertView.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));

            }
        */
            return convertView;
        }

        @Override
        public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            correctAnswer = false;



            Log.i("ShukDash", "group position is "+groupPosition);
            Log.i("ShukDash", "child position is "+childPosition);
           // pointsTotal = pref.getInt("catPointsTotal", 0);
           // Log.i("ShukDash", "initial points total is "+pointsTotal);

            if(convertView==null){
                LayoutInflater inflater = (LayoutInflater) this.c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.task_enter_answers, null);
            }

            Button takeAPic = (Button)convertView.findViewById(R.id.btnTakePic);
            //Button postFB = (Button)convertView.findViewById(R.id.btnPostFB);
            Button saveAnswers = (Button)convertView.findViewById(R.id.btnSaveAnswers);
            final EditText answer = (EditText)convertView.findViewById(R.id.edtTAnswer);


            if (answers.get(groupPosition)!=null){
                answer.setText(answers.get(groupPosition));
                correctAnswer=true;
            }

/*
This should open up a platform camera app to take a pic and return it to here for display and save the details.

I would also like to add a value to the database that reflects whether the task requires a pic to be taken or not
Similarly whether a text answer needs to be given or not/ post to fb.

In each case the relevant buttons and views will be displayed and hidden
 */
            takeAPic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(c, "Take a Pic button Pressed", Toast.LENGTH_LONG).show();
                }
            });

     /*       postFB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(c, "Post to FB button Pressed", Toast.LENGTH_LONG).show();
                }
            });

*/
            final int answerForGroup = groupPosition;
            saveAnswers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("ShukDash", "on click");
                    String answerText = null;
                    if (answer.getText() != null) {
                        answerText = answer.getText().toString();
                        Log.i("ShukDash", "TasksDisplay Set answer and isAnswered save to DB: catCode- "+num +" taskNum " +groupPosition+1);
                       boolean result = db.setAnswerForTask(num ,groupPosition+1 , answerText, 1);

                        if (result){
                            Log.i("ShukDash", "TasksDisplay setAnswersForTask "+groupPosition +" Success");
                            Toast.makeText(getApplicationContext(), "Results successfully saved", Toast.LENGTH_LONG).show();
                        }
                        else if (!result){
                            Log.i("ShukDash", "TasksDisplay setAnswersForTask "+groupPosition +" UNSUCCESSFUL");
                            Toast.makeText(getApplicationContext(), "Results successfully saved", Toast.LENGTH_LONG).show();
                        }


                    }
                //    Log.i("ShukDash", "New Pointstotal is " + pointsTotal);
                    Log.i("ShukDash", "EditText text is " + answerText);
                    Toast.makeText(c, "Save Answers button Pressed " + answerText, Toast.LENGTH_LONG).show();

                    //////
                    ///update the points total in the pointstotal fragment
                    ///////
                }
            });

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }
    }

}
/*
SharedPreferences.Editor edit = pref.edit();
                        Log.i("ShukDash", "save text");

                        if(isTicked.get(groupPosition)==0) {
                            Log.i("ShukDash", "Pointstotal original is " + pointsTotal);

                            Log.i("ShukDash", "Points for this answer " + points.get(groupPosition));
                            pointsTotal = pointsTotal + points.get(groupPosition);
                            Log.i("ShukDash", "New Pointstotal is " + pointsTotal);
                            edit.putInt("catPointsTotal", pointsTotal);
                        }

                        edit.putString("Answer" + answerForGroup, answerText);
                        edit.putBoolean("Ticked" + answerForGroup, true);
                        edit.commit();


 */