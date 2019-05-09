package com.example.a16213.yuekao.Fragment.fragment1;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class fg4pagerAdapter extends FragmentPagerAdapter{

    List<Fragment> list = new ArrayList <>();
    List<String> strlist = new ArrayList <>();

    public fg4pagerAdapter(FragmentManager fm, List <Fragment> list, List <String> strlist) {
        super(fm);
        this.list = list;
        this.strlist = strlist;
    }

    @Override
    public Fragment getItem(int i) {
        return list.get(i);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strlist.get(position);

    }
}
