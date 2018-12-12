package com.patrykpolec.organizer.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.patrykpolec.organizer.R;
import com.patrykpolec.organizer.Task;

import java.util.ArrayList;
import java.util.Arrays;

public class Tables {
    private TabLayout tabLayout;
    private Adapter adapter;
    private ViewPager viewPager;

    static private int count;

    static private ArrayList<ArrayList<Integer>> fragmentTab;
    static private ArrayList<Task> data;

    static public Recycler recycler;

    public Tables(View layout, @Nullable ArrayList<Task> data) {
        this.tabLayout = (TabLayout) layout;
        this.fragmentTab = new ArrayList();
        this.data = new ArrayList(data);
    }

    public void Add(int name, final int fragment, int type) {
        tabLayout.addTab(tabLayout.newTab().setText(name));
        fragmentTab.add(new ArrayList<>(Arrays.asList(fragment, type)));
        count++;
    }

    public void Create(FragmentManager fm, View pager) {
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        adapter = new Adapter(fm);
        viewPager = (ViewPager) pager;
        viewPager.setAdapter(adapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(viewPager));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setScrollPosition(position, 0, true);
                tabLayout.setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
    }

    public void UpdateRecycler(ArrayList<Task> tasks) {
        recycler.Update(tasks);
    }
    public class Adapter extends FragmentPagerAdapter {

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position > 0 && position < getCount())
                return PlaceholderFragment.newInstance(fragmentTab.get(position).get(0));
            else
                return PlaceholderFragment.newInstance(fragmentTab.get(0).get(0));
        }

        @Override
        public int getCount() {
            return count;
        }

    }

    public static class PlaceholderFragment extends Fragment {
        private int fragmentID;

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int fragmentID) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            fragment.fragmentID = fragmentID;
            Bundle args = new Bundle();
            fragment.setArguments(args);

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            return inflater.inflate(fragmentID, container, false);
        }

        @Override
        public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            for (int i = 0; i < fragmentTab.size(); ++i) {
                if (fragmentTab.get(i).get(0) == fragmentID && fragmentTab.get(i).get(1) == 1) {
                    recycler = new Recycler(view.findViewById(R.id.view_recycler), getContext(), data);
                }
            }
        }
    }
}