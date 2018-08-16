package com.test.presentation.screeens.list;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.test.presentation.screeens.list.fragment.left.ListLeftFragment;
import com.test.presentation.screeens.list.fragment.right.ListRightFragment;

public class TabsPageFragmentAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    private String tabTitles[] = new String[]{"Your Coin", "All Coin"};
    private Context context;

    public TabsPageFragmentAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ListLeftFragment.newInstance();
            case 1:
                return ListRightFragment.newInstance();
            default:
                break;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}