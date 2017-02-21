package com.app.shukdash.Views;


import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.app.shukdash.Adapters.MissionDisplayDataAdapter;
import com.app.shukdash.Adapters.TabCategoryViewPagerAdapter;
import com.app.shukdash.Models.CatDetailsData;
import com.app.shukdash.Models.ShukDashDB;
import com.app.shukdash.Presenters.ShukDashMainPresenter;
import com.app.shukdash.UseCases.StoreCatNumFromAdapter;
import com.example.shukdash.R;

import java.util.ArrayList;
import java.util.List;

public class ShukDashMain extends AppCompatActivity {

    public ShukDashDB db;
    public TabLayout tabLayout;
    private TabCategoryViewPagerAdapter tabCatViewPagerAdapter;
    private RecyclerView recyclerView;
    private MissionDisplayDataAdapter missionDisplayRecyclerAdapter;
    List<CatDetailsData> dataFromDB;
    List<String> catNums;
    LinearLayoutManager linLayRecycler;
    private ShukDashMainPresenter sdmPres;
    ImageButton btnEndGame;
    Cursor c;
    int catNum;
  //  Context context;
    public ShukDashMain() {
//added new comment to test github
        //second test
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuk_dash_main);
        Log.i("ShukDashMain", "OnCreate()");
       // context = this;
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        Log.i("ShukDashMain screen width ", " " + width);

        ////////////////////////////////////
        //set up recyclerview
        tabLayout = (TabLayout) findViewById(R.id.TabLayCategories);
        recyclerView = (RecyclerView) findViewById(R.id.missionDisplayByCatInRecyclerView);

        /////////////////////////////////////
        //get data from DataBase
        /////////////////////////////////////
        sdmPres = new ShukDashMainPresenter();

        //TODO: check to see if these two lines should stay here or really move to the presenter file.
        db = new ShukDashDB(this);
        c = db.getTaskListDetails(1);  //cat num 1-6
        Log.i("ShukDashMain received DB data ", "");
        dataFromDB = new ArrayList<>();
        catNums = new ArrayList<>();
        catNums.add("Performing Arts"); catNums.add("Shuk Dash Special");
        catNums.add("Meet and Greet"); catNums.add("Shuk-tionary"); catNums.add("GR8 2 Navigate"); catNums.add("Snap");
        missionDisplayRecyclerAdapter = new MissionDisplayDataAdapter(this, dataFromDB);
     //   Log.i("ShukDashMain", "display contents of catNums , this doesnt display in tabs and causes crash - "+catNums.size()+" data: "+catNums.toString());
      //  viewPager = (ViewPager) findViewById(R.id.ViewpagerMissionsDisplay);
         Log.i("ShukDashMain", "OnStart() get new data from db for dataFromDB");
        dataFromDB = sdmPres.getMissionTasksList(c, dataFromDB);

        //   AsyncGetData asyncGetData = new AsyncGetData(recyclerView, missionDisplayRecyclerAdapter, this);
     //   asyncGetData.execute();




        Log.i("ShukDashMain", "Oncreate() get new data from db for dataFromDB");


        Log.i("ShukDashMain", "OnCreate() initial get from db completed in thread!!!!!!!!!");


        Log.i("ShukDashMain", "OnCreate() size of data is "+dataFromDB.size());

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //              start of section copied into async task                                 ////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        catNums = db.getNumOfTasks();


        ////////////////////////////////////
        //set up tabs
        tabCatViewPagerAdapter = new TabCategoryViewPagerAdapter(getSupportFragmentManager());
        //   viewPager.setAdapter(tabCatViewPagerAdapter);
      //  TabLayout.Tab[] tabs = new TabLayout.Tab[5];
        TabLayout.Tab special = tabLayout.newTab();
        TabLayout.Tab meetGreet = tabLayout.newTab();
        TabLayout.Tab shuktionary = tabLayout.newTab();
        TabLayout.Tab navigate = tabLayout.newTab();
        TabLayout.Tab snap = tabLayout.newTab();
        TabLayout.Tab perform = tabLayout.newTab();

        View firstTabCustomV = new View(this);
        firstTabCustomV.setBackgroundColor(getResources().getColor(R.color.catTabArts));
        //TextView txtVMissionsTotPerCat = (TextView)firstTabCustomV.findViewById(R.id.TxtVDone);
        // txtVMissionsTotPerCat.setText("33");
        View secondTabCustomV = new View(this);
        secondTabCustomV.setBackgroundColor(getResources().getColor(R.color.catTabDash));

        View thirdTabCustomV = new View(this);
        thirdTabCustomV.setBackgroundColor(getResources().getColor(R.color.catTabShuktion));

        View forthTabCustomV = new View(this);
        forthTabCustomV.setBackgroundColor(getResources().getColor(R.color.catTabMeet));

        View fifthTabCustomV = new View(this);
        fifthTabCustomV.setBackgroundColor(getResources().getColor(R.color.catTabGR8));

        View sixthTabCustomV = new View(this);
        sixthTabCustomV.setBackgroundColor(getResources().getColor(R.color.catTabSnap));

        //  TabLayout.Tab firstTab = tabLayout.newTab().setText("FirstTAb").setCustomView(R.layout.tabcat1);
        tabLayout.setSelectedTabIndicatorColor(Color.CYAN);

        special.setCustomView(R.layout.tabcat1);
        //special.setText("Special");
        // meetGreet.setText("MeetAndGreet");
        meetGreet.setCustomView(R.layout.tabcat2);
        // shuktionary.setText("Shuktionary");
        shuktionary.setCustomView(R.layout.tabcat3);
        // navigate.setText("Navigate");
        navigate.setCustomView(R.layout.tabcat4);
        // snap.setText("SNAP");
        snap.setCustomView(R.layout.tabcat5);
        // perform.setText("Performing Arts");
        perform.setCustomView(R.layout.tabcat6);

        tabLayout.setBackgroundColor(Color.DKGRAY);
        tabLayout.addTab(special, 0);
        tabLayout.addTab(meetGreet, 1);
        tabLayout.addTab(shuktionary, 2);
        tabLayout.addTab(navigate, 3);
        tabLayout.addTab(snap, 4);
        tabLayout.addTab(perform, 5);

        //   int num1 =1;
        // num1 = dataFromDB.get(1).getCatLength();
        //   String tabNum1 = String.valueOf(num1);
        //   int num2 =1;
        // num2 = dataFromDB.get(2).getCatLength();
        // String tabNum2 = String.valueOf(num2);
        //  int num3 =1;
        //  num3 = dataFromDB.get(3).getCatLength();
        //  String tabNum3 = String.valueOf(num3);

        TextView txtVMissionsTotPerCat1 = (TextView) findViewById(R.id.TxtVDoneTab1);
        txtVMissionsTotPerCat1.setText(catNums.get(0));   //TODO: this sometimes causes a crash as I need to ensure all the screen sizes are configured
        TextView txtVMissionsTotPerCat2 = (TextView) findViewById(R.id.TxtVDoneTab2);
        txtVMissionsTotPerCat2.setText(catNums.get(1));
        TextView txtVMissionsTotPerCat3 = (TextView) findViewById(R.id.TxtVDoneTab3);
        txtVMissionsTotPerCat3.setText(catNums.get(2));
        TextView txtVMissionsTotPerCat4 = (TextView) findViewById(R.id.TxtVDoneTab4);
        txtVMissionsTotPerCat4.setText(catNums.get(3));
        TextView txtVMissionsTotPerCat5 = (TextView) findViewById(R.id.TxtVDoneTab5);
        txtVMissionsTotPerCat5.setText(catNums.get(4));
        TextView txtVMissionsTotPerCat6 = (TextView) findViewById(R.id.TxtVDoneTab6);
        txtVMissionsTotPerCat6.setText(catNums.get(5));

        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));

        tab1TasksCompleted = (TextView) findViewById(R.id.TxtVToDoTab1);
        tab2TasksCompleted = (TextView) findViewById(R.id.TxtVToDoTab2);
        tab3TasksCompleted = (TextView) findViewById(R.id.TxtVToDoTab3);
        tab4TasksCompleted = (TextView) findViewById(R.id.TxtVToDoTab4);
        tab5TasksCompleted = (TextView) findViewById(R.id.TxtVToDoTab5);
        tab6TasksCompleted = (TextView) findViewById(R.id.TxtVToDoTab6);

        btnEndGame = (ImageButton) findViewById(R.id.imgBtnEndGame);
        //  viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        Log.i("ShukDashMain", "OnCreate() get new data from db for dataFromDB");
      //  dataFromDB = sdmPres.getMissionTasksList(c, dataFromDB);
            //////////////this was already called earlier ///////////////////////
        Log.i("ShukDashMain", "OnCreate() recyclerview should be correctly updated!!");

        /////////////////////////////////////////////////////////////////////////
        // i will move the recycler intilisation to onresume
        //////////////////////////////////////////////////////

        recyclerView.setHasFixedSize(true);
        linLayRecycler = new LinearLayoutManager(this, 0, false);
        recyclerView.setLayoutManager(linLayRecycler);

        missionDisplayRecyclerAdapter = new MissionDisplayDataAdapter(this, dataFromDB);

        recyclerView.setAdapter(missionDisplayRecyclerAdapter);

        //missionDisplayRecyclerAdapter.notifyDataSetChanged();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //              end of section copied into async task                                 ////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Log.i("ShukDashMain", "OnCreate() db size "+dataFromDB.size());
    }

    int[] answersByCat;
    String taskNumFromTaskAct, catnumFromTaskAct;
    int taskNumFromAct, catnumFromAct;
    //   int counterCat1 =0, counterCat2=0, counterCat3=0, counterCat4=0, counterCat5=0, counterCat6=0;
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("ShukDashMain", "OnStart()");

        //TODO: check if returning from next screen (back) and return to the category of the mission page.
        Log.i("ShukDashMain", "OnStart() clear dataFromDB");
      //      dataFromDB.clear();
        c = db.getTaskListDetails(catNum);
        dataFromDB.clear();
        //   missionDisplayRecyclerAdapter.notifyDataSetChanged();
        dataFromDB = sdmPres.getMissionTasksList(c, dataFromDB);
        answersByCat = new int[6];
        answersByCat[0] = 0; answersByCat[1]=0; answersByCat[2] = 0; answersByCat[3]=0;answersByCat[4] = 0; answersByCat[5]=0;

        // missionDisplayRecyclerAdapter.notifyDataSetChanged();
        Log.i("ShukDashMain", "OnStart() get new data from db for dataFromDB");
       //   dataFromDB=sdmPres.getMissionTasksList(c,dataFromDB);
      //  Log.i("ShukDashMain", "OnStart() notifydatasetchanged to update with new data from db for dataFromDB");
      //  missionDisplayRecyclerAdapter.notifyDataSetChanged();
      //  Log.i("ShukDashMain", "OnStart() data updated");
        //TODO: need new query to get all details of number of tasks completed per category




    /*    Intent intentFromTaskAct = getIntent();
        if(intentFromTaskAct!=null){
            taskNumFromTaskAct = intentFromTaskAct.getStringExtra("TaskNum");
            catnumFromTaskAct = intentFromTaskAct.getStringExtra("TabCat");
            taskNumFromAct = Integer.getInteger(taskNumFromTaskAct);
            catnumFromAct = Integer.getInteger(catnumFromTaskAct);
            displayTabData(taskNumFromAct, tabLayout.getTabAt(catnumFromAct));
        }
        */
        int tabPosOnResume = tabLayout.getSelectedTabPosition();
        Log.i("ShukDashMain", "OnStart() get tabpos "+tabPosOnResume);
        if(tabPosOnResume==5){
            displayTabData(6,tabLayout.getTabAt(5));
        }

        int dbdatalength = dataFromDB.size();
        Log.i("ShukDashMain", "getting values for dbdatalength " + dbdatalength);


        answersByCat = sdmPres.getTabData(answersByCat, db);
        for (int m = 0; m < answersByCat.length; m++) {
               Log.i("ShukDashMain, onResume", "checking if this has been updated after db call : answersByCat "+answersByCat[m] +" at position "+m);
        }

        for (int j = 0; j < dataFromDB.size(); j++) {
            int isAnsw = dataFromDB.get(j).getIsAnswered();
              Log.i("ShukDashMain", "getting values for IsAnswered "+ isAnsw +" at position "+j);
        }

        tab1TasksCompleted.setText(String.valueOf(answersByCat[0]));
        tab2TasksCompleted.setText(String.valueOf(answersByCat[1]));
        tab3TasksCompleted.setText(String.valueOf(answersByCat[2]));
        tab4TasksCompleted.setText(String.valueOf(answersByCat[3]));
        tab5TasksCompleted.setText(String.valueOf(answersByCat[4]));
        tab6TasksCompleted.setText(String.valueOf(answersByCat[5]));

    //    missionDisplayRecyclerAdapter.notifyDataSetChanged();
      //  recyclerView.setAdapter(missionDisplayRecyclerAdapter);
    }

    TextView tab1TasksCompleted, tab2TasksCompleted, tab3TasksCompleted, tab4TasksCompleted, tab5TasksCompleted, tab6TasksCompleted;

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("ShukDashMain", "Onresume()");
      //  Log.i("ShukDashMain", "OnResume() get new data from db for dataFromDB");
        ///////////////////////////////////////////////////////////////////////////
        // I have taken this out to test the intial display bug
        // It could be this mainly updates the score details!!! and needs to be returned!!
        ////////////////////////////////////////////////////////////////////////////////

     //   dataFromDB=sdmPres.getMissionTasksList(c,dataFromDB);
     //   Log.i("ShukDashMain", "OnStart() notifydatasetchanged to update with new data from db for dataFromDB");
     //   missionDisplayRecyclerAdapter.notifyDataSetChanged();
      //  Log.i("ShukDashMain", "OnResume() data updated");

        //////////////////////////////////////////////////////////////
        // initialise recycler
        ///////////////////////////////////////////////////////////////


     /*       Log.i("ShukDashMain", "Onresume() .......Start recycler process");
        recyclerView.setHasFixedSize(true);
        linLayRecycler = new LinearLayoutManager(this, 0, false);
        recyclerView.setLayoutManager(linLayRecycler);

        missionDisplayRecyclerAdapter = new MissionDisplayDataAdapter(this, dataFromDB);

        recyclerView.setAdapter(missionDisplayRecyclerAdapter);
*/
        /////////////////////////////////////////////////////////////////////////////////////

        if (tabLayout.getSelectedTabPosition() == 0) {
            recyclerView.setBackgroundColor(getResources().getColor(R.color.catTabArts));
        }

        //change data in recylcer view cards when pressing on relevant tab
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                catNum = tab.getPosition() + 1;
                Log.i("ShukDashMain onTabSelected", " " + tab.getPosition() + " catnum " + catNum);
                displayTabData(catNum, tab);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        //     Intent j = getIntent();
        //     String teamName = j.getStringExtra("TeamName");
        final Snackbar.Callback endGameSnkBr = new Snackbar.Callback() {
            @Override
            public void onDismissed(Snackbar snackbar, int event) {
                super.onDismissed(snackbar, event);
                Log.i("ShukDashMain end game ", "Button pushed and canceled");

            }
        };

        btnEndGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar end = Snackbar.make(v, "Your answers will be saved and you will get your results", Snackbar.LENGTH_LONG)
                        .setCallback(endGameSnkBr)
                        .setAction("PRESS TO CONTINUE", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                Intent i = new Intent(ShukDashMain.this, FinishedGameScreen.class);
                                startActivity(i);
                            }
                        });

                TextView snackBrTxtV = (TextView) end.getView().findViewById(android.support.design.R.id.snackbar_text);
                snackBrTxtV.setMinWidth(1200);
                end.show();
            }


        });

    }

    public int getCatNum() {
        return catNum;
    }

    public int[] exportTasksToInts(List<String> data) {

        int[] tasksdata = new int[6];

        for (int i = 0; i < data.size(); i++) {

            tasksdata[i] = Integer.valueOf(data.get(i));
            Log.i("ShukDash CategoryFragment", "function exporttasks data : position: " + i + " value " + tasksdata[i]);

        }

        return tasksdata;

    }


    public void displayTabData(int catNum, TabLayout.Tab tab) {
        c = db.getTaskListDetails(catNum);
        dataFromDB.clear();
        missionDisplayRecyclerAdapter.notifyDataSetChanged();
        dataFromDB = sdmPres.getMissionTasksList(c, dataFromDB);

        missionDisplayRecyclerAdapter.notifyDataSetChanged();
        switch (tab.getPosition()) {
            case 0:
                recyclerView.setBackgroundColor(getResources().getColor(R.color.catTabArts));
                StoreCatNumFromAdapter.getInstance().setCatNumFromAdapter(1);
               // missionDisplayRecyclerAdapter.setCatNumFromAdapter(1);
                break;
            case 1:
                recyclerView.setBackgroundColor(getResources().getColor(R.color.catTabDash));
                StoreCatNumFromAdapter.getInstance().setCatNumFromAdapter(2);
                // missionDisplayRecyclerAdapter.setCatNumFromAdapter(2);
                break;
            case 2:
                recyclerView.setBackgroundColor(getResources().getColor(R.color.catTabMeet));
                StoreCatNumFromAdapter.getInstance().setCatNumFromAdapter(3);
                //missionDisplayRecyclerAdapter.setCatNumFromAdapter(3);
                break;
            case 3:
                recyclerView.setBackgroundColor(getResources().getColor(R.color.catTabShuktion));
                StoreCatNumFromAdapter.getInstance().setCatNumFromAdapter(4);
                //missionDisplayRecyclerAdapter.setCatNumFromAdapter(4);
                break;
            case 4:
                recyclerView.setBackgroundColor(getResources().getColor(R.color.catTabGR8));
                StoreCatNumFromAdapter.getInstance().setCatNumFromAdapter(5);
                //missionDisplayRecyclerAdapter.setCatNumFromAdapter(5);
                break;
            case 5:
                recyclerView.setBackgroundColor(getResources().getColor(R.color.catTabSnap));
                StoreCatNumFromAdapter.getInstance().setCatNumFromAdapter(6);
                //missionDisplayRecyclerAdapter.setCatNumFromAdapter(6);
                break;

        }
        Log.i("ShukDashMain onTabSelected", " notify data set changed");
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shuk_dash, menu);
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


    @Override
    protected void onPause() {
        super.onPause();
        Log.i("ShukDashMain","OnPause()");
        int tabNum = tabLayout.getSelectedTabPosition();
        Log.i("ShukDashMain","OnPause() tabnum = "+tabNum);
        //to maintain the correct answers displayed for the values in the tabtodo textview these must be reset to 0,
        // otherwise they simply continue to increase in value each time the screen is reloaded.
     //   counterCat1 =0; counterCat2=0; counterCat3=0; counterCat4=0; counterCat5=0; counterCat6=0;
        Log.i("ShukDashMain","OnPause()  set counter ints to 0");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("ShukDashMain","OnStop()");
        int tabNum = tabLayout.getSelectedTabPosition();
        Log.i("ShukDashMain","OnStop() tabnum = "+tabNum);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("ShukDashMain","OnDestroy()");
    }

    private class AsyncGetData extends AsyncTask<Void ,Void ,List<CatDetailsData> >{
        private final RecyclerView recylceV;
        private final Context context;
        private MissionDisplayDataAdapter missionDisplayRecyclerAdapter;

        public AsyncGetData(RecyclerView recylceV, MissionDisplayDataAdapter missionDisplayRecyclerAdapter, Context context) {
            this.context = context;
            this.recylceV = recylceV;
            this.missionDisplayRecyclerAdapter = missionDisplayRecyclerAdapter;
        }

        @Override
        protected List<CatDetailsData> doInBackground(Void... params) {
            Log.i("ShukDashMain", "AsyncTask do in background .......Start recycler process");
            dataFromDB = sdmPres.getMissionTasksList(c, dataFromDB);
            Log.i("ShukDashMain", "AsyncTask do in background .......send data to on post execute");
            if(dataFromDB.size()>0) {
                Log.i("ShukDashMain AsyncTask do in background .......", " checking data from BD : " + dataFromDB.get(0).getCategoryName() + " " + dataFromDB.get(0).getCatLength() + " " + dataFromDB.get(0).getCategoryCode() + " " + dataFromDB.get(0).getDescription());
             //   missionDisplayRecyclerAdapter.notifyDataSetChanged();

                //   Log.i("MissionDisplayDataAdapter constructor", "datafrom BD : " + dataFromDB.get(1).getCategoryName() + " " + dataFromDB.get(1).getCatLength() + " " + dataFromDB.get(1).getCategoryCode() + " " + dataFromDB.get(1).getDescription());

                //    Log.i("MissionDisplayDataAdapter constructor", "datafrom BD : " + dataFromDB.get(2).getCategoryName() + " " + dataFromDB.get(2).getCatLength() + " " + dataFromDB.get(2).getCategoryCode() + " " + dataFromDB.get(2).getDescription());
            }
            return dataFromDB;
        }

        @Override
        protected void onPostExecute(List<CatDetailsData> catDetailsDatas) {
            super.onPostExecute(catDetailsDatas);
            Log.i("ShukDashMain", "AsyncTask on post execute .......Start recycler process");
            if(catDetailsDatas.size()>0) {
                Log.i("ShukDashMain AsyncTask on post execute .......", " checking data from BD : " + catDetailsDatas.get(0).getCategoryName() + " " + catDetailsDatas.get(0).getCatLength() + " " + catDetailsDatas.get(0).getCategoryCode() + " " + catDetailsDatas.get(0).getDescription());

                //   Log.i("MissionDisplayDataAdapter constructor", "datafrom BD : " + dataFromDB.get(1).getCategoryName() + " " + dataFromDB.get(1).getCatLength() + " " + dataFromDB.get(1).getCategoryCode() + " " + dataFromDB.get(1).getDescription());

                //    Log.i("MissionDisplayDataAdapter constructor", "datafrom BD : " + dataFromDB.get(2).getCategoryName() + " " + dataFromDB.get(2).getCatLength() + " " + dataFromDB.get(2).getCategoryCode() + " " + dataFromDB.get(2).getDescription());
            }
            recyclerView.setHasFixedSize(true);
            linLayRecycler = new LinearLayoutManager(context, 0, false);
            recyclerView.setLayoutManager(linLayRecycler);

         //   missionDisplayRecyclerAdapter = new MissionDisplayDataAdapter(context, catDetailsDatas);

            recyclerView.setAdapter(missionDisplayRecyclerAdapter);

           missionDisplayRecyclerAdapter.notifyDataSetChanged();

            //////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //   adding code from oncreate() to see if it solves crashing problem                   /////////////////////
            /////////////////////////////////////////////////////////////////////////////////////////////////////////////

            Log.i("ShukDashMain", "Oncreate() get new data from db for dataFromDB");


            Log.i("ShukDashMain", "OnCreate() initial get from db completed in thread!!!!!!!!!");


            Log.i("ShukDashMain", "OnCreate() size of data is "+dataFromDB.size());

            catNums = db.getNumOfTasks();


            ////////////////////////////////////
            //set up tabs
            tabCatViewPagerAdapter = new TabCategoryViewPagerAdapter(getSupportFragmentManager());
            //   viewPager.setAdapter(tabCatViewPagerAdapter);
            //  TabLayout.Tab[] tabs = new TabLayout.Tab[5];
            TabLayout.Tab special = tabLayout.newTab();
            TabLayout.Tab meetGreet = tabLayout.newTab();
            TabLayout.Tab shuktionary = tabLayout.newTab();
            TabLayout.Tab navigate = tabLayout.newTab();
            TabLayout.Tab snap = tabLayout.newTab();
            //    TabLayout.Tab perform = tabLayout.newTab();

            View firstTabCustomV = new View(context);
            firstTabCustomV.setBackgroundColor(getResources().getColor(R.color.catTabArts));
            //TextView txtVMissionsTotPerCat = (TextView)firstTabCustomV.findViewById(R.id.TxtVDone);
            // txtVMissionsTotPerCat.setText("33");
            View secondTabCustomV = new View(context);
            secondTabCustomV.setBackgroundColor(getResources().getColor(R.color.catTabDash));

            View thirdTabCustomV = new View(context);
            thirdTabCustomV.setBackgroundColor(getResources().getColor(R.color.catTabShuktion));

            View forthTabCustomV = new View(context);
            forthTabCustomV.setBackgroundColor(getResources().getColor(R.color.catTabMeet));

            View fifthTabCustomV = new View(context);
            fifthTabCustomV.setBackgroundColor(getResources().getColor(R.color.catTabGR8));

            //    View sixthTabCustomV = new View(this);
            //    sixthTabCustomV.setBackgroundColor(getResources().getColor(R.color.catTabSnap));

            //  TabLayout.Tab firstTab = tabLayout.newTab().setText("FirstTAb").setCustomView(R.layout.tabcat1);
            tabLayout.setSelectedTabIndicatorColor(Color.CYAN);

            special.setCustomView(R.layout.tabcat1);
            //special.setText("Special");
            // meetGreet.setText("MeetAndGreet");
            meetGreet.setCustomView(R.layout.tabcat2);
            // shuktionary.setText("Shuktionary");
            shuktionary.setCustomView(R.layout.tabcat3);
            // navigate.setText("Navigate");
            navigate.setCustomView(R.layout.tabcat4);
            // snap.setText("SNAP");
            snap.setCustomView(R.layout.tabcat5);
            // perform.setText("Performing Arts");
            //   perform.setCustomView(R.layout.tabcat6);

            tabLayout.setBackgroundColor(Color.DKGRAY);
            tabLayout.addTab(special, 0);
            tabLayout.addTab(meetGreet, 1);
            tabLayout.addTab(shuktionary, 2);
            tabLayout.addTab(navigate, 3);
            tabLayout.addTab(snap, 4);
            // tabLayout.addTab(perform, 5);

            //   int num1 =1;
            // num1 = dataFromDB.get(1).getCatLength();
            //   String tabNum1 = String.valueOf(num1);
            //   int num2 =1;
            // num2 = dataFromDB.get(2).getCatLength();
            // String tabNum2 = String.valueOf(num2);
            //  int num3 =1;
            //  num3 = dataFromDB.get(3).getCatLength();
            //  String tabNum3 = String.valueOf(num3);

            TextView txtVMissionsTotPerCat1 = (TextView) findViewById(R.id.TxtVDoneTab1);
            txtVMissionsTotPerCat1.setText(catNums.get(0));   //TODO: this sometimes causes a crash as I need to ensure all the screen sizes are configured
            TextView txtVMissionsTotPerCat2 = (TextView) findViewById(R.id.TxtVDoneTab2);
            txtVMissionsTotPerCat2.setText(catNums.get(1));
            TextView txtVMissionsTotPerCat3 = (TextView) findViewById(R.id.TxtVDoneTab3);
            txtVMissionsTotPerCat3.setText(catNums.get(2));
            TextView txtVMissionsTotPerCat4 = (TextView) findViewById(R.id.TxtVDoneTab4);
            txtVMissionsTotPerCat4.setText(catNums.get(3));
            TextView txtVMissionsTotPerCat5 = (TextView) findViewById(R.id.TxtVDoneTab5);
            txtVMissionsTotPerCat5.setText(catNums.get(4));
            //   TextView txtVMissionsTotPerCat6 = (TextView) findViewById(R.id.TxtVDoneTab6);
            //   txtVMissionsTotPerCat6.setText(catNums.get(5));

            tabLayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));

            tab1TasksCompleted = (TextView) findViewById(R.id.TxtVToDoTab1);
            tab2TasksCompleted = (TextView) findViewById(R.id.TxtVToDoTab2);
            tab3TasksCompleted = (TextView) findViewById(R.id.TxtVToDoTab3);
            tab4TasksCompleted = (TextView) findViewById(R.id.TxtVToDoTab4);
            tab5TasksCompleted = (TextView) findViewById(R.id.TxtVToDoTab5);
            //   tab6TasksCompleted = (TextView) findViewById(R.id.TxtVToDoTab6);

            btnEndGame = (ImageButton) findViewById(R.id.imgBtnEndGame);
            //  viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

            Log.i("ShukDashMain", "OnCreate() get new data from db for dataFromDB");
            //  dataFromDB = sdmPres.getMissionTasksList(c, dataFromDB);
            //////////////this was already called earlier ///////////////////////
            Log.i("ShukDashMain", "OnCreate() recyclerview should be correctly updated!!");

            /////////////////////////////////////////////////////////////////////////
            // i will move the recycler intilisation to onresume
            //////////////////////////////////////////////////////
/*
        recyclerView.setHasFixedSize(true);
        linLayRecycler = new LinearLayoutManager(this, 0, false);
        recyclerView.setLayoutManager(linLayRecycler);

        missionDisplayRecyclerAdapter = new MissionDisplayDataAdapter(this, dataFromDB);

        recyclerView.setAdapter(missionDisplayRecyclerAdapter);
*/
            //missionDisplayRecyclerAdapter.notifyDataSetChanged();
            Log.i("ShukDashMain", "OnCreate() db size "+dataFromDB.size());

            ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //    end of copied section from oncreate()                                         ////////////////////////////
            //////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            Log.i("ShukDashMain", "AsyncTask on post execute .......endding  recycler process");
        }
    }

}
// ShukDashPresenter presenter = new ShukDashPresenter(getApplicationContext());
/*
 public List<String> catNameData;
    public List<String> numOfTasksData;
    public List<String[]> isAnsweredData ;
    public RecyclerView catRCV;
    public CategoryRecyclerAdapter recyclData;
    public RecyclerView.LayoutManager mLayoutManager;
    private ViewPager viewPager;

 //this fills the recycler view with the data for categories to be displayed in the cards
        catRCV = (RecyclerView)findViewById(R.id.recyclerVShukDashCats);
        catRCV.setHasFixedSize(true);

        db = new ShukDashDB(this);

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


        Log.i("Parse Category Fragment", "adapter start");
        recyclData = new CategoryRecyclerAdapter(this,
                catNameData  , numOfTasksData,isAnsweredData,dataReturned);
        Log.i("Parse Category Fragment", "adapter start");

        mLayoutManager = new LinearLayoutManager(this);
        catRCV.setLayoutManager(mLayoutManager);

        catRCV.setAdapter(recyclData);
        Log.i("Parse Category Fragment", "adapter start");
*/
/*
                c = db.getTaskListDetails(catNum);
                dataFromDB.clear();
                missionDisplayRecyclerAdapter.notifyDataSetChanged();
                dataFromDB = sdmPres.getMissionTasksList(c, dataFromDB);

                missionDisplayRecyclerAdapter.notifyDataSetChanged();
                switch (tab.getPosition()) {
                    case 0:
                        recyclerView.setBackgroundColor(getResources().getColor(R.color.catTabArts));
                        missionDisplayRecyclerAdapter.setCatNumFromAdapter(1);
                        break;
                    case 1:
                        recyclerView.setBackgroundColor(getResources().getColor(R.color.catTabDash));
                        missionDisplayRecyclerAdapter.setCatNumFromAdapter(2);
                        break;
                    case 2:
                        recyclerView.setBackgroundColor(getResources().getColor(R.color.catTabMeet));
                        missionDisplayRecyclerAdapter.setCatNumFromAdapter(3);
                        break;
                    case 3:
                        recyclerView.setBackgroundColor(getResources().getColor(R.color.catTabShuktion));
                        missionDisplayRecyclerAdapter.setCatNumFromAdapter(4);
                        break;
                    case 4:
                        recyclerView.setBackgroundColor(getResources().getColor(R.color.catTabGR8));
                        missionDisplayRecyclerAdapter.setCatNumFromAdapter(5);
                        break;
                    case 5:
                        recyclerView.setBackgroundColor(getResources().getColor(R.color.catTabSnap));
                        missionDisplayRecyclerAdapter.setCatNumFromAdapter(6);
                        break;

                }
                Log.i("ShukDashMain onTabSelected", " notify data set changed");
                */