package com.app.shukdash.Views;

import android.Manifest;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.shukdash.Models.ShukDashDB;
import com.app.shukdash.UseCases.StoreCatNumFromAdapter;
import com.example.shukdash.R;
import com.ragnarok.rxcamera.config.RxCameraConfig;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class TaskMissionPhoto extends AppCompatActivity {
    String catNum,taskNum;
    Activity activity;
    int  catN, taskN;
    boolean isAnswered;
    TextView txtVPhotoText, txtVSaveText;
    ImageView imgVCamera1, imgVPicLarge, imgVMissionPhotoEnlarged ;
    ShukDashDB db;
    Uri picFileLocation;
    private Boolean isFabOpen = false;
    Animation fab_open, fab_close, rotateFrwd, rotateBkwd;
    String result;
    RxCameraConfig config;
    FloatingActionButton FabReturn, fabPhoto, fabSave;
    int drawLoc;
    Drawable d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_mission_photo);

        Log.i("Shukdash TaskMissionPhoto", "onCreate started");
        isAnswered=false;
        TextView txtVDesc = (TextView)findViewById(R.id.txtVDescription);
        TextView txtVtaskNum = (TextView)findViewById(R.id.txtVTaskNumber);
        TextView txtVPoints = (TextView)findViewById(R.id.txtVPoints);
        RelativeLayout outer = (RelativeLayout)findViewById(R.id.outerContainer);
        RelativeLayout inner = (RelativeLayout)findViewById(R.id.innerContainer);
        activity = this;

        ImageView imgV = (ImageView)findViewById(R.id.ImgVTaskMissionTypeDisplay);
        imgVMissionPhotoEnlarged = (ImageView)findViewById(R.id.imgVDisplayEnlargedMissionPhoto);
        imgVPicLarge =(ImageView)findViewById(R.id.imgVDisplayPhotoLarge);
        Drawable drawable = getDrawable(R.drawable.photo_camera2 );
        VectorDrawable vDraw = (VectorDrawable) drawable;
        txtVPhotoText = (TextView)findViewById(R.id.txtVFABPhotoText);
        txtVSaveText = (TextView)findViewById(R.id.txtVFABSaveText);

        imgVCamera1=(ImageView)findViewById(R.id.cameraViewDisplay);
        db = new ShukDashDB(activity.getApplicationContext());

        imgV.setImageDrawable(vDraw);

        Intent i = getIntent();

        //TODO: set default data in case there is No Intent
        int getCatNumFromAdapter = StoreCatNumFromAdapter.getInstance().getCatNumFromAdapter();
        String desc = i.getStringExtra("Description");
        catNum = i.getStringExtra("CatNum");
        String catName = i.getStringExtra("CatName");
        catN = Integer.valueOf(catNum);

        taskNum = i.getStringExtra("TaskNum");
        taskN = Integer.valueOf(taskNum);
        String p = i.getStringExtra("Points");
//        String drawableLoc = i.getStringExtra("picDrawable");
//        String picPosition = i.getStringExtra("picNum");
        imgVPicLarge.setVisibility(View.GONE);

        if(getCatNumFromAdapter==6) {
            drawLoc = i.getIntExtra("locationID", 0);
            Log.i("Shukdash TaskMissionPhoto", "drawLoc " + drawLoc);
            if (drawLoc == 0) {
                drawLoc = 2130837659;
            }
            Log.i("Shukdash TaskMissionPhoto", "drawLoc " + drawLoc);
            d = getResources().getDrawable(drawLoc, null);

            RelativeLayout.LayoutParams layoutparamsImg = new RelativeLayout.LayoutParams(180, 150);
            layoutparamsImg.setMargins(0, 8, 0, 8);
            layoutparamsImg.addRule(RelativeLayout.BELOW, R.id.txtVDescription);
            layoutparamsImg.addRule(RelativeLayout.ALIGN_PARENT_START);
            layoutparamsImg.addRule(RelativeLayout.ALIGN_END, R.id.viewSeparateTaskAnswerBorder);
         //   imgVPic.setLayoutParams(layoutparamsImg);
        //    imgVPic.setImageDrawable(d);
            imgVPicLarge.setVisibility(View.VISIBLE);
       /*     BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;
            Uri uriLrgPic = Uri.fromFile()
            Bitmap bitmap = BitmapFactory.decodeFile(picFileLocation.getPath(), options);
            imgVCamera1.setImageBitmap(bitmap);
         */
            imgVPicLarge.setImageDrawable(d);

        }
        int numPoints = Integer.valueOf(p);
        // Resources res = getResources();

        Log.i("Shukdash TaskMissionPhoto", "onCreate catN= "+catN);
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
/*
        switch (catN){
            case 0:
                outer.setBackgroundColor(getResources().getColor(R.color.catTabArts));
                break;
            case 1:
                outer.setBackgroundColor(getResources().getColor(R.color.catTabDash));
                break;
            case 2:
                outer.setBackgroundColor(getResources().getColor(R.color.catTabMeet));
                break;
            case 3:
                outer.setBackgroundColor(getResources().getColor(R.color.catTabShuktion));
                break;
            case 4:
                outer.setBackgroundColor(getResources().getColor(R.color.catTabGR8));
                break;
            case 5:
                outer.setBackgroundColor(getResources().getColor(R.color.catTabSnap));
                break;

        }

*/
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




    }
    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "Hello Camera";
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("taskmissionphoto", "onStart");
        //TODO: check and get answers stored on db for this question
        //TODO:Check the database to see if there is a previously
        // saved answer and then display that answer in the edittext widget
        //

        int isAnswered = db.getIsCompleted(catN, taskN);

        if (isAnswered==1){
            String savedAnswer=  db.getAnswerForTask(catN, taskN);
            picFileLocation = Uri.parse(savedAnswer);
            BitmapFactory.Options options = new BitmapFactory.Options();

            options.inSampleSize = 8;

            Bitmap bitmap = BitmapFactory.decodeFile(picFileLocation.getPath(), options);
            imgVCamera1.setImageBitmap(bitmap);
        }



        //////////////////////////////////////////////////////////////
        //check to display the saved pic
        // and display in the imageview
   /*     TaskMissionPhotoPresenter presenter = new TaskMissionPhotoPresenter();
        String location = presenter.getPhotoLocation(catN ,taskN, this );
        Log.i("taskmissionphoto", "onStart getphotolocation "+location);
        if(location!=""){
            isAnswered=true;
            //////// make uri from file location data

          //  Uri uri = Uri

            BitmapFactory.Options optionsPic = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            optionsPic.inSampleSize = 8;

            Bitmap bitmapPic = BitmapFactory.decodeFile(picFileLocation.getPath(), optionsPic);
            imgVCamera1.setImageBitmap(bitmapPic);

         //   imgVCamera1.setImageDrawable();
        }
        */
    }





   // SurfaceView imgVCamera;
   // TextureView imgVCamera;
   boolean enlarged;
    @Override
    protected void onResume() {
        super.onResume();

        enlarged = false;
        imgVMissionPhotoEnlarged.setVisibility(View.GONE);
        Log.i("taskmissionphoto", "start");
        isFabOpen=false;
        fab_open = AnimationUtils.loadAnimation(this, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(this, R.anim.fab_close);
        rotateFrwd = AnimationUtils.loadAnimation(this, R.anim.rotate_forward);
        rotateBkwd = AnimationUtils.loadAnimation(this, R.anim.rotate_backward);

     //   imgVCamera = (SurfaceView) findViewById(R.id.surfaceView);
      //  final MagicalCamera magicalCamera = new MagicalCamera(this,50);

        config = new RxCameraConfig.Builder()
                .useBackCamera()
                .setAutoFocus(true)
                .setPreferPreviewFrameRate(15, 30)
                .setPreferPreviewSize(new Point(200, 200), true)
                .setHandleSurfaceEvent(true)
                .build();
        Log.i("taskmissionphoto", "fab");


 /*       FrameLayout.LayoutParams layoutParamsPhoto = (FrameLayout.LayoutParams) fabPhoto.getLayoutParams();
        layoutParamsPhoto.rightMargin += (int) (fabPhoto.getWidth() * 1.7);
        layoutParamsPhoto.bottomMargin += (int) (fabPhoto.getHeight() * 0.25);
        fabPhoto.setLayoutParams(layoutParamsPhoto);

        FrameLayout.LayoutParams layoutParamsSave = (FrameLayout.LayoutParams) fabSave.getLayoutParams();
        layoutParamsSave.rightMargin += (int) (fabSave.getWidth() * 2.5);
        layoutParamsSave.bottomMargin += (int) (fabSave.getHeight() * 0.25);
        fabSave.setLayoutParams(layoutParamsSave);
*/


 imgVPicLarge.setOnLongClickListener(new View.OnLongClickListener() {
     @Override
     public boolean onLongClick(View v) {
         if (!enlarged) {

             imgVMissionPhotoEnlarged.setVisibility(View.VISIBLE);
             imgVMissionPhotoEnlarged.setImageDrawable(d);
                imgVPicLarge.setVisibility(View.INVISIBLE);
              /*      Intent i = new Intent(TaskMissionPhoto.this,TaskMissionPhotoEnlarged.class );
                    ActivityOptionsCompat acc = ActivityOptionsCompat.makeScaleUpAnimation(imgVPicLarge, 100,100,300,300);
                    i.putExtra("DrawableLocation", drawLoc);

                    ActivityCompat.startActivity(TaskMissionPhoto.this, i, acc.toBundle());
                */    //     v.animate().scaleX(2.50f);
             //     v.animate().scaleY(2.50f);
             //     v.animate().start();
             enlarged = true;
         }
         else if (enlarged){

             imgVMissionPhotoEnlarged.setVisibility(View.GONE);
             imgVPicLarge.setVisibility(View.VISIBLE);
             //v.animate().scaleX(0.4f);
             //v.animate().scaleY(0.4f);
             //v.animate().start();
             enlarged = false;

         }
         return true;
     }
 });

        imgVMissionPhotoEnlarged.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (enlarged) {
                    imgVMissionPhotoEnlarged.setVisibility(View.GONE);
                    imgVPicLarge.setVisibility(View.VISIBLE);
                    //v.animate().scaleX(0.4f);
                    //v.animate().scaleY(0.4f);
                    //v.animate().start();
                    enlarged = false;
                }
            return true;
            }
        });
      /*  imgVPicLarge.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                if (!enlarged) {

                    imgVMissionPhotoEnlarged.setVisibility(View.VISIBLE);
                    imgVMissionPhotoEnlarged.setImageDrawable(d);

              /*      Intent i = new Intent(TaskMissionPhoto.this,TaskMissionPhotoEnlarged.class );
                    ActivityOptionsCompat acc = ActivityOptionsCompat.makeScaleUpAnimation(imgVPicLarge, 100,100,300,300);
                    i.putExtra("DrawableLocation", drawLoc);

                    ActivityCompat.startActivity(TaskMissionPhoto.this, i, acc.toBundle());
                    //     v.animate().scaleX(2.50f);
               //     v.animate().scaleY(2.50f);
               //     v.animate().start();
                    enlarged = true;
                }
                 else if (enlarged){

                    imgVMissionPhotoEnlarged.setVisibility(View.GONE);
                    //v.animate().scaleX(0.4f);
                    //v.animate().scaleY(0.4f);
                    //v.animate().start();
                    enlarged = false;

                }
                return true;
            }
        });
*/

        FabReturn = (FloatingActionButton)findViewById(R.id.fabReturn);

     //   FabReturn.setImageDrawable(getDrawable(android.R.drawable.ic_input_add));
        FabReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        fabPhoto = (FloatingActionButton) findViewById(R.id.fabPhoto);
        // Drawable save = getDrawable(R.drawable.save_black_24dp);
        fabPhoto.setImageDrawable(getDrawable(R.drawable.ic_photo_camera2));
        fabPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Shukdash TaskMissionPhoto", "onclicklistener clicked");
                //    magicalCamera.takePhoto();
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                    Log.i("Shukdash TaskMissionPhoto", "if sdk is below M");
                    Intent in = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    picFileLocation = getOutputMediaFileUri();

                    Log.i("taskmissionphoto", "pic file location "+picFileLocation);
                    in.putExtra(MediaStore.EXTRA_OUTPUT, picFileLocation);

                   startActivityForResult(in, 0);

                }

                 else{
                    Log.i("taskmissionphoto", "check version");
                    //check for permission to save to external stoarge
                    if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                        Log.i("taskmissionphoto", "get ext store permission");
                        if (!shouldShowRequestPermissionRationale(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                            showMessageOKCancel("You need to allow camera usage",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                                    1);
                                        }
                                    });
                        }
                    }

                    //check for permission to use camera
                    if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, 1);
                        Log.i("taskmissionphoto", "get camera permission");
                        if (!shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                            showMessageOKCancel("You need to allow camera usage",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA},
                                                    1);
                                        }
                                    });
                        }
                    }

                    //when all permissions ok take pic
                    else {
                        Log.i("Shukdash TaskMissionPhoto", "check we get here");
                        Intent intentCam = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                   //     if(intentCam.resolveActivity(getPackageManager())!=null) {
/*
                        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                                Locale.getDefault()).format(new Date());
                        File mediaFile;
                        Log.i("Shukdash TaskMissionPhoto", "getOutputMediaFile namefile");
                        // if (type == MEDIA_TYPE_IMAGE) {
                        mediaFile = new File(mediaStorageDir.getPath() + File.separator
                                + "IMG_" + timeStamp + ".jpg");

*/

                        File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                        String name = new SimpleDateFormat("yyyyMMdd_HHmmss",
                                Locale.getDefault()).format(new Date());
                        File file = new File(path, name);
                     //   Toast.makeText(getBaseContext(), file.toString(), Toast.LENGTH_LONG).show();
                        picFileLocation = Uri.fromFile(file);

                        //    picFileLocation = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
                            Log.i("taskmissionphoto", "pic file location "+picFileLocation);
                            intentCam.putExtra(MediaStore.EXTRA_OUTPUT, picFileLocation);
                            startActivityForResult(intentCam, 0);
                            Log.i("taskmissionphoto", "rxcamera before open");
                     //   }


                    }


                }
            }


                //TODO: set up a way to check the string for specific words depending on the task. For this we should probably load a list / array of strings of
                // keywords which would mean the correct answer had been given

                /*
             //   Log.i("TaskMissionText fab onclick", "result: " + result + " hintResult: " + resultHint);
                if (result != "" || resultHint != "Tap Here to enter your answer") {

                   // ShukDashDB db = new ShukDashDB(getApplicationContext());
                  //  boolean answerSaved = db.setAnswerForTask(Integer.valueOf(catNum), Integer.valueOf(taskNum), result, 1);

                    if (answerSaved) {
                        Snackbar.make(view, "Your answer has been saved", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();

                        //TODO: set up a way to check the string for specific words depending on the task. For this we should probably load a list / array of strings of
                        // keywords which would mean the correct answer had been given

                    } else {
                        Snackbar.make(view, "Your answer has NOT been saved, please check and make any changes and then save again", Snackbar.LENGTH_LONG)
                                .setAction("Action", null).show();
                    }
                } else {
                    Snackbar.make(view, "Your answer has NOT been saved, please check and make any changes and then save again", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            }
            */
        });

        fabSave = (FloatingActionButton) findViewById(R.id.fabSave);
      //   Drawable saveIcon = getDrawable(R.drawable.save_black_24dp);
           fabSave.setImageDrawable(getDrawable(R.drawable.save_black_24dp));
        fabSave.setMinimumWidth(48);
        fabSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (picFileLocation!=null){
                    Log.i("taskmissionphoto", " save bitmap location  "+picFileLocation.toString());
                    int taskNm = Integer.valueOf(taskNum);

                    boolean isSet = db.setAnswerForTask(catN, taskNm, picFileLocation.toString(), 1);
                    Log.i("taskmissionphoto", " save bitmap location succesful "+isSet);

                    Log.i("taskmissionphoto", "rxcamera snackbar");
                    Snackbar.make(v, "Your answer has been saved", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                    Log.i("taskmissionphoto", "rxcamera snackbar completed");

                //    Handler h = new Handler(" having a wonderful times!!!!");
                    Thread ret = new Thread();

                    try{  ret.sleep(3000);
                    finish();}
                    catch (InterruptedException e){}
                }

                else{
                    Log.i("taskmissionphoto", " No bitmap location - nothing saved");
                }
            }
        });
    }
    private void showMessageOKCancel(String message, DialogInterface.OnClickListener okListener) {
        new AlertDialog.Builder(this)
                .setMessage(message)
                .setPositiveButton("OK", okListener)
                .setNegativeButton("Cancel", null)
                .create()
                .show();
    }

    public void snackBarDisplay(View view){
        Log.i("taskmissionphoto", "rxcamera snackbar");
        Snackbar.make(view, "Your answer has been saved", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 0: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("taskmissionphoto", "read ext store permitted");
               //     Intent in = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
               //     startActivityForResult(in, 1);
                }
            }
            case 1: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Log.i("taskmissionphoto", "camera permitted");
  /*                  Intent in = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(in, 1);

                   Observable<RxCamera> cam =  RxCamera.open(activity, config).flatMap(new Func1<RxCamera, Observable<RxCamera>>() {
                        @Override
                        public Observable<RxCamera> call(RxCamera rxCamera) {
                            Log.i("taskmissionphoto", "rxcamera open");
                            return rxCamera.bindSurface(imgVCamera);
                            //rxCamera.bindTexture(imgVCamera);
                            // or bind a SurfaceView:
                            // rxCamera.bindSurface(SurfaceView)
                        }
                    }).flatMap(new Func1<RxCamera, Observable<RxCamera>>() {
                        @Override
                        public Observable<RxCamera> call(RxCamera rxCamera) {
                            Log.i("taskmissionphoto", "rxcamera flatmap");
                            return rxCamera.startPreview();
                        }
                    });
                    //cam.observeOn()
                    */
                } else {
                    Toast.makeText(this, "You did not allow camera usage :(", Toast.LENGTH_SHORT).show();

                }
                return;
            }
        }
    }
    Bitmap bm;
    Uri pic;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("taskmissionphoto", "onactivity result");
        Log.i("taskmissionphoto", "onactivity result requestCode= "+requestCode+" resultCode= "+resultCode);

       // Log.i("taskmissionphoto", "onactivity result  getdata "+data.getData().getPath().toString());

        //        Log.i("taskmissionphoto", "onactivity result  ,datastring "+data.getDataString());

       //     Log.i("taskmissionphoto", "onactivity result intent data= "+data.getDataString()+" "+data.getData().toString());

        /*
         String dataRes = data.getData().toString();

            Log.i("taskmissionphoto", "onactivity result " + dataRes);
            pic = data.getData();

         */
        if (requestCode == 0) {
    //  Uri uri1=  getIntent().getData();
      //  Log.i("taskmissionphoto", "onactivity result2 " + uri1);

        //String dataRes = data.getDataString();
            //pic=data.getData();
            Log.i("taskmissionphoto", "onactivity result " + picFileLocation);
//            pic = data.getData();
            BitmapFactory.Options options = new BitmapFactory.Options();

            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;

            Bitmap bitmap = BitmapFactory.decodeFile(picFileLocation.getPath(), options);
            imgVCamera1.setImageBitmap(bitmap);
        /*    try {
                Log.i("taskmissionphoto", "onactivity result, try begin");
                bm = MediaStore.Images.Media.getBitmap(activity.getContentResolver(), picFileLocation);
                Log.i("taskmissionphoto", "onactivity result, try 1");
                Matrix matrix = new Matrix();
                Log.i("taskmissionphoto", "onactivity result, try 2");
                matrix.postRotate(90);
              //  bm.createBitmap(bm, 0,0,200,300,matrix, true);
                Log.i("taskmissionphoto", "onactivity result, try 3");
                imgVCamera1.setImageBitmap(bm);
                Log.i("taskmissionphoto", "onactivity result, try 4");
            }
            catch (IOException e){
                Log.i("taskmissionphoto onactivity result IOException", e.toString());
            }
            */
       }
     //   else{
     //       Log.i("taskmissionphoto", "onactivity result Result Code is Not 0 intent data= "+data.getDataString());
     //   }
    }




    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Shukdash TaskMissionPhoto", "onPause started");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Shukdash TaskMissionPhoto", "onStop started");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Shukdash TaskMissionPhoto", "onDestroy started");
    }

    /**
     * ------------ Helper Methods ----------------------
     * */

    /**
     * Creating file uri to store image/video     int type
     */
    public Uri getOutputMediaFileUri() {
        return Uri.fromFile(getOutputMediaFile());
    }

    /**
     * returning image / video
     */
    private static File getOutputMediaFile() {
        Log.i("Shukdash TaskMissionPhoto", "getOutputMediaFile started");
        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }
        Log.i("Shukdash TaskMissionPhoto", "getOutputMediaFile setup timestamp");
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        Log.i("Shukdash TaskMissionPhoto", "getOutputMediaFile namefile");
       // if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
       /* } else if (type == MEDIA_TYPE_VIDEO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "VID_" + timeStamp + ".mp4");
        } else {
            return null;
        }
        */
        Log.i("Shukdash TaskMissionPhoto", "getOutputMediaFile return");
        return mediaFile;
    }
}


/*


if (isFabOpen){
                    Log.i("taskmissionphoto", "fab closing");
                    fabDisplay.startAnimation(rotateBkwd);
                    fabPhoto.startAnimation(fab_close);
                    fabPhoto.setClickable(false);
                    fabSave.startAnimation(fab_close);
                    fabSave.setClickable(false);
                    txtVSaveText.setAnimation(fab_close);
                    txtVPhotoText.setAnimation(fab_close);
                   // txtVSaveText.setVisibility(View.INVISIBLE);
                   // txtVPhotoText.setVisibility(View.INVISIBLE);
                   // frm.setVisibility(View.INVISIBLE);
                    isFabOpen=false;
                }
                else{
                    Log.i("taskmissionphoto", "fab opening");
                    fabDisplay.startAnimation(rotateFrwd);
                    fabPhoto.startAnimation(fab_open);
                    fabPhoto.setClickable(true);
                    fabSave.startAnimation(fab_open);
                    txtVSaveText.startAnimation(fab_open);
                    txtVPhotoText.setAnimation(fab_open);
                   // frm.setVisibility(View.VISIBLE);
                  //  txtVSaveText.setVisibility(View.VISIBLE);
                  //  txtVPhotoText.setVisibility(View.VISIBLE);
                    fabSave.setClickable(true);

                    isFabOpen=true;
                }

*/
    /* Log.i("taskmissionphoto", "rxcamera before open");
                        RxCamera.open(activity.getApplicationContext(), config).flatMap(new Func1<RxCamera, Observable<RxCamera>>() {
                            @Override
                            public Observable<RxCamera> call(RxCamera rxCamera) {
                                Log.i("taskmissionphoto", "rxcamera open");
                                return rxCamera.bindSurface(imgVCamera);
                                        //rxCamera.bindTexture(imgVCamera);
                                // or bind a SurfaceView:
                                // rxCamera.bindSurface(SurfaceView)
                            }
                        }).flatMap(new Func1<RxCamera, Observable<RxCamera>>() {
                            @Override
                            public Observable<RxCamera> call(RxCamera rxCamera) {
                                Log.i("taskmissionphoto", "rxcamera flatmap");
                                return rxCamera.startPreview();
                            }
                        });

                    //RxCamera camera = new RxCamera();
                    //Camera c2 = new Camera();

                    //camera.request().takePictureRequest(false, Func )
                    */
