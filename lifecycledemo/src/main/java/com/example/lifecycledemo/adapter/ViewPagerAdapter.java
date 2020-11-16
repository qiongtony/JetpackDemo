package com.example.lifecycledemo.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by huangmeng1 on 2018/5/7.
 */
@Deprecated
public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<Fragment> mFragmentList;//list保存fragment会内存泄漏
    private String[] mTitleList;
    public ViewPagerAdapter(FragmentManager fm, List<Fragment> mFragmentList, String[] mTitleList) {
        super(fm);
        this.mFragmentList = mFragmentList;
        this.mTitleList = mTitleList;
    }
    public void clear(){
        if (mFragmentList!=null){
            mFragmentList.clear();
        }
        mFragmentList = null;
    }
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList==null?0:mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mTitleList[position];
    }
}
