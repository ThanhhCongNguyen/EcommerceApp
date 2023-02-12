package com.example.ecommerceapp.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.ecommerceapp.ui.home_fragment.ArmChairFragment;
import com.example.ecommerceapp.ui.home_fragment.BedFragment;
import com.example.ecommerceapp.ui.home_fragment.ChairFragment;
import com.example.ecommerceapp.ui.home_fragment.TableFragment;

public class ViewPagerAdapter extends FragmentStateAdapter  {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
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
    public int getItemCount() {
        return 4;
    }
}
