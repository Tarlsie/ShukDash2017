package com.app.shukdash.Adapters;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.app.shukdash.Views.fragments.CategoryFragment;

/**
 * Created by danielT on 18/07/2016.
 */
public class TabCategoryViewPagerAdapter extends FragmentStatePagerAdapter {
    public TabCategoryViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Intent i = new Intent();

        switch(position){
            case 0:
                i.putExtra("Category", 1);
                break;
            case 1:
                i.putExtra("Category", 2);
                break;
            case 2:
                i.putExtra("Category", 3);
                break;
            case 3:
                i.putExtra("Category", 4);
                break;
            case 4:
                i.putExtra("Category", 5);
                break;
            case 5:
                i.putExtra("Category", 6);
                break;

        }


        return new CategoryFragment();

    }

    @Override
    public int getCount() {
        return 6;
    }
}
