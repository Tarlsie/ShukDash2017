package com.app.shukdash.Models;

import android.util.Log;

import com.parse.ParseClassName;
import com.parse.ParseObject;



/**
 * Created by danielT on 09/08/2015.
 */

@ParseClassName("shukDashJerDetails")
public class InitDashData extends ParseObject{

    int catCode, catLength, pointS, taskNum;
    String catName, desc, fileLoc;


    public int getCategoryCode  (){
      //  n = getNumber("categoryCode");
       // catCode=  n.intValue();
        return getNumber("categoryCode").intValue();
    }

    public void setCategoryCode(Number categoryCode){
              put("categoryCode", categoryCode);
    }

    public int getCategoryLength  (){

        return getNumber("categoryLength").intValue();
    }

    public void setCategoryLength(Number categoryLen){
        put("categoryLength", categoryLen);
    }


    public String getCategoryName  (){

        catName=getString("categoryName");
        return catName;
    }
    public void setCategoryName(String categoryName){
        put("categoryName", categoryName);
    }


    public String getDescription  (){

        desc = getString("description");
        return desc;
    }

    public void setDescription(String descrip){
        put("description", descrip);
    }

    public String getFileLocation  (){
        fileLoc=getString("fileLocation");
        return fileLoc;
    }

    public void setFileLocation(String fileLocat){
        put("fileLocation", fileLocat);
    }
    public int getPoints  (){

        return getNumber("points").intValue();
    }

    public void setPoints(Number point){
        put("points", point);
    }

    public int getTaskNumber  (){

        return getNumber("taskNumber").intValue();
    }

    public void setTaskNumber(Number tasknum){
        put("taskNumber", tasknum);
    }

    public void displayDataOrginal(){
        Log.i("Display Parse init data", getString("categoryName")  +" "+getString("description")
                +" "+getString("fileLocation") );
    }


}
