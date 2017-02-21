package com.app.shukdash.Adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.shukdash.Models.CatDetailsData;
import com.app.shukdash.UseCases.StoreCatNumFromAdapter;
import com.app.shukdash.Views.TaskMissionPhoto;
import com.app.shukdash.Views.TaskMissionText;
import com.example.shukdash.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danieltarlow on 8/17/16.
 */
public class MissionDisplayDataAdapter extends RecyclerView.Adapter<MissionDisplayDataAdapter.MissionDisplayViewHolder> {
    Context context;
   // CatDetailsData initData, initData1;
    private LayoutInflater inflater;
    List<CatDetailsData> data1= new ArrayList<>();
    //TabLayout tabLayout;
    int textAns;
    int itemCount;
    int photoAns;
    private int catNumFromAdapter;

    public MissionDisplayDataAdapter(Context context, List<CatDetailsData> dataFromDB) {
        this.context = context;
        inflater = LayoutInflater.from(context);
       // data1 = new ArrayList<>();
       // Log.i("MissionDisplayDataAdapter constructor", "datafrom BD : " + dataFromDB.get(0).getCategoryName() + " " + dataFromDB.get(0).getCatLength() + " " + dataFromDB.get(0).getCategoryCode() + " " + dataFromDB.get(0).getDescription());

     //   Log.i("MissionDisplayDataAdapter constructor", "datafrom BD : " + dataFromDB.get(1).getCategoryName() + " " + dataFromDB.get(1).getCatLength() + " " + dataFromDB.get(1).getCategoryCode() + " " + dataFromDB.get(1).getDescription());

    //    Log.i("MissionDisplayDataAdapter constructor", "datafrom BD : " + dataFromDB.get(2).getCategoryName() + " " + dataFromDB.get(2).getCatLength() + " " + dataFromDB.get(2).getCategoryCode() + " " + dataFromDB.get(2).getDescription());

        //get details for specific category from db
        this.data1 = dataFromDB;
       // notifyDataSetChanged();
        Log.i("MissionDisplayDataAdapter constructor", "datafrom BD : " + data1.size());
        itemCount = dataFromDB.size();
        Log.i("MissionDisplayDataAdapter constructor", "datafrom BD : " + itemCount);
        catNumFromAdapter = 0;
    }


    @Override
    public MissionDisplayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        itemCount= data1.size();
        View view = inflater.inflate(R.layout.missions_display, parent, false);
        MissionDisplayViewHolder display = new MissionDisplayViewHolder(view);
        return display;
    }

    String picFromCard;
    int drawLoc;

    @Override
    public void onBindViewHolder(final MissionDisplayViewHolder holder, final int position) {
      //  final CatDetailsData localData1 = dataFr

        final CatDetailsData localData = data1.get(position);
        Log.i("onBindViewHolder", " location id = " + drawLoc);
        // localData.getIsPhotoAnswer();
        // localData.getIsTextAnswer();
        catNumFromAdapter = getCatNumFromAdapter();
        Log.i("onBindViewHolder", " catnumfromadapter = " + catNumFromAdapter);
        drawLoc = 0;

        //   final String fileLocation="";
        String fileLocCheck = data1.get(position).getFileLocation();
        String fileLocation = "";
        if (fileLocCheck != null) {
            fileLocation = fileLocCheck;
            Log.i("onBindViewHolder", "start fileLocation: " + fileLocation);
        }
        ;
        Log.i("onBindViewHolder", "start fileLocation: " + fileLocation);
        final String fileLocationCopy = fileLocation;

        Log.i("onBindViewHolder", " location id = " + drawLoc);
        int pointNum = localData.getPoints();
        final String p = String.valueOf(localData.getPoints());
        Resources res = context.getResources();
        final String point = String.format(res.getString(R.string.point1InCardView), p);
        String points = String.format(res.getString(R.string.pointsInCardView), p);

        Log.i("onBindViewHolder", "num task for pos: " + position + " " + localData.getNumTasks());
        Log.i("onBindViewHolder", "points for pos: " + position + " " + localData.getPoints());
        Log.i("onBindViewHolder", "description for pos: " + position + " " + localData.getDescription());

        holder.taskNum.setText(String.valueOf(data1.get(position).getNumTasks()));

        if (pointNum == 1) {
            holder.points.setText(point);
        } else {
            holder.points.setText(points);
        }
        holder.description.setText(data1.get(position).getDescription());
        holder.description.setMinHeight(150);
        // holder.missionsCrd.setBackgroundColor(Color.BLUE);
        holder.missionsCrd.setCardElevation(8);
        // holder.missionsCrd.setPadding(5,10,5,10);
        holder.missionsCrd.setRadius(10);


        textAns = data1.get(position).getIsTextAnswer();
        photoAns = data1.get(position).getIsPhotoAnswer();
        Log.i("onBindViewHolder", "photoanswer: " + photoAns + " text answer: " + textAns);
        if (textAns == 1) {
            Drawable drawable = res.getDrawable(R.drawable.writenew, null);
            VectorDrawable vDraw = (VectorDrawable) drawable;
            holder.img.setImageDrawable(vDraw);
        } else if (photoAns == 1) {
            Drawable drawable = res.getDrawable(R.drawable.photo_camera1, null);
            VectorDrawable vDraw = (VectorDrawable) drawable;
            holder.img.setImageDrawable(vDraw);
        }
        Log.i("onBindViewHolder", "photoanswer: " + photoAns + " catnumfrom adapter: " + catNumFromAdapter);


        if (catNumFromAdapter == 6) {
            Log.i("onBindViewHolder", "imgVSnap category " + catNumFromAdapter);
            Log.i("onBindViewHolder", "imgVSnap tasknum " + localData.getNumTasks());

            // if (data1.get(position).getNumTasks()<6){
            Log.i("onBindViewHolder", "imgVSnap tasknum " + data1.get(position).getNumTasks());
            int snapNum = data1.get(position).getNumTasks();
            holder.description.setMinHeight(50);

            RelativeLayout.LayoutParams layoutparams = new RelativeLayout.LayoutParams(350, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutparams.setMargins(10, 20, 10, 0);
            layoutparams.addRule(RelativeLayout.BELOW, R.id.viewBelowImgBorder);
            holder.description.setLayoutParams(layoutparams);

            RelativeLayout.LayoutParams layoutparamsImg = new RelativeLayout.LayoutParams(350, 650);
            layoutparamsImg.setMargins(10, 5, 10, 20);
            layoutparamsImg.addRule(RelativeLayout.BELOW, R.id.txtVDescription);
            holder.imgSnapPic.setLayoutParams(layoutparamsImg);


            switch (snapNum) {

                case 1:
                    Log.i("onBindViewHolder", "imgVsnap case 1");
                    Drawable pic2 = context.getResources().getDrawable(R.drawable.shuksnap9, null);
                    drawLoc = R.drawable.shuksnap9;
                    Log.i("onBindViewHolder", "imgVsnap case 1 location id = " + drawLoc);
                    holder.imgSnapPic.setVisibility(View.VISIBLE);
                    holder.imgSnapPic.setImageDrawable(pic2);
                    picFromCard = "david_dagim";
                    break;
                case 2:
                    Log.i("onBindViewHolder", "imgVsnap case 2");
                    Drawable pic3 = context.getResources().getDrawable(R.drawable.shuksnap11_900x600, null);
                    drawLoc = R.drawable.shuksnap11_900x600;
                    Log.i("onBindViewHolder", "imgVsnap case 2 location id = " + drawLoc);
                    //pic3.setBounds(0,0,650,650);
                    holder.imgSnapPic.setVisibility(View.VISIBLE);
                    // holder.imgSnapPic.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
                    holder.imgSnapPic.setImageDrawable(pic3);
                    picFromCard = "olam_umeloo";
                    break;
                case 3:
                    Log.i("onBindViewHolder", "imgVsnap case 3");
                    Drawable pic1 = context.getResources().getDrawable(R.drawable.shuksnap1_900x600, null);
                    drawLoc = R.drawable.shuksnap1_900x600;
                    Log.i("onBindViewHolder", "imgVsnap case 3 location id = " + drawLoc);
                    holder.imgSnapPic.setVisibility(View.VISIBLE);
                    holder.imgSnapPic.setImageDrawable(pic1);
                    picFromCard = "shuksnap1_900x600";
                    break;
                case 4:
                    Log.i("onBindViewHolder", "imgVsnap case 4");
                    Drawable pic4 = context.getResources().getDrawable(R.drawable.shuksnap10, null);
                    drawLoc = R.drawable.shuksnap10;
                    Log.i("onBindViewHolder", "imgVsnap case 4 location id = " + drawLoc);
                    holder.imgSnapPic.setVisibility(View.VISIBLE);
                    holder.imgSnapPic.setImageDrawable(pic4);
                    picFromCard = "shuksnap10";
                    break;
                case 5:
                    Log.i("onBindViewHolder", "imgVsnap case 5");
                    Drawable pic5 = context.getResources().getDrawable(R.drawable.shuksnap6, null);
                    drawLoc = R.drawable.shuksnap6;
                    Log.i("onBindViewHolder", "imgVsnap case 5 location id = " + drawLoc);
                    holder.imgSnapPic.setVisibility(View.VISIBLE);
                    holder.imgSnapPic.setImageDrawable(pic5);
                    picFromCard = "teff_flour";
                    break;
                case 6:
                    Log.i("onBindViewHolder", "imgVsnap case 6");
                    Drawable pic6 = context.getResources().getDrawable(R.drawable.shuksnap7, null);
                    drawLoc = R.drawable.shuksnap7;
                    Log.i("onBindViewHolder", "imgVsnap case 6 location id = " + drawLoc);
                    holder.imgSnapPic.setVisibility(View.VISIBLE);
                    holder.imgSnapPic.setImageDrawable(pic6);
                    picFromCard = "shuksnap7";
                    break;
                case 7:
                    Log.i("onBindViewHolder", "imgVsnap case 7");
                    Drawable pic7 = context.getResources().getDrawable(R.drawable.shuksnap2_900x500, null);
                    drawLoc = R.drawable.shuksnap2_900x500;
                    Log.i("onBindViewHolder", "imgVsnap case 7 location id = " + drawLoc);
                    holder.imgSnapPic.setVisibility(View.VISIBLE);
                    holder.imgSnapPic.setImageDrawable(pic7);
                    picFromCard = "shuksnap2_900x600";
                    break;
                case 8:
                    Log.i("onBindViewHolder", "imgVsnap case 8");
                    Drawable pic8 = context.getResources().getDrawable(R.drawable.shuksnap3_900x600, null);
                    drawLoc = R.drawable.shuksnap3_900x600;
                    Log.i("onBindViewHolder", "imgVsnap case 8 location id = " + drawLoc);
                    holder.imgSnapPic.setVisibility(View.VISIBLE);
                    holder.imgSnapPic.setImageDrawable(pic8);
                    picFromCard = "shuksnap3_900x600";
                    break;
                case 9:
                    Log.i("onBindViewHolder", "imgVsnap case 9");
                    Drawable pic9 = context.getResources().getDrawable(R.drawable.shuksnap4_900x500, null);
                    drawLoc = R.drawable.shuksnap4_900x500;
                    Log.i("onBindViewHolder", "imgVsnap case 9 location id = " + drawLoc);
                    holder.imgSnapPic.setVisibility(View.VISIBLE);
                    holder.imgSnapPic.setImageDrawable(pic9);
                    picFromCard = "shuksnap4_900x600";
                    break;
                case 10:
                    Log.i("onBindViewHolder", "imgVsnap case 10");
                    Drawable pic10 = context.getResources().getDrawable(R.drawable.shuksnap5_900x600, null);
                    drawLoc = R.drawable.shuksnap5_900x600;
                    Log.i("onBindViewHolder", "imgVsnap case 10 location id = " + drawLoc);
                    holder.imgSnapPic.setVisibility(View.VISIBLE);
                    holder.imgSnapPic.setImageDrawable(pic10);
                    picFromCard = "shuksnap5_900x600";
                    break;


            }
        }
        ////////////////////////////////////////////////////////
        //this set the imgsnappic view to imvisible so if you wish to add to another card the code
        // miust be after this otherwise this next section of code will hide anything you add in placed above this

        if (catNumFromAdapter != 6) {
            holder.imgSnapPic.setVisibility(View.INVISIBLE);
        }


        /////////////////////////////////////////////////
        //Add pictures to card if it has one

        Log.i("onBindViewHolder", "1 fileLocation: " + fileLocation);
        if (fileLocation.equals("olam_umeloo") || fileLocation.equals("david_dagim") || fileLocation.equals("teff_flour")) {
            Log.i("onBindViewHolder", "imgVSnap category " + catNumFromAdapter);
            Log.i("onBindViewHolder", "imgVSnap tasknum " + localData.getNumTasks());

            //   Log.i("onBindViewHolder","imgVSnap tasknum "+data1.get(position).getNumTasks());
            // int snapNum  = data1.get(position).getNumTasks();
            holder.description.setMinHeight(50);

            RelativeLayout.LayoutParams layoutparams = new RelativeLayout.LayoutParams(350, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutparams.setMargins(10, 20, 10, 0);
            layoutparams.addRule(RelativeLayout.BELOW, R.id.viewBelowImgBorder);
            holder.description.setLayoutParams(layoutparams);

            RelativeLayout.LayoutParams layoutparamsImg = new RelativeLayout.LayoutParams(350, 650);
            layoutparamsImg.setMargins(10, 5, 10, 20);
            layoutparamsImg.addRule(RelativeLayout.BELOW, R.id.txtVDescription);
            holder.imgSnapPic.setLayoutParams(layoutparamsImg);

            Log.i("onBindViewHolder", "fileLocation: ");

            if (fileLocation.equals("olam_umeloo")) {
                Log.i("onBindViewHolder", "fileLocation: olam_umeloo " + fileLocation);
                Log.i("onBindViewHolder", "fileLocation: " + fileLocation);
                Log.i("onBindViewHolder", "drawable setup ");
                Drawable meetGreetpic = context.getResources().getDrawable(R.drawable.olam_umeloo, null);
                Log.i("onBindViewHolder", "fileLocation: asign drawloc");
                drawLoc = R.drawable.olam_umeloo;
                Log.i("onBindViewHolder", "imgVsnap meetgreet pic 9 location id = " + drawLoc);
                holder.imgSnapPic.setVisibility(View.VISIBLE);
                Log.i("onBindViewHolder", "fileLocation: set visibility");
                holder.imgSnapPic.setImageDrawable(meetGreetpic);
                Log.i("onBindViewHolder", "fileLocation: set imagedrawable");
                picFromCard = "olam_umeloo";

            } else if (fileLocation.equals("david_dagim")) {
                Log.i("onBindViewHolder", "fileLocation: david_dagim " + fileLocation);
                Log.i("onBindViewHolder", "drawable setup ");
                Drawable meetGreetpic = context.getResources().getDrawable(R.drawable.david_dagim, null);
                Log.i("onBindViewHolder", "fileLocation: asign drawloc");
                drawLoc = R.drawable.david_dagim;
                Log.i("onBindViewHolder", "imgVsnap  meetgreet pic 10 location id = " + drawLoc);
                holder.imgSnapPic.setVisibility(View.VISIBLE);
                Log.i("onBindViewHolder", "fileLocation: set visibility");
                holder.imgSnapPic.setImageDrawable(meetGreetpic);
                Log.i("onBindViewHolder", "fileLocation: set imagedrawable");
                picFromCard = "david_dagim";
            } else if (fileLocation.equals("teff_flour")) {
                Log.i("onBindViewHolder", "fileLocation: teff_flour " + fileLocation);
                Log.i("onBindViewHolder", "fileLocation: " + fileLocation);
                Log.i("onBindViewHolder", "drawable setup ");
                Drawable meetGreetpic = context.getResources().getDrawable(R.drawable.teff_flour, null);
                Log.i("onBindViewHolder", "fileLocation: asign drawloc");
                drawLoc = R.drawable.teff_flour;
                Log.i("onBindViewHolder", "imgVsnap meetgreet pic 9 location id = " + drawLoc);
                holder.imgSnapPic.setVisibility(View.VISIBLE);
                Log.i("onBindViewHolder", "fileLocation: set visibility");
                holder.imgSnapPic.setImageDrawable(meetGreetpic);
                Log.i("onBindViewHolder", "fileLocation: set imagedrawable");
                picFromCard = "teff_flour";

            }
        }

        ////////////////////////////////////////
        //set click listener for each cardview
        ///////////////////////////////////////
        //use observer to send value of catnum and the listener for updating
        holder.missionsCrd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textAns = data1.get(position).getIsTextAnswer();
                photoAns = data1.get(position).getIsPhotoAnswer();
                String ii = String.valueOf(getCatNumFromAdapter()); //tabLayout.get();

                Log.i("ShukDashMain mission card ontouchlistener", "" + v.getId() + " adapterposition " + holder.getAdapterPosition()
                        + " layoutposition " + position + " tab position " + ii + " textAns " + textAns + " photoAnswer " + photoAns);

                Intent i;// =new Intent(context, TaskMissionText.class); //not sure this is correct way to intialise

                if (textAns == 1) {
                    i = new Intent(context, TaskMissionText.class);
                    Log.i("ShukDashMain mission card ontouchlistener", "textans = 1 Intent set for taskmissiontext");
                    if (fileLocationCopy.equals("olam_umeloo")) {
                        Log.i("ShukDashMain mission card ontouchlistener", "textans = 1 Intent set for taskmissiontextWithPic");
                        i.putExtra("fileLocation", fileLocationCopy);
                        i.putExtra("picDrawable", picFromCard);
                        drawLoc = R.drawable.olam_umeloo;
                        i.putExtra("locationID", drawLoc);
                        Log.i("onBindViewHolder", "imgVsnap OlamUmLoo location id = " + drawLoc);
                    }
                    if (fileLocationCopy.equals("david_dagim")) {
                        Log.i("ShukDashMain mission card ontouchlistener", "textans = 1 Intent set for taskmissiontextWithPic");
                        i.putExtra("fileLocation", fileLocationCopy);
                        i.putExtra("picDrawable", picFromCard);
                        drawLoc = R.drawable.david_dagim;
                        i.putExtra("locationID", drawLoc);
                        Log.i("onBindViewHolder", "imgVsnap daviddagim location id = " + drawLoc);
                    }
                    if (fileLocationCopy.equals("teff_flour")) {
                        Log.i("ShukDashMain mission card ontouchlistener", "textans = 1 Intent set for taskmissiontextWithPic");
                        i.putExtra("fileLocation", fileLocationCopy);
                        i.putExtra("picDrawable", picFromCard);
                        drawLoc = R.drawable.teff_flour;
                        i.putExtra("locationID", drawLoc);
                        Log.i("onBindViewHolder", "imgVsnap daviddagim location id = " + drawLoc);
                    }
                } else if (photoAns == 1) {
                    Log.i("ShukDashMain mission card ontouchlistener", "PHOTOans = 1 Intent set for photomissiontext");
                    i = new Intent(context, TaskMissionPhoto.class);
                    i.putExtra("Description", localData.getDescription());
                    if (catNumFromAdapter == 6) {
                        Log.i("ShukDashMain mission card ontouchlistener", "PHOTOans = 1 catNumFromAdapter==6");
                        int pos = position + 1;
                        i.putExtra("picNum", position);

                        i.putExtra("picDrawable", picFromCard);
                        Log.i("ShukDashMain mission card ontouchlistener", "drawLoc " + drawLoc);
                        switch (pos) {

                            case 1:
                                Log.i("onBindViewHolder mission card ontouchlistener", "imgVsnap case 1");
                                //   Drawable pic2 = context.getResources().getDrawable(R.drawable.david_dagim,null);
                                drawLoc = R.drawable.shuksnap9;
                                Log.i("onBindViewHolder", "imgVsnap case 1 location id = " + drawLoc);

                                break;
                            case 2:
                                Log.i("onBindViewHolder", "imgVsnap case 2");
                                //  Drawable pic3 = context.getResources().getDrawable(R.drawable.olam_umeloo,null);
                                drawLoc = R.drawable.shuksnap11_900x600;
                                Log.i("onBindViewHolder", "imgVsnap case 2 location id = " + drawLoc);

                                break;
                            case 3:
                                Log.i("onBindViewHolder", "imgVsnap case 3");
                                //Drawable pic1 = context.getResources().getDrawable(R.drawable.shuksnap1_900x600,null);
                                drawLoc = R.drawable.shuksnap1_900x600;
                                Log.i("onBindViewHolder", "imgVsnap case 3 location id = " + drawLoc);
                                break;
                            case 4:
                                Log.i("onBindViewHolder", "imgVsnap case 4");
                                //  Drawable pic4 = context.getResources().getDrawable(R.drawable.shuksnap10,null);
                                drawLoc = R.drawable.shuksnap10;
                                Log.i("onBindViewHolder", "imgVsnap case 4 location id = " + drawLoc);

                                break;
                            case 5:
                                Log.i("onBindViewHolder", "imgVsnap case 5");
                                // Drawable pic5 = context.getResources().getDrawable(R.drawable.teff_flour,null);
                                drawLoc = R.drawable.shuksnap6;
                                Log.i("onBindViewHolder", "imgVsnap case 5 location id = " + drawLoc);

                                break;
                            case 6:
                                Log.i("onBindViewHolder", "imgVsnap case 6");
                                //  Drawable pic6 = context.getResources().getDrawable(R.drawable.shuksnap7,null);
                                drawLoc = R.drawable.shuksnap7;
                                Log.i("onBindViewHolder", "imgVsnap case 6 location id = " + drawLoc);

                                break;
                            case 7:
                                Log.i("onBindViewHolder", "imgVsnap case 7");
                                // Drawable pic7 = context.getResources().getDrawable(R.drawable.shuksnap2_900x500,null);
                                drawLoc = R.drawable.shuksnap2_900x500;
                                Log.i("onBindViewHolder", "imgVsnap case 7 location id = " + drawLoc);

                                break;
                            case 8:
                                Log.i("onBindViewHolder", "imgVsnap case 8");
                                // Drawable pic8 = context.getResources().getDrawable(R.drawable.shuksnap3_900x600,null);
                                drawLoc = R.drawable.shuksnap3_900x600;
                                Log.i("onBindViewHolder", "imgVsnap case 8 location id = " + drawLoc);

                                break;
                            case 9:
                                Log.i("onBindViewHolder", "imgVsnap case 9");
                                //  Drawable pic9 = context.getResources().getDrawable(R.drawable.shuksnap4_900x500,null);
                                drawLoc = R.drawable.shuksnap4_900x500;
                                Log.i("onBindViewHolder", "imgVsnap case 9 location id = " + drawLoc);

                                break;
                            case 10:
                                Log.i("onBindViewHolder", "imgVsnap case 10");
                                // Drawable pic10 = context.getResources().getDrawable(R.drawable.shuksnap5_900x600,null);
                                drawLoc = R.drawable.shuksnap5_900x600;
                                Log.i("onBindViewHolder", "imgVsnap case 10 location id = " + drawLoc);

                                break;
                            //addition to description//
// \n\nTo enlarge the picture press on it for a second or two(You cannot take a photo whilst the picture is enlarged).\n\n To return to the original version press the picture again for a second or two.//
                        }


                        i.putExtra("Description", localData.getDescription() + "\n\nPress on the picture for 2 seconds to enlarge or reduce it's size.");
                        i.putExtra("locationID", drawLoc);
                    }

                    if (catNumFromAdapter == 2) {
                        Log.i("ShukDashMain mission card ontouchlistener", "PHOTOans = 1 catNumFromAdapter==6");
                        Log.i("ShukDashMain mission card ontouchlistener", "photoAns = 1 Intent set for TaskMissionPhoto CatFromAdap =2");
                        Log.i("ShukDashMain mission card ontouchlistener", "photoAns = 1 Intent set for TaskMissionPhoto CatFromAdap =2");
                        Log.i("ShukDashMain mission card ontouchlistener", "photoAns = 1 Intent set for TaskMissionPhoto CatFromAdap =2");
                        Log.i("ShukDashMain mission card ontouchlistener", "photoAns = 1 Intent set for TaskMissionPhoto CatFromAdap =2");

                    }

                    if (catNumFromAdapter == 1) {
                        Log.i("ShukDashMain mission card ontouchlistener", "PHOTOans = 1 catNumFromAdapter==1");
                        Log.i("ShukDashMain mission card ontouchlistener", "photoAns = 1 Intent set for TaskMissionPhoto CatFromAdap =1");
                        Log.i("ShukDashMain mission card ontouchlistener", "photoAns = 1 Intent set for TaskMissionPhoto CatFromAdap =1");
                        Log.i("ShukDashMain mission card ontouchlistener", "photoAns = 1 Intent set for TaskMissionPhoto CatFromAdap =1");
                        Log.i("ShukDashMain mission card ontouchlistener", "photoAns = 1 Intent set for TaskMissionPhoto CatFromAdap =1");

                    }

                    if (catNumFromAdapter == 0) {
                        Log.i("ShukDashMain mission card ontouchlistener", "PHOTOans = 1 catNumFromAdapter==0");
                        Log.i("ShukDashMain mission card ontouchlistener", "photoAns = 1 Intent set for TaskMissionPhoto CatFromAdap =0");
                        Log.i("ShukDashMain mission card ontouchlistener", "photoAns = 1 Intent set for TaskMissionPhoto CatFromAdap =0");
                        Log.i("ShukDashMain mission card ontouchlistener", "photoAns = 1 Intent set for TaskMissionPhoto CatFromAdap =0");
                        Log.i("ShukDashMain mission card ontouchlistener", "photoAns = 1 Intent set for TaskMissionPhoto CatFromAdap =0");

                    }

                    Log.i("ShukDashMain mission card ontouchlistener", "photoAns = 1 Intent set for TaskMissionPhoto");

                } else {
                    i = new Intent(context, TaskMissionPhoto.class);
                    Log.i("ShukDashMain mission card ontouchlistener", "else as default Intent set for TaskMissionPhoto");
                }
                String tasknumtoIntent = String.valueOf((position + 1));
                i.putExtra("CatNum", ii);
                i.putExtra("CatName", localData.getCategoryName());
                i.putExtra("TaskNum", tasknumtoIntent);
                i.putExtra("Points", p);
                // i.putExtra("Description", localData.getDescription());
                Log.i("ShukDashMain mission card ontouchlistener", " CatNum " + localData.getCategoryCode() + " CatName " + localData.getCategoryName()
                        + " TaskNum " + tasknumtoIntent + " Description " + localData.getDescription() + " locationID " + drawLoc);
                context.startActivity(i);
            }
        });

        ////////////////////////////
        // Set foreground of each card if they have been completed
        /////////////////////////////

        int isAnswered = data1.get(position).getIsAnswered();
        Log.i("ShukDashMain mission card ontouchlistener", " isAnswered " + isAnswered);
        if (isAnswered == 1) {
            holder.imgVCompletedTick.setVisibility(View.VISIBLE);
            //   holder.relLayCrdVMissionTaskDisplay.setBackgroundColor(Color.rgb( 45,239,239));
            holder.relLayCrdVMissionTaskDisplay.setBackgroundColor(Color.rgb(140, 234, 175));

        } else if (isAnswered == 0) {
            holder.missionsCrd.setForeground(null);
            holder.imgVCompletedTick.setVisibility(View.INVISIBLE);
            holder.relLayCrdVMissionTaskDisplay.setBackgroundColor(Color.rgb(255, 255, 255));
        }
   /*   if(localData.getIsAnswered()==1) {
            Drawable d2 = res.getDrawable(R.color.catTabMeet, null);
            holder.description.setBackground(d2);
            // holder.description.setForeground(d2);
        }
*/

    }

    @Override
    public int getItemCount() {
        Log.i("MissionDisplayAdapter, getItemCount", " " + String.valueOf(data1.size()));
        Log.i("MissionDisplayAdapter, getItemCount", " " + String.valueOf(itemCount));

        return data1.size();
        /*
        try {

          return data1.size();
        }
        catch (NullPointerException e){
            e.printStackTrace();
            Log.i("MissionDisplayAdapter, getItemCount", e.toString()+" " + data1.size() );
        }
        return 10;
      //  Log.i("MissionDisplayAdapter, getItemCount", " " + String.valueOf(data1.size()));
   //     if (data1.size() == 0) {
         //   return itemCount;
    //    } else
//            return data1.size();
      //  return itemCount;

      */
    }


    public int getCatNumFromAdapter() {

        Log.i("MissionDisplayAdapter, getcatNumFromAdapater", " "+catNumFromAdapter);
       // return catNumFromAdapter;
        return StoreCatNumFromAdapter.getInstance().getCatNumFromAdapter();
    }

    public void setCatNumFromAdapter(int catNumFromAdapter) {
        Log.i("MissionDisplayAdapter, setcatNumFromAdapater", " "+catNumFromAdapter);
        this.catNumFromAdapter = catNumFromAdapter;
    }

    //ViewHolder used to define the view returned with oncreateviewholder
    class MissionDisplayViewHolder extends RecyclerView.ViewHolder {
        CardView missionsCrd;
        TextView taskNum;
        TextView points;
        TextView description;
        ImageView img, imgSnapPic, imgVCompletedTick;
        RelativeLayout relLayCrdVMissionTaskDisplay;
        public MissionDisplayViewHolder(View itemView) {
            super(itemView);
            missionsCrd = (CardView)itemView.findViewById(R.id.CrdViewMissions);
            taskNum= (TextView)itemView.findViewById(R.id.txtVTaskNumber);
            points= (TextView)itemView.findViewById(R.id.txtVPoints);
            description= (TextView)itemView.findViewById(R.id.txtVDescription);
            img = (ImageView)itemView.findViewById(R.id.ImgVCrdVMissionDisplay);
            imgSnapPic = (ImageView)itemView.findViewById(R.id.imgVMissionCardSNAPDisplay);
            relLayCrdVMissionTaskDisplay = (RelativeLayout)itemView.findViewById(R.id.relLayCrdVMissionTaskDisplay);
            imgVCompletedTick = (ImageView)itemView.findViewById(R.id.imgVCompletedTick);
        }
    }

/*    public class CardVDecorator extends RecyclerView.ItemDecoration{
            private Drawable mDrawable;

        public CardVDecorator (Drawable drawable){
            mDrawable = drawable;
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);
       //     parent.setForeground(mDrawable);
            c.drawColor(Color.argb(180, 240,223,208));

            Bitmap bitm = BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_completed, null);
            Paint p = new Paint();
            c.drawBitmap(bitm,100,100, p);
        }
    }
*/}
