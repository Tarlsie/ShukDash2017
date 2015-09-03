package com.app.shukdash.Models;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by danielT on 23/08/2015.
 */
public class GetCatDetails extends Activity {

   Context c;
    public GetCatDetails (Context c){
        this.c = c;
    }


    List<CatDetailsData> categDetails;

    public List<CatDetailsData> getCatDetailsFromParse() {

        categDetails = new ArrayList<CatDetailsData>();

        ParseQuery<CatDetailsData> query = new ParseQuery<CatDetailsData>("CategoryDetails");

        try {
            categDetails = query.find();
            int in = categDetails.size();
            Log.i("Parse catDetails", "size " + String.valueOf(in));

        }
        catch (ParseException e)
        {
            Log.i("Parse Exception ", e.toString());
        }

        return categDetails;

    }

}
/*




 */