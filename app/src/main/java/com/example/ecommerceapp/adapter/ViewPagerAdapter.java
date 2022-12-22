package com.example.ecommerceapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.ecommerceapp.ui.home_fragment.ArmChairFragment;
import com.example.ecommerceapp.ui.home_fragment.BedFragment;
import com.example.ecommerceapp.ui.home_fragment.ChairFragment;
import com.example.ecommerceapp.ui.home_fragment.TableFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new ChairFragment();
        } else if (position == 1) {
            fragment = new TableFragment();
        } else if (position == 2) {
            fragment = new ArmChairFragment();
        } else {
            fragment = new BedFragment();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = null;
        if (position == 0)
            title = "Chair";
        else if (position == 1)
            title = "Table";
        else if (position == 2)
            title = "ArmChair";
        else if (position == 3)
            title = "Bed";
        return title;
    }
}
