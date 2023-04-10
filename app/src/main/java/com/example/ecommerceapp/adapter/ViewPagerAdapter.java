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

public class ViewPagerAdapter extends FragmentStateAdapter {
    private ViewPagerAdapterCallback viewPagerAdapterCallback;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    public void setViewPagerAdapterCallback(ViewPagerAdapterCallback viewPagerAdapterCallback) {
        this.viewPagerAdapterCallback = viewPagerAdapterCallback;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Fragment fragment = null;
        if (position == 0) {
            fragment = new ChairFragment();
            if (viewPagerAdapterCallback != null) {
                viewPagerAdapterCallback.getCurrentFragment(fragment);
            }
        } else if (position == 1) {
            fragment = new TableFragment();
            if (viewPagerAdapterCallback != null) {
                viewPagerAdapterCallback.getCurrentFragment(fragment);
            }
        } else if (position == 2) {
            fragment = new ArmChairFragment();
            if (viewPagerAdapterCallback != null) {
                viewPagerAdapterCallback.getCurrentFragment(fragment);
            }
        } else {
            fragment = new BedFragment();
            if (viewPagerAdapterCallback != null) {
                viewPagerAdapterCallback.getCurrentFragment(fragment);
            }
        }
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public interface ViewPagerAdapterCallback {
        void getCurrentFragment(Fragment fragment);
    }
}
