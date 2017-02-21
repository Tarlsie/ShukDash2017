package com.app.shukdash.Models;

import android.util.Log;

/**
 * Created by danielT on 23/08/2015.
 */


public class CatDetailsData {


    String catName, description, answer, fileLocation;
    int catCode, catLength, points, taskNums, isAnswered, catTotalPoints, isTextAnswer, isPhotoAnswer, isFBAnswer;

    public CatDetailsData(){

    }

    public CatDetailsData(String catName, int catCode, int catLength, int taskNums, String description,
                          int points, int isTextAnswer, int isPhotoAnswer, int isFBAnswer, String fileLocation ){

        this.catName = catName;
        this.catCode = catCode;
        this.catLength = catLength;
        this.taskNums = taskNums;
        this.description = description;
        this.points = points;
        this.isTextAnswer = isTextAnswer;
        this.isPhotoAnswer = isPhotoAnswer;
        this.isFBAnswer = isFBAnswer;
        this.fileLocation = fileLocation;
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

    public int getIsTextAnswer(){
          return this.isTextAnswer;
      }

     public void setIsTextAnswer(int isTextAnswer){
            this.isTextAnswer = isTextAnswer;
        }

    public int getIsPhotoAnswer(){
        return this.isPhotoAnswer;
    }

    public void setIsPhotoAnswer(int isPhotoAnswer){
        this.isPhotoAnswer = isPhotoAnswer;
    }

    public int getIsFBAnswer(){
        return this.isFBAnswer;
    }

    public void setIsFBAnswer(int isFBAnswer){
        this.isFBAnswer = isFBAnswer;
    }

    public String getFileLocation(){
        return this.fileLocation;
    }

    public void setFileLocation(String fileLocation){
        this.fileLocation = fileLocation;
    }


}
