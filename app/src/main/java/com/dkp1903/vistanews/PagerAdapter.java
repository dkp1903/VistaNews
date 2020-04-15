package com.dkp1903.vistanews;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.dkp1903.vistanews.fragments.feedsenseFragment;
import com.dkp1903.vistanews.fragments.smartStoriesFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    private int tabsNumber;
    public PagerAdapter(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: return new smartStoriesFragment();
            case 1: return new feedsenseFragment();
            default: return null;
        }

    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
