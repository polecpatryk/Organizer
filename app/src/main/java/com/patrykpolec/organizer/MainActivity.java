package com.patrykpolec.organizer;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private final int MEMORY_ACCESS = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTabLayout = findViewById(R.id.tab_layout);
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab_task));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab_address));
        mTabLayout.addTab(mTabLayout.newTab().setText(R.string.tab_note));
        mTabLayout.addTab(mTabLayout.newTab().setText("Kalendarz"));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.view_pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mTabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setScrollPosition(position, 0, true);
                mTabLayout.setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }
        });

        FloatingActionButton add_object = findViewById(R.id.add_object);
        add_object.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddObject.class);
                startActivity(intent);
            }
        });

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (!checkIfAlreadyHavePermission()) {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, MEMORY_ACCESS);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MEMORY_ACCESS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_LONG).show();
                } else {
                    //Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private boolean checkIfAlreadyHavePermission() {
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission.GET_ACCOUNTS);
        if (result == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    public static class PlaceholderFragment extends Fragment {
        private int fragmentID;

        public PlaceholderFragment() {
        }

        public static PlaceholderFragment newInstance(int fragmentID, int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            fragment.fragmentID = fragmentID;
            Bundle args = new Bundle();
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(fragmentID, container, false);
            return view;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return PlaceholderFragment.newInstance(R.layout.fragment_tab1, position + 1);

                case 1:
                    return PlaceholderFragment.newInstance(R.layout.fragment_tab2, position + 1);

                case 2:
                    return PlaceholderFragment.newInstance(R.layout.fragment_tab3, position + 1);

                case 3:
                    return PlaceholderFragment.newInstance(R.layout.fragment_tab4, position + 1);

                default:
                    return PlaceholderFragment.newInstance(R.layout.fragment_tab1, position + 1);
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}