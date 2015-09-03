package com.app.shukdash.Models;

import android.util.Log;

import com.parse.ParseClassName;
import com.parse.ParseObject;

/**
 * Created by danielT on 23/08/2015.
 */


public class CatDetailsData {


    String catName, description, answer;
    int catCode, catLength, points, taskNums, isAnswered, catTotalPoints;

    public CatDetailsData(){

    }

    public CatDetailsData(String catName, int catCode, int catLength, int taskNums, String description, int points ){
        this.catName = catName;
        this.catCode = catCode;
        this.catLength = catLength;
        this.taskNums = taskNums;
        this.description = description;
        this.points = points;
    }

    public CatDetailsData(String answer, int isAnswered){

        this.answer = answer;
        this.isAnswered = isAnswered;
    }

    public CatDetailsData(String answer, int isAnswered, int catTotalPoints){

        this.answer = answer;
        this.isAnswered = isAnswered;
        this.catTotalPoints = catTotalPoints;
    }


    public int getCategoryCode  (){

        return this.catCode;
    }

    public void setCategoryCode(int catCode){
        this.catCode = catCode;
    }

    public int getNumTasks  (){

        return this.taskNums;
    }

    public void setNumTasks(int numOfTasks)
    {
        Log.i("ShukDash", "set numOfTasks" + numOfTasks);
        this.taskNums = numOfTasks;
    }


    public String getCategoryName  (){

       return this.catName;

    }
    public void setCategoryName(String catName){
        this.catName = catName;
    }

    public int getCatLength(){
        return this.catLength;
    }

    public void setCatLength( int catLength){
        this.catLength = catLength;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public int getPoints(){
        return this.points;
    }

    public void setPoints(int points){
        this.points = points;
    }

    public String getAnswer(){
        return this.answer;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

    public int getIsAnswered(){
        return this.isAnswered;
    }

    public void setIsAnswered(int isAnswered){
        this.isAnswered = isAnswered;
    }

    public int getCatTotalPoints(){
        return this.catTotalPoints;
    }

    public void setCatTotalPoints(int catTotalPoints){
        this.catTotalPoints = catTotalPoints;
    }


}
