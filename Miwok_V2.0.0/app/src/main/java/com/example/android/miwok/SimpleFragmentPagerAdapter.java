package com.example.android.miwok;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter{

    public SimpleFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
       switch (position) {
           case 0: return new NumbersFragment();
           case 1: return new FamilyFragment();
           case 2: return new ColorsFragment();
           case 3: return new PhrasesFragment();
           default: return null;
       }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0: return "Numbers";
            case 1: return "Family";
            case 2: return "Colors";
            case 3: return "Phrases";
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }
}
