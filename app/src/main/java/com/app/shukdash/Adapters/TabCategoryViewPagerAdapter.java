package com.app.shukdash.Adapters;

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
        return new CategoryFragment();

    }

    @Override
    public int getCount() {
        return 6;
    }
}
