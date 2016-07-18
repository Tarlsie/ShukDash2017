package com.app.shukdash.Models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielT on 01/09/2015.
 */
public class ShukDashDB extends SQLiteOpenHelper {

    Context contextDB;
    private static final int DATABASE_VERSION = 1;
    private static final String DB_NAME="shukDash_MachaneYehuda";
    private static final String DB_TABLE = "missions";

    private static final String ID = "id";
    private static final String CATCODE = "cat_code";
    private static final String CATNAME = "cat_name";
    private static final String CATLENGTH ="cat_length";
    private static final String TASKNUM = "task_num";
    private static final String DESCRIPTION = "description";
    private static final String POINTS = "points";
    private static final String ISANSWERED ="is_answered";
    private static final String ANSWER = "answer";
    private static final String ISTEXTINPUTANSWER = "is_text_answer";
    private static final String ISPHOTOANSWER = "is_photo_answer";
    private static final String ISFBANSWER = "is_fb_answer";

    private static final String CREATE_SHUKDASH_TABLE = "CREATE TABLE " + DB_TABLE + "("
            + ID + " INTEGER PRIMARY KEY, "
            + CATCODE + " INTEGER, "
            + CATNAME + " TEXT, "
            + CATLENGTH + " INTEGER, "
            + TASKNUM + " INTEGER, "
            + DESCRIPTION + " TEXT, "
            + POINTS + " INTEGER, "
            + ISANSWERED + " INTEGER DEFAULT 0, "
            + ANSWER + " TEXT, "
            + ISTEXTINPUTANSWER + " INTEGER DEFAULT 0, "
            + ISPHOTOANSWER + " INTEGER DEFAULT 0, "
            + ISFBANSWER + " INTEGER DEFAULT 0);";

    private static final String DB_ALTER_1 = "ALTER TABLE "+ DB_TABLE + " ADD COLUMN" + ISTEXTINPUTANSWER + " INTEGER DEFAULT 0, "
            + " ADD COLUMN "+ ISPHOTOANSWER + " INTEGER DEFAULT 0, " + " ADD COLUMN "+ ISFBANSWER + " INTEGER DEFAULT 0 ;";

    private static final String DB_ALTER_2 = "ALTER TABLE "+ DB_TABLE + " DROP COLUMN  is_text_input_answer ADD COLUMN "+ ISTEXTINPUTANSWER + " INTEGER DEFAULT 0 ;";

    public ShukDashDB(Context context) {

        super(context, DB_NAME, null, DATABASE_VERSION);
        this.contextDB = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        db.execSQL(CREATE_SHUKDASH_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        /*
        if(oldVersion<2){
            db.execSQL(DB_ALTER_1);
            try {
                GetParseData newDBData = new GetParseData(contextDB);
                boolean isUpGradeSuccess = newDBData.parseQuery();
               Log.i("ShukDash", "OnUpGrade DB is Success " + isUpGradeSuccess);
            }
            catch (ParseException e){
                Log.i("ShukDash", "OnUpGrade Parse Exception "+ e.toString());
            }
        }

        if(oldVersion<3){
            db.execSQL(DB_ALTER_2);
            try {
                GetParseData newDBData = new GetParseData(contextDB);
                boolean isUpGradeSuccess = newDBData.parseQuery();
                Log.i("ShukDash", "OnUpGrade DB is Success " + isUpGradeSuccess);
            }
            catch (ParseException e){
                Log.i("ShukDash", "OnUpGrade Parse Exception "+ e.toString());
            }
        }
        */
        db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);
        this.onCreate(db);

    }

    public void dropDB(){
        SQLiteDatabase db = this.getWritableDatabase();
         db.execSQL("DROP TABLE IF EXISTS " + DB_TABLE);

    }

    public boolean inputInitialMissions(List<CatDetailsData> data){

        long result=-1;
        boolean isSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
       for(int i =0; i<data.size();i++) {

           cv.put(CATCODE, data.get(i).getCategoryCode());
           cv.put(CATNAME, data.get(i).getCategoryName());
           cv.put(CATLENGTH, data.get(i).getCatLength());
           cv.put(TASKNUM, data.get(i).getNumTasks());
           cv.put(DESCRIPTION, data.get(i).getDescription());
           cv.put(POINTS, data.get(i).getPoints());
           cv.put(ISTEXTINPUTANSWER, data.get(i).getIsTextAnswer());
           cv.put(ISPHOTOANSWER, data.get(i).getIsPhotoAnswer());
           cv.put(ISFBANSWER, data.get(i).getIsFBAnswer());


           int catCode = data.get(i).getCategoryCode();
           String catName = data.get(i).getCategoryName();
           int catLength= data.get(i).getCatLength();
           int taskNum= data.get(i).getNumTasks();
           String description=data.get(i).getDescription() ;
           int points =data.get(i).getPoints() ;

           result = db.insert(DB_TABLE, null, cv);

           Log.i("ShukDash", "initialMission Data : insert number "+i+" : " + catCode + " " + catName + " " + catLength+" "+taskNum+" "+description+" "+points);
       }

        db.close();

        Log.i("ShukDash", "DB initial mission data finished");

        if(result==-1){
            Log.i("ShukDash" , "DB initial mission data unsuccessful");
            return isSuccessful;
        }
        else {
            isSuccessful=true;
            Log.i("ShukDash" , "DB initial mission data successful");
            return isSuccessful;
        }
    }

    public boolean isDatabaseExist(){

        SQLiteDatabase checkDB = null;
        //checkDB = SQLiteDatabase.openDatabase("/data/data/com.example.shukdash/databases/shukDash_MachaneYehuda", null, SQLiteDatabase.OPEN_READONLY);

            checkDB = SQLiteDatabase.openDatabase("/storage/extSdCsrd/shukDash_MachaneYehuda", null, SQLiteDatabase.OPEN_READONLY);
            checkDB.close();

        if(checkDB==null)
        {Log.i("ShukDash", "Check DB is false");
            return false;}
        else {
            Log.i("ShukDash", "Check DB is true");
            return true;
        }
    }


    public List<CatDetailsData> getAllMissionsDetails(){

        List<CatDetailsData> data = new ArrayList<CatDetailsData>();
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM "+DB_TABLE;

        Cursor cursor =db.rawQuery(query, null);

        while(cursor.moveToNext()){
            CatDetailsData catData = new CatDetailsData();
            catData.setCategoryCode(cursor.getInt(1));
            catData.setCategoryName(cursor.getString(2));
            catData.setCatLength(cursor.getInt(3));
            catData.setNumTasks(cursor.getInt(4));
            catData.setDescription(cursor.getString(5));
            catData.setPoints(cursor.getInt(6));

            data.add(catData);

        }

        return data;
    }

    public List<CatDetailsData> getAllTasksInCategory(int catCode){

        List<CatDetailsData> data = new ArrayList<CatDetailsData>();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT * FROM "+DB_TABLE +" WHERE " +CATCODE +" = " + catCode;

        Cursor cursor =db.rawQuery(query, null);

        while(cursor.moveToNext()){
            CatDetailsData catData = new CatDetailsData();
            catData.setCategoryCode(cursor.getInt(1));
            catData.setCategoryName(cursor.getString(2));
            catData.setCatLength(cursor.getInt(3));
            catData.setNumTasks(cursor.getInt(4));
            catData.setDescription(cursor.getString(5));
            catData.setPoints(cursor.getInt(6));

            data.add(catData);

        }
        return data;
    }

    public boolean setAnswerForTask(int catCode, int taskNum, String answer, int isAnswered){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(ANSWER, answer);
        cv.put(ISANSWERED, isAnswered);

        long result = db.update(DB_TABLE, cv, CATCODE + "= ?" + " and " + TASKNUM + "= ?", new String[]{String.valueOf(catCode), String.valueOf(taskNum)});

        if(result==-1){
            return  false;
        }
        else {
            return true;

        }


    }

    public List<String> getCatNames(){

        SQLiteDatabase db = this.getReadableDatabase();

        List<String> data = new ArrayList<String>();

        String query = "SELECT DISTINCT "+ CATNAME +" FROM "+ DB_TABLE;

        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()){
            String d = cursor.getString(0);
            Log.i("ShukDash", "getCatName function - string: "+d);
            data.add(d);

        }

        return data;
    }

    public List<String> getNumOfTasks(){

        List<String> data = new ArrayList<String>();

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT "+CATLENGTH +" FROM "+DB_TABLE +" GROUP BY "+ CATCODE;
        Cursor cursor = db.rawQuery(query, null);

        while (cursor.moveToNext()){
            String d = cursor.getString(0);
            Log.i("ShukDash", "getNumOfTasks function - string: "+d);
            data.add(d);

        }

        return data;
    }

    public List<String[]> getIsCompletedOrderedByCat(){

        List<String[]> data = new ArrayList<String[]>();

        SQLiteDatabase db =this.getWritableDatabase();

        String query = "SELECT "+CATCODE+" , "+ISANSWERED +" FROM "+ DB_TABLE +" ORDER BY "+ CATCODE+" ASC";


        Cursor cursor = db.rawQuery(query, null);
//        Log.i("ShukDash", "SQL Function getIsAnswered "+cursor.


        String dataDump = DatabaseUtils.dumpCursorToString(cursor);

        Log.i("ShukDash", "GetIsCompleted Cursor Dump to string "+dataDump);

        while (cursor.moveToNext()){

            String[] dataArray = new String[2];

            String d1 = cursor.getString(0);
            String d2 = cursor.getString(1);

             //   Log.i("ShukDash", "SQL Function getIsAnswered "+ d1 +" "+d2);
                dataArray[0] =d1;
                dataArray[1]=d2;
            data.add(dataArray);
        }

        return data;
    }


    public List<String[]> getIsCompletedByCatCode(int catCode){

        List<String[]> data = new ArrayList<String[]>();

        SQLiteDatabase db =this.getWritableDatabase();

        String query = "SELECT "+ANSWER+" , "+ISANSWERED +" FROM "+ DB_TABLE +" WHERE "+ CATCODE+" = "+catCode+" ORDER BY "+ TASKNUM+" ASC" ;

        Cursor cursor = db.rawQuery(query, null);
//        Log.i("ShukDash", "SQL Function getIsAnswered "+cursor.


        String dataDump = DatabaseUtils.dumpCursorToString(cursor);

        Log.i("ShukDash", "GetIsCompletedByCatCode for category: " +catCode+ " Cursor Dump to string "+dataDump);

        while (cursor.moveToNext()){

            String[] dataArray = new String[2];

            String d1 = cursor.getString(0);
            String d2 = String.valueOf(cursor.getInt(1));

            //   Log.i("ShukDash", "SQL Function getIsAnswered "+ d1 +" "+d2);
            dataArray[0] =d1;
            dataArray[1]=d2;
            data.add(dataArray);
        }

        return data;
    }

    public List<Integer> getAllIsCompleted(){

        List<Integer> data = new ArrayList<>();

        SQLiteDatabase db =this.getWritableDatabase();

        String query = "SELECT "+ISANSWERED +" FROM "+ DB_TABLE ;

        Cursor cursor = db.rawQuery(query, null);

        String dataDump = DatabaseUtils.dumpCursorToString(cursor);

        Log.i("ShukDash", "GetIsCompleted Cursor Dump to string "+dataDump);

        while (cursor.moveToNext()){

            int d1 = cursor.getInt(0);

               Log.i("ShukDash", "SQL Function getIsAnswered "+ d1 );

            data.add(d1);
        }

        return data;
    }
    public Cursor getTaskListDetails(int categoryNum){

        Cursor cursor = null;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT "+ TASKNUM +" , "+ CATNAME +" , "+CATLENGTH   +" , "+ DESCRIPTION  +" , "+ POINTS +" , "+
                ANSWER +" , "+ ISANSWERED   + " , "+ ISTEXTINPUTANSWER  +" , "+ ISPHOTOANSWER +" FROM "+DB_TABLE +" WHERE "+CATCODE +" = "+categoryNum;

        cursor = db.rawQuery(query,null);

        String dumpCursor = DatabaseUtils.dumpCursorToString(cursor);
        Log.i("ShukDash", " ShukDashDB : Cursor Dump to string "+dumpCursor);

        return cursor;
    }

    public int getAllPointsTotal(){

        int points = 0;
        SQLiteDatabase db = this.getWritableDatabase();

        String query = "SELECT "+POINTS+" FROM "+DB_TABLE+" WHERE "+ ISANSWERED +" = 1";
        Cursor c = db.rawQuery(query,null);

        String dataDump = DatabaseUtils.dumpCursorToString(c);

        Log.i("ShukDash", "getAllPointsTotal Cursor Dump to string "+dataDump);


        while(c.moveToNext()){

            int a = c.getInt(0);

            points = points+a;

            Log.i("ShukDash", "DB getAllPointsTotal point "+a+ " new points total : "+points);
        }

        return points;
    }

    public int getPointsByCatTotal(int catNum){
        int points = 0;

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+POINTS+" FROM "+DB_TABLE+" WHERE "+ CATCODE +" = "+ catNum  +" , "+ISANSWERED +" = 1";
        Cursor c = db.rawQuery(query, null);

        String dataDump = DatabaseUtils.dumpCursorToString(c);

        Log.i("ShukDash", "getPointsByCatTotal for category: " +catNum+ " Cursor Dump to string "+dataDump);


        while(c.moveToNext()){

            int a = c.getInt(0);

            points = points+a;

            Log.i("ShukDas", "DB getAllPointsTotal point "+a+ " new points total : "+points);
        }

        return points;
    }

    public boolean deleteTable(){

        SQLiteDatabase db = this.getWritableDatabase();
        String query = " delete from "+DB_TABLE;
        Cursor c = db.rawQuery(query, null);
        String dataDump = DatabaseUtils.dumpCursorToString(c);

        Log.i("ShukDash ", "DB drop all data - keep table");


        return true;
    }

    public boolean resetAnswersSettings(){

        SQLiteDatabase db = this.getWritableDatabase();

        String query = "update "+DB_TABLE +" SET " +ANSWER +" = null , " +ISANSWERED + " = 0 ";

        Cursor c = db.rawQuery(query, null);
        String dataDump = DatabaseUtils.dumpCursorToString(c);

        Log.i("ShukDash ", "DB update data - keep table");
        return true;
    }
}
