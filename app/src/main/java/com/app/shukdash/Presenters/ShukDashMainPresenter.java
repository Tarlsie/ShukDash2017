package com.app.shukdash.Presenters;

import android.app.Activity;
import android.database.Cursor;
import android.util.Log;

import com.app.shukdash.Models.CatDetailsData;
import com.app.shukdash.Models.ShukDashDB;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielT on 29/08/2016.
 */
public class ShukDashMainPresenter extends Activity {

   // private List<CatDetailsData> dataFromDB;
   // private Cursor c;
    public ShukDashMainPresenter() {
    }

    public List<CatDetailsData> getMissionTasksList(Cursor c, List<CatDetailsData> dataFromDB){

        while (c.moveToNext()) {
            Log.i("ShukDashMain add DB data to Listarray", "Start and position: "+c.getPosition());
            CatDetailsData data = new CatDetailsData();
            data.setNumTasks(c.getInt(0));
            data.setCategoryName(c.getString(1));
            data.setCatLength(c.getInt(2));
            data.setDescription(c.getString(3));
            data.setPoints(c.getInt(4));
            data.setAnswer(c.getString(5));
            data.setIsAnswered(c.getInt(6));
            data.setIsTextAnswer(c.getInt(7));
            data.setIsPhotoAnswer(c.getInt(8));
            data.setFileLocation(c.getString(9));

            dataFromDB.add(data);
            int n = dataFromDB.size();
            Log.i("ShukDashMain database size", " "+n);
            Log.i("ShukDashMain add DB data to Listarray", data.getCatLength()+" "+ data.getNumTasks()+" "
                    +data.getDescription() +" "+data.getPoints() +" " +data.getIsPhotoAnswer()  +" "+data.getIsTextAnswer() +" "+data.getIsAnswered()+" "+data.getAnswer()+" "+data.getFileLocation());
        }

        return dataFromDB;
    }


    public int[] getTabData(int[] answersByCat, ShukDashDB db){
        int counterCat1 =0, counterCat2=0, counterCat3=0, counterCat4=0, counterCat5=0, counterCat6=0;
        List<Integer> dataAllIsCompleted = new ArrayList<Integer>();
        List<String[]> dataISCompletedByCat = new ArrayList<String[]>();

        dataAllIsCompleted = db.getAllIsCompleted();
        dataISCompletedByCat = db.getIsCompletedOrderedByCat();

        for(int k=0; k<dataAllIsCompleted.size(); k++){
            Log.i("ShukDashmain, onStart", "dataAllIsCompleted "+dataAllIsCompleted.get(k).toString() +" at position "+k);
        }

        for(int l=0; l<dataISCompletedByCat.size(); l++){

            String[] data1= dataISCompletedByCat.get(l);
            int dataInt1 = Integer.valueOf(data1[0]);
            int dataInt2 = Integer.valueOf(data1[1]);

           // Log.i("ShukDashmain, onStart", "dataISCompletedByCat "+data1[0] +" "+ data1[1] +" at position "+l);

            switch(dataInt1){
                case 1:
                //    Log.i("ShukDashmain, onStart", "dataISCompletedByCat dataInt1= "+dataInt1+" dataInt2= "+dataInt2);
                    if(dataInt2==1){
                        counterCat1++;
                    }
                 //   Log.i("ShukDashmain, onStart", "dataISCompletedByCat counterCat1= "+counterCat1);
                    break;
                case 2:
                 //   Log.i("ShukDashmain, onStart", "dataISCompletedByCat dataInt1= "+dataInt1+" dataInt2= "+dataInt2);
                    if(dataInt2==1){
                        counterCat2++;
                    }
                  //  Log.i("ShukDashmain, onStart", "dataISCompletedByCat counterCat2= "+counterCat2);
                    break;
                case 3:
                 //   Log.i("ShukDashmain, onStart", "dataISCompletedByCat dataInt1= "+dataInt1+" dataInt2= "+dataInt2);
                    if(dataInt2==1){
                        counterCat3++;
                    }
                //    Log.i("ShukDashmain, onStart", "dataISCompletedByCat counterCat3= "+counterCat3);
                    break;
                case 4:
                 //   Log.i("ShukDashmain, onStart", "dataISCompletedByCat dataInt1= "+dataInt1+" dataInt2= "+dataInt2);
                    if(dataInt2==1){
                        counterCat4++;
                    }
                 //   Log.i("ShukDashmain, onStart", "dataISCompletedByCat counterCat4= "+counterCat4);
                    break;
                case 5:
                 //   Log.i("ShukDashmain, onStart", "dataISCompletedByCat dataInt1= "+dataInt1+" dataInt2= "+dataInt2);
                    if(dataInt2==1){
                        counterCat5++;
                    }
                  //  Log.i("ShukDashmain, onStart", "dataISCompletedByCat counterCat5= "+counterCat5);
                    break;
                case 6:
               //     Log.i("ShukDashmain, onStart", "dataISCompletedByCat dataInt1= "+dataInt1+" dataInt2= "+dataInt2);
                    if(dataInt2==1){
                        counterCat6++;
                    }
                 //   Log.i("ShukDashmain, onStart", "dataISCompletedByCat counterCat6= "+counterCat6);
                    break;

            }
        }

        answersByCat[0]=counterCat1;
        answersByCat[1]=counterCat2;
        answersByCat[2]=counterCat3;
        answersByCat[3]=counterCat4;
        answersByCat[4]=counterCat5;
        answersByCat[5]=counterCat6;
        for(int m=0; m<answersByCat.length; m++){
            Log.i("ShukDashmain, onStart", "answersByCat "+answersByCat[m] +" at position "+m);
        }

        return answersByCat;
    }

    public List<CatDetailsData> getMissionTasksList1(Cursor c, List<CatDetailsData> dataFromDB){

        while (c.moveToNext()) {
            Log.i("ShukDashMain add DB data to Listarray", "Start and position: "+c.getPosition());
            CatDetailsData data = new CatDetailsData();
            data.setCatLength(c.getInt(2));
            data.setNumTasks(c.getInt(0));
            data.setCategoryName(c.getString(1));
            data.setDescription(c.getString(3));
            data.setPoints(c.getInt(4));
            data.setAnswer(c.getString(5));
            data.setIsAnswered(c.getInt(6));
            data.setIsTextAnswer(c.getInt(7));
            data.setIsPhotoAnswer(c.getInt(8));

            dataFromDB.add(data);
            int n = dataFromDB.size();
            Log.i("ShukDashMain database size", " "+n);
            Log.i("ShukDashMain add DB data to Listarray", data.getCatLength()+" "+ data.getNumTasks()+" "+data.getDescription() +" "+data.getPoints() +" " +data.getIsPhotoAnswer()  +" "+data.getIsTextAnswer() +" "+data.getIsAnswered()+" "+data.getAnswer());
        }

        return dataFromDB;
    }

}
