package com.app.shukdash.Views;

import android.annotation.TargetApi;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
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
import com.app.shukdash.Presenters.OnSaveGameUpdateData;
import com.app.shukdash.Presenters.onSaveUpdateFragment;
import com.app.shukdash.Views.fragments.PointsTimeFragment;
import com.example.shukdash.R;

import java.util.ArrayList;
import java.util.List;

public class TasksDisplay extends AppCompatActivity  {

    List<CatDetailsData> dataFromDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks_display);


        Intent i = getIntent();
        int num = i.getIntExtra("Category", 0);
        Log.i("ShukDash TasksDisplay", "Category number " + String.valueOf(num));

        TextView txtVCatTitle = (TextView)findViewById(R.id.txtVTaskCategoryTitle);

        ShukDashDB db = new ShukDashDB(getApplicationContext());
        Cursor c = db.getTaskListDetails(num);


        switch (num){
            case 1:

                Log.i("ShukDash", "prefs category "+String.valueOf(num));
                txtVCatTitle.setText("Category :-  Performing Arts");
                break;
            case 2:

                Log.i("ShukDash", "prefs category "+String.valueOf(num));
                txtVCatTitle.setText("Category :-  Shuk Dash Special");
                break;
            case 3:

                Log.i("ShukDash", "prefs category "+String.valueOf(num));
                txtVCatTitle.setText("Category :-  Meet and Greet");
                break;
            case 4:

                Log.i("ShukDash", "prefs category "+String.valueOf(num));
                txtVCatTitle.setText("Category :-  Shuk-tionary");
                break;
            case 5:

                Log.i("ShukDash", "prefs category "+String.valueOf(num));
                txtVCatTitle.setText("Category :-  GR8 2 Navigate");
                break;
            case 6:

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
           data.setIsTextAnswer(c.getInt(7));
           data.setIsPhotoAnswer(c.getInt(8));

           dataFromDB.add(data);

       }


        int numOfTasks  = dataFromDB.get(0).getCatLength();
        Log.i("ShukDash", "numofTasks "+String.valueOf(numOfTasks));


        List<String> taskNumbers = new ArrayList<>();
        for (int m = 0; m<numOfTasks;m++){

            String a = String.valueOf(m + 1);
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

        List<Integer> isTextAnswer = new ArrayList<>();
        for(int n =0;n<numOfTasks;n++){

            int t = dataFromDB.get(n).getIsTextAnswer();
            Log.i("ShukDash", "isTextAnswer "+t);
            isTextAnswer.add(t);
        }

        List<Integer> isPhotoAnswer = new ArrayList<>();
        for (int n = 0; n<numOfTasks; n++){
            int t = dataFromDB.get(n).getIsPhotoAnswer();
            Log.i("ShukDash", "isPhotoAnswer "+t);
            isPhotoAnswer.add(t);
        }


        ExpandableListView exLView = (ExpandableListView)findViewById(R.id.expLstTasks);

        ExpandableListAdapter exAdapter = new ExpandableListAdapter(getApplicationContext(),num,  taskNumbers, descripts,answers, points, isTicked,isTextAnswer, isPhotoAnswer);

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



    public class ExpandableListAdapter extends BaseExpandableListAdapter  {

        onSaveUpdateFragment updateFragment;
        private Context c;
        private  List<String> taskNum;
        private List<String> description;
        private List<Integer> points;
        private List<Integer > isTicked;
        private List<String > answers;
        List<Integer> isTextAnswer;
        List<Integer> isPhotoAnswer;
        private int num;
        boolean correctAnswer;
        int pointsTotal;
        ShukDashDB db ;
        OnSaveGameUpdateData obs;
       // Observer data;
        public ExpandableListAdapter(Context c,  int num, List<String> numbers,  List<String> description,
                                     List<String > answers,  List<Integer> points, List<Integer > isTicked,
                                     List<Integer> isTextAnswer,List<Integer> isPhotoAnswer ){
            this.c =c;
            this.taskNum = numbers;
            this.description = description;
            this.points = points;
            this.num = num;
            this.isTicked = isTicked;
            this.answers = answers;
            this.isTextAnswer = isTextAnswer;
            this.isPhotoAnswer = isPhotoAnswer;
            obs = new OnSaveGameUpdateData();
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


            TextView taskNumber = (TextView)convertView.findViewById(R.id.txtVTaskNumber);
            taskNumber.setText(taskNum.get(groupPosition));

            TextView pointsTxtV = (TextView)convertView.findViewById(R.id.txtVPoints);
            pointsTxtV.setText(points.get(groupPosition).toString() + " Points ");

            TextView descriptionTxtView = (TextView)convertView.findViewById(R.id.txtVDescription);
            descriptionTxtView.setText(description.get(groupPosition));


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

        EditText answer=null;
        Button saveAnswers;
        Button takeAPic=null;

        @Override
        public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            correctAnswer = false;


            if(convertView==null) {
                LayoutInflater inflater = (LayoutInflater) this.c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.task_enter_answers, null);
             //  convertView = inflater.inflate(R.layout.task_enter_answers_text_only, null);
            }
             //   if(isFBAnswer.equals(1)&&isPhotoAnswer.equals(1)) {
             //       convertView = inflater.inflate(R.layout.task_enter_answers_photo_and_fb, null);
             // Button postFB = (Button)convertView.findViewById(R.id.btnPostFB);
             //   }

            /*
           if(isPhotoAnswer.equals(1)) {
                LayoutInflater inflater = (LayoutInflater) this.c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.task_enter_answers_photo_only, null);
                   takeAPic = (Button)convertView.findViewById(R.id.btnTakePic);
               saveAnswers = (Button)convertView.findViewById(R.id.btnSaveAnswers);
                }
           else if(isTextAnswer.equals(1)) {
                LayoutInflater inflater = (LayoutInflater) this.c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
               convertView = inflater.inflate(R.layout.task_enter_answers_text_only, null);
               saveAnswers = (Button)convertView.findViewById(R.id.btnSaveAnswers);
                   answer = (EditText)convertView.findViewById(R.id.edtTAnswer);
                }
*/


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

            /*
                takeAPic.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(c, "Take a Pic button Pressed", Toast.LENGTH_LONG).show();
                    }
                });
*/

     /*       postFB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(c, "Post to FB button Pressed", Toast.LENGTH_LONG).show();
                }
            });

*/

            final View cv = convertView;
            final int answerForGroup = groupPosition;
            saveAnswers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i("ShukDash", "on click");
                    String answerText = null;

                    //if(v.get==R.layout.task_enter_answers_text_only)
               // if(isTextAnswer.equals(1)) {
                    if (answer.getText() != null) {
                        answerText = answer.getText().toString();
                        Log.i("ShukDash", "TasksDisplay Set answer and isAnswered save to DB: catCode- " + num + " taskNum " + (groupPosition + 1));
                        boolean result = db.setAnswerForTask(num, groupPosition + 1, answerText, 1);

                        if (result) {
                            Log.i("ShukDash", "TasksDisplay setAnswersForTask " + groupPosition + " Success");
                            Toast.makeText(getApplicationContext(), "Results successfully saved", Toast.LENGTH_LONG).show();
                            int tasksValue = Integer.valueOf(taskNum.get(groupPosition));

                            Log.i("ShukDash setPoints", " points " + points.get(groupPosition));
                            obs.setPoints(points.get(groupPosition));
                            Log.i("ShukDash setTasks", " tasks " + tasksValue);
                            obs.setTask(tasksValue);

                           //  obs.addObserver();
                           //  obs.notifyObservers();

                        } else if (!result) {
                            Log.i("ShukDash", "TasksDisplay setAnswersForTask " + groupPosition + " UNSUCCESSFUL");
                            Toast.makeText(getApplicationContext(), "Results successfully saved", Toast.LENGTH_LONG).show();
                        }


                    }

                    answerText = answer.getText().toString();
                    Log.i("ShukDash", "EditText text is " + answerText);
                    Toast.makeText(c, "Save Answers button Pressed " + answerText, Toast.LENGTH_LONG).show();
               // }
                //    if(isPhotoAnswer.equals(1)) {
                //        Toast.makeText(getApplicationContext(), "Photo answer" , Toast.LENGTH_SHORT).show();
                //    }

                    //////
                    ///update the points total in the pointstotal fragment
                    ///////

                    PointsTimeFragment pointsTime = (PointsTimeFragment)getSupportFragmentManager().findFragmentById(R.id.ClockPointsFragment);
                    pointsTime.onPause();
                    pointsTime.onResume();

                }
            });

            return convertView;
        }

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }


    }

}
