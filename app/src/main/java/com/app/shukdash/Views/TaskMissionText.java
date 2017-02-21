package com.app.shukdash.Views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.shukdash.Models.ShukDashDB;
import com.example.shukdash.R;

public class TaskMissionText extends AppCompatActivity {

    String catNum,taskNum;
    int taskN, catN;
    Intent i;
    ShukDashDB db;
    EditText edittext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_mission);

        Log.i("Shukdash TaskMissionText", "onCreate started");
        db = new ShukDashDB(getApplicationContext());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView txtVDesc = (TextView)findViewById(R.id.txtVDescription);
        TextView txtVtaskNum = (TextView)findViewById(R.id.txtVTaskNumber);
        TextView txtVPoints = (TextView)findViewById(R.id.txtVPoints);
        RelativeLayout outer = (RelativeLayout)findViewById(R.id.outerContainer);
        RelativeLayout inner = (RelativeLayout)findViewById(R.id.innerContainer);


        ImageView imgV = (ImageView)findViewById(R.id.ImgVTaskMissionTypeDisplay);
        Drawable drawable = getDrawable(R.drawable.writenew );
        VectorDrawable vDraw = (VectorDrawable) drawable;

        imgV.setImageDrawable(vDraw);

        i = getIntent();

        //TODO: set default data in case there is No Intent

        String desc = i.getStringExtra("Description");
        catNum = i.getStringExtra("CatNum");
        String catName = i.getStringExtra("CatName");
        taskNum = i.getStringExtra("TaskNum");
        String p = i.getStringExtra("Points");

        int numPoints = Integer.valueOf(p);
       // Resources res = getResources();
        catN = Integer.valueOf(catNum);
        taskN = Integer.valueOf(taskNum);
        Log.i("Shukdash TaskMissionText", "onCreate catN = "+catN);
        switch (catN){
            case 1:
                outer.setBackgroundColor(getResources().getColor(R.color.catTabArts));
                break;
            case 2:
                outer.setBackgroundColor(getResources().getColor(R.color.catTabDash));
                break;
            case 3:
                outer.setBackgroundColor(getResources().getColor(R.color.catTabMeet));
                break;
            case 4:
                outer.setBackgroundColor(getResources().getColor(R.color.catTabShuktion));
                break;
            case 5:
                outer.setBackgroundColor(getResources().getColor(R.color.catTabGR8));
                break;
            case 6:
                outer.setBackgroundColor(getResources().getColor(R.color.catTabSnap));
                break;

        }
        Log.i("TaskMissionText ", " notify data set changed ");

        Log.i("TaskMissionText intent data ", " CatNum "+catNum+" CatName "+ catName
                +" TaskNum " + taskNum + " Description " + desc) ;

        txtVtaskNum.setText(String.valueOf(taskNum));
        txtVDesc.setText(desc);
       // Integer taskNUM = Integer.valueOf(taskNum);

        if (numPoints==1){
            txtVPoints.setText(String.format(getString(R.string.point1InCardView),p));
        }
        else if (numPoints>1){
            txtVPoints.setText(String.format(getString( R.string.pointsInCardView), p));
        }

        //TODO:Check the database to see if there is a previously
        // saved answer and then display that answer in the edittext widget
        //


    }
    int isAnswered;
    @Override
    protected void onStart() {
        super.onStart();

        //TODO: check and get answers stored on db for this question
        edittext = (EditText)findViewById(R.id.editText);
        isAnswered = db.getIsCompleted(catN, taskN);

        if (isAnswered==1){
          String savedAnswer=  db.getAnswerForTask(catN, taskN);
            Log.i("TaskMissionText ","Saved answer from DB for this task "+savedAnswer);
            edittext.setText(savedAnswer);
        }
    }

    String result;
    @Override
    protected void onResume() {
        super.onResume();
        if(isAnswered==0) {
            edittext.setHint("Tap Here to enter your answer");
            edittext.setHintTextColor(Color.DKGRAY);
        }
        Log.i("TaskMissionText ", " set input type for catNum "+catN+" taskNum "+taskN);
        if(catN==5&&taskN==1||catN==5&&taskN==4){  //||   ||
            edittext.setInputType(InputType.TYPE_CLASS_NUMBER|InputType.TYPE_NUMBER_FLAG_DECIMAL);
        }

        if (catN==3&&(taskN==10||taskN==9)||catN==4&&taskN==5){
            Log.i("TaskMissionText ", " set input type for catNum "+catN+" taskNum "+taskN);
            String fileLocation = i.getStringExtra("fileLocation");
            int locationID = i.getIntExtra("locationID",0);
            String picDrawable = i.getStringExtra("picDrawable");
            Log.i("TaskMissionText ", " picdata for img v for daviddagim or olumumloo pics fileLoc "+fileLocation+" locID "+locationID+" picDrawable "+picDrawable);
            ImageView imgForPic = (ImageView)findViewById(R.id.imgVDisplayPhotoForQuestion);
            imgForPic.setVisibility(View.VISIBLE);
            String uri = "R.drawable."+picDrawable;
            Log.i("TaskMissionText ", " picdata for img v for daviddagim or olumumloo pics URI= "+uri);
         //   Drawable pic = Drawable.createFromPath(uri);
            Drawable pic =getDrawable(locationID);

            imgForPic.setImageDrawable(pic);

            RelativeLayout.LayoutParams relLayParams = new RelativeLayout.LayoutParams(560,350);
            relLayParams.addRule(RelativeLayout.END_OF, R.id.viewSeparateTaskAnswerBorder);
            relLayParams.addRule(RelativeLayout.ALIGN_TOP, R.id.innerContainer);
            imgForPic.setLayoutParams(relLayParams);

            RelativeLayout.LayoutParams relLayParamsToMoveEditText = new RelativeLayout.LayoutParams(150,350);
            relLayParamsToMoveEditText.addRule(RelativeLayout.END_OF, R.id.imgVDisplayPhotoForQuestion);
            relLayParamsToMoveEditText.addRule(RelativeLayout.ALIGN_TOP, R.id.innerContainer);
            edittext.setLayoutParams(relLayParamsToMoveEditText);

        }

        ///////////////////////////////////////////////////
        //Dont need this at the moment
    /*    if (catN==3&&(taskN!=10||taskN!=9)){
            Log.i("TaskMissionText ", " set input type for catNum "+catN+" taskNum "+taskN);
            Log.i("TaskMissionText ", " Add image to the task description as with photo missions");
            Log.i("TaskMissionText ", " Display image ");
        }
*/

        edittext.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                edittext.setHint("");
                InputMethodManager im = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                  im.hideSoftInputFromWindow(edittext.getWindowToken(),0); return false;
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabSaveAnswer);

        Drawable save = getDrawable(R.drawable.save_black_24dp);
        fab.setImageDrawable(save);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                result = edittext.getText().toString();
                String resultHint = edittext.getHint().toString();
                Log.i("TaskMissionText fab onclick", "result: "+result +" hintResult: "+resultHint);
                if (result!=""||resultHint!="Tap Here to enter your answer"  ) {


                    boolean answerSaved = db.setAnswerForTask(Integer.valueOf(catNum), Integer.valueOf(taskNum), result, 1);

                    if (answerSaved) {
                        Snackbar.make(view, "Your answer has been saved", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                        //TODO: set up a way to check the string for specific words depending on the task. For this we should probably load a list / array of strings of
                        // keywords which would mean the correct answer had been given

                    }
                    else {
                        Snackbar.make(view, "Your answer has NOT been saved, please check and make any changes and then save again", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                }
                else {
                    Snackbar.make(view, "Your answer has NOT been saved, please check and make any changes and then save again", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
        });

        FloatingActionButton fabGoBack = (FloatingActionButton) findViewById(R.id.fabGoBack);
        fabGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("TaskMissionText fab onclick", "fabgoback clicked - finish() ");

                //TODO:What is best way to go back to previous screen???
             /*   Intent i = new Intent(TaskMissionText.this, ShukDashMain.class);
                i.putExtra("TabCat", catNum);
                i.putExtra("TaskNum", taskNum);
                startActivity(i);
               */
                finish();
            }
        });
    }




    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Shukdash TaskMissionText", "onPause started");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Shukdash TaskMissionText", "onStop started");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Shukdash TaskMissionText", "onDestroy started");
    }
}
