package com.app.shukdash.Unused;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.app.shukdash.Adapters.MissionDisplayDataAdapter;
import com.app.shukdash.Adapters.TabCategoryViewPagerAdapter;
import com.app.shukdash.Models.CatDetailsData;
import com.app.shukdash.Models.CategoryRecyclerAdapter;
import com.app.shukdash.Models.ShukDashDB;
import com.example.shukdash.R;

import java.util.ArrayList;
import java.util.List;

public class Shuk_Dash_MainBU extends AppCompatActivity {
    public ShukDashDB db ;

    public List<String> catNameData;
    public List<String> numOfTasksData;
    public List<String[]> isAnsweredData ;
    public RecyclerView catRCV;
    public CategoryRecyclerAdapter recyclData;
    public RecyclerView.LayoutManager mLayoutManager;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabCategoryViewPagerAdapter tabCatViewPagerAdapter;
    private RecyclerView recyclerView;
    private MissionDisplayDataAdapter missionDisplayRecyclerAdapter;
    List<CatDetailsData> dataFromDB;
    LinearLayoutManager linLayRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shuk_dash_main);
        ShukDashDB db = new ShukDashDB(this);
        Cursor c = db.getTaskListDetails(0);
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

        viewPager = (ViewPager)findViewById(R.id.ViewpagerMissionsDisplay);
        tabLayout = (TabLayout)findViewById(R.id.TabLayCategories);
        recyclerView = (RecyclerView) findViewById(R.id.missionDisplayByCatInRecyclerView);

        tabCatViewPagerAdapter = new TabCategoryViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabCatViewPagerAdapter);

        TabLayout.Tab special = tabLayout.newTab();
        TabLayout.Tab meetGreet = tabLayout.newTab();
        TabLayout.Tab shuktionary = tabLayout.newTab();
        TabLayout.Tab navigate = tabLayout.newTab();
        TabLayout.Tab snap = tabLayout.newTab();
        TabLayout.Tab perform = tabLayout.newTab();

        View firstTabCustomV = new View(this);
        firstTabCustomV.setBackgroundColor(getResources().getColor(R.color.catTabArts ));

        View secondTabCustomV = new View(this);
        secondTabCustomV.setBackgroundColor(getResources().getColor(R.color.catTabDash));

        View thirdTabCustomV = new View(this);
        thirdTabCustomV.setBackgroundColor(getResources().getColor(R.color.catTabShuktion));

        View forthTabCustomV = new View (this);
        forthTabCustomV.setBackgroundColor(getResources().getColor(R.color.catTabMeet));

        View fifthTabCustomV = new View (this);
        fifthTabCustomV.setBackgroundColor(getResources().getColor(R.color.catTabGR8));

        View sixthTabCustomV = new View(this);
        sixthTabCustomV.setBackgroundColor(getResources().getColor(R.color.catTabSnap));

        //  TabLayout.Tab firstTab = tabLayout.newTab().setText("FirstTAb").setCustomView(R.layout.tabcat1);
        tabLayout.setSelectedTabIndicatorColor(Color.CYAN);



        special.setCustomView(R.layout.tabcat1);
        special.setText("Special");
        meetGreet.setText("MeetAndGreet");
        meetGreet.setCustomView(R.layout.tabcat2);
        shuktionary.setText("Shuktionary");
        shuktionary.setCustomView(R.layout.tabcat3);
        navigate.setText("Navigate");
        navigate.setCustomView(R.layout.tabcat4);
        snap.setText("SNAP");
        snap.setCustomView(R.layout.tabcat5);
        perform.setText("Performing Arts");
        perform.setCustomView(R.layout.tabcat6);

        tabLayout.setBackgroundColor(Color.DKGRAY);

        tabLayout.addTab(special,0);
        tabLayout.addTab(meetGreet,1);
        tabLayout.addTab(shuktionary,2);
        tabLayout.addTab(navigate,3);
        tabLayout.addTab(snap,4);
        tabLayout.addTab(perform,5);

        tabLayout.setTabTextColors(ColorStateList.valueOf(Color.WHITE));

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        recyclerView.setHasFixedSize(true);
        linLayRecycler = new LinearLayoutManager(this, 0, false);
        recyclerView.setLayoutManager(linLayRecycler);

        missionDisplayRecyclerAdapter = new MissionDisplayDataAdapter(this, dataFromDB);

        recyclerView.setAdapter(missionDisplayRecyclerAdapter);

        Intent j = getIntent();
        String teamName = j.getStringExtra("TeamName");

        // ShukDashPresenter presenter = new ShukDashPresenter(getApplicationContext());
/*      //this fills the recycler view with the data for categories to be displayed in the cards
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
    }


    public int[] exportTasksToInts (List<String> data){

        int[] tasksdata = new int[6];

        for (int i =0; i<data.size(); i++){

            tasksdata[i] = Integer.valueOf(data.get(i));
            Log.i("ShukDash CategoryFragment", "function exporttasks data : position: "+ i + " value "+tasksdata[i]);

        }

        return tasksdata;

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







}
