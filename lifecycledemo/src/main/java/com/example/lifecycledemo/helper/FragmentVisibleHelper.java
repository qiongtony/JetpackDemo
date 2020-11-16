package com.example.lifecycledemo.helper;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

/**
 * Created by Vance on 2020/6/8 0008.
 */
public class FragmentVisibleHelper {
    
    public static void listenHiddenChange(ViewPager viewPager, final List<Fragment> fragments){
        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
            public int currentPos = -1;
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                if(currentPos == position){
                    return;
                }
                
                Fragment fragment = fragments.get(position);
                FragmentTransaction transaction = fragment.getParentFragmentManager().beginTransaction();
                transaction.setMaxLifecycle(fragment, Lifecycle.State.RESUMED);
                fragment.onHiddenChanged(false);

                if(currentPos >= 0) {
                    Fragment last = fragments.get(currentPos);
                    transaction.setMaxLifecycle(last, Lifecycle.State.STARTED);
                    last.onHiddenChanged(true);
                }
                transaction.commitAllowingStateLoss();
                currentPos = position;
            }
        });
    }
}
