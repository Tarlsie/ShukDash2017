package com.app.shukdash.Presenters;

import android.util.Log;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by danielT on 11/09/2015.
 */
public class OnSaveGameUpdateData extends Observable {

    private ArrayList<Observer> observers;
    private int points;
    private int task;
   // Observable observable;

    public OnSaveGameUpdateData(){
        observers = new ArrayList<Observer>();
    }

    public void setPoints(int points){
        this.points = points;
        setChanged();
        Log.i("ShukDash Observable", " set points " +points);
        notifyObservers(points);
    }

    public int getPoints(){
        return points;
    }

    public void setTask(int tasks){
        this.task = tasks;
        setChanged();
        Log.i("ShukDash Observable", " set tasks " + task);
        notifyObservers(task);
    }

    public int getTask(){
        return task;
    }





}
