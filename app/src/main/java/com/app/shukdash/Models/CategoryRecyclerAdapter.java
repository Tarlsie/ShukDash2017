package com.app.shukdash.Models;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.shukdash.Views.TasksDisplay;
import com.example.shukdash.R;

import java.util.List;

/**
 * Created by danielT on 30/10/2015.
 */
public class CategoryRecyclerAdapter extends RecyclerView.Adapter<CategoryRecyclerAdapter.CatRecyclerViewHolder> {

    private LayoutInflater inflater;
    //List<CategoryRecyclerViewData> data = Collections.emptyList();
    public List<String> alName;
    public List<String> alTasks;
    List<String[]> isAnsweredData;
    int[] dataReturned;
    Context context;
    CatRecyclerViewHolder holderGeneral;

    public CategoryRecyclerAdapter(Context context, List<String> catName,
                                   List<String> tasks, List<String[]> isAnsweredData,int[] dataReturned ) {
        inflater = LayoutInflater.from(context);
       // this.data = data;
        alName=catName;
        alTasks = tasks;
       // this.inflater = inflater;
       // this.layoutResourceID = layoutResourceID;
        this.isAnsweredData = isAnsweredData;
        this.dataReturned = dataReturned;
        this.context = context;
    }

    @Override
    public CatRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.i("CatRecycler", "viewholder start");
        View view = inflater.inflate(R.layout.shukdashcategorieslistview, parent, false);
        CatRecyclerViewHolder holder = new CatRecyclerViewHolder(view);
        Log.i("CatRecycler", "viewholder return");
        return holder;
    }

    @Override
    public void onBindViewHolder(CatRecyclerViewHolder holder, int position) {
        holderGeneral = holder;
       // CategoryRecyclerViewData importedData = data.get(position);
        Log.i("CatRecycler", "onBindviewholder start");
        holder.catName.setText(alName.get(position));
        holder.numofTasks.setText(alTasks.get(position)+" Tasks");

        int counterToDoT1=0;
        int counterDoneT1=0;
        int counterToDoT2=0;
        int counterDoneT2=0;
        int counterToDoT3=0;
        int counterDoneT3=0;
        int counterToDoT4=0;
        int counterDoneT4=0;
        int counterToDoT5=0;
        int counterDoneT5=0;
        int counterToDoT6=0;
        int counterDoneT6=0;

        for (int i =0; i<isAnsweredData.size();i++){

            //  Log.i("Shukdash categoryfrag", "int i = "+i);
            if (i<3)
            {

                int j = Integer.valueOf(isAnsweredData.get(i)[1]);
                //Log.i("Shukdash categoryfrag", "int i = "+i +" "+" int j = "+j);

                if (j == 0)
                {
                    //Log.i("Shukdash categoryfrag", "if j=0 "+counterToDoT1);
                    counterToDoT1++;
                    //Log.i("Shukdash categoryfrag", "if j=0 "+counterToDoT1);
                }
                counterDoneT1 =3-counterToDoT1;
                //Log.i("Shukdash categoryfrag", "int counterDoneT1 = "+counterDoneT1 +" "+" int counterToDoT1 = "+counterToDoT1);
            }


            else if (i>2 && i<=5)
            {

                int j = Integer.valueOf(isAnsweredData.get(i)[1]);
                //  Log.i("Shukdash categoryfrag", "int i = "+i +" "+" int j = "+j);

                if (j == 0)
                {
                    //   Log.i("Shukdash categoryfrag", "if j=0");
                    counterToDoT2++;
                }
                counterDoneT2 =3-counterToDoT2;
                // Log.i("Shukdash categoryfrag", "int counterDoneT2 = "+counterDoneT2 +" "+" int counterToDoT2 = "+counterToDoT2);
            }

            else if (i>5 && i<=14)
            {

                int j = Integer.valueOf(isAnsweredData.get(i)[1]);
                // Log.i("Shukdash categoryfrag", "int i = "+i +" "+" int j = "+j);

                if (j == 0)
                {
                    // Log.i("Shukdash categoryfrag", "if j=0");
                    counterToDoT3++;
                }
                counterDoneT3 =9-counterToDoT3;
                //  Log.i("Shukdash categoryfrag", "int counterDoneT3 = "+counterDoneT3 +" "+" int counterToDoT3 = "+counterToDoT3);
            }

            else if (i>14 && i<=18)
            {

                int j = Integer.valueOf(isAnsweredData.get(i)[1]);
                //  Log.i("Shukdash categoryfrag", "int i = "+i +" "+" int j = "+j);

                if (j == 0)
                {
                    //    Log.i("Shukdash categoryfrag", "if j=0");
                    counterToDoT4++;
                }
                counterDoneT4 =4-counterToDoT4;
                //  Log.i("Shukdash categoryfrag", "int counterDoneT4 = "+counterDoneT4 +" "+" int counterToDoT4 = "+counterToDoT4);
            }

            else if (i>18 && i<=28)
            {

                int j = Integer.valueOf(isAnsweredData.get(i)[1]);
                //   Log.i("Shukdash categoryfrag", "int i = "+i +" "+" int j = "+j);

                if (j == 0)
                {
                    //     Log.i("Shukdash categoryfrag", "if j=0");
                    counterToDoT5++;
                }
                counterDoneT5 =10-counterToDoT5;
                // Log.i("Shukdash categoryfrag", "int counterDoneT5 = "+counterDoneT5 +" "+" int counterToDoT5 = "+counterToDoT5);
            }

            else if (i>28 && i<=33)
            {

                int j = Integer.valueOf(isAnsweredData.get(i)[1]);
                // Log.i("Shukdash categoryfrag", "int i = "+i +" "+" int j = "+j);

                if (j == 0)
                {
                    //     Log.i("Shukdash categoryfrag", "if j=0");
                    counterToDoT6++;
                }
                counterDoneT6 =5-counterToDoT6;
                //  Log.i("Shukdash categoryfrag", "int counterDoneT6 = "+counterDoneT6 +" "+" int counterToDoT6 = "+counterToDoT6);
            }
            switch (position){
                case 0:
                    // Log.i("Shukdash categoryfrag", "switch counterDoneT1 = "+counterDoneT1 +" "+" int counterToDoT1 = "+counterToDoT1);
                    holder.tasksToDo.setText(String.valueOf(counterToDoT1));
                    holder.tasksDone.setText(String.valueOf(counterDoneT1));
                    break;
                case 1:
                    holder.tasksToDo.setText(String.valueOf(counterToDoT2));
                    holder.tasksDone.setText(String.valueOf(counterDoneT2));
                    break;
                case 2:
                    holder.tasksToDo.setText(String.valueOf(counterToDoT3));
                    holder.tasksDone.setText(String.valueOf(counterDoneT3));
                    break;
                case 3:
                    holder.tasksToDo.setText(String.valueOf(counterToDoT4));
                    holder.tasksDone.setText(String.valueOf(counterDoneT4));
                    break;
                case 4:
                    holder.tasksToDo.setText(String.valueOf(counterToDoT5));
                    holder.tasksDone.setText(String.valueOf(counterDoneT5));
                    break;
                case 5:
                    holder.tasksToDo.setText(String.valueOf(counterToDoT6));
                    holder.tasksDone.setText(String.valueOf(counterDoneT6));
                    break;
            }

        }
        Log.i("CatRecycler", "onBindviewholder end");
     //   holder.tasksToDo.setText(importedData.getTaskToDo());
      //  holder.tasksDone.setText(importedData.getTaskDone());
    }



    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return alTasks.size();
    }

    /*
    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        super.onViewRecycled(holder);
    }
     @Override
      public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, List payloads) {
         super.onBindViewHolder(holder, position, payloads);
     }
    */

    class CatRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView catName, numofTasks, tasksToDo, tasksDone;

        public CatRecyclerViewHolder(View itemView) {
            super(itemView);
            Log.i("CatRecycler", "viewholder Class start");
            catName = (TextView) itemView.findViewById(R.id.txtVShukDashCatListCatName);
            numofTasks = (TextView) itemView.findViewById(R.id.txtVShukDashCatListNumOfTasks);
            tasksToDo = (TextView) itemView.findViewById(R.id.txtVShukDashCatListTasksToDoNum);
            tasksDone = (TextView) itemView.findViewById(R.id.txtVShukDashCatListTasksCompletedNum);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           // RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams)v.getLayoutParams();
            int catNum =getAdapterPosition();
            //int catNum2 =lp.getViewAdapterPosition();
            Log.i("CatRecycler onclick item data", "ItemId "+catNum);
            //Log.i("CatRecycler onclick item data", "Adapter Position "+catNum2);
            Intent i = new Intent(context, TasksDisplay.class);
            catNum++;
            i.putExtra("Category",catNum);
            Log.i("CatRecycler onclick item data", "ItemId "+catNum);
            context.startActivity(i);
        }
    }
}
