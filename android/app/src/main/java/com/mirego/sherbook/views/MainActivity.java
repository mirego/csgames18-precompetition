package com.mirego.sherbook.views;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.mirego.sherbook.R;
import com.mirego.sherbook.SherbookApplication;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.main_root)
    ViewGroup root;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.tabs)
    TabLayout tabs;

    @BindView(R.id.container)
    ViewPager viewPager;

    public class TabDescriptor {
        public Class fragmentClass;
        public Integer titleRes;
        public Integer iconRes;

        public TabDescriptor(Class fragmentClass, Integer titleRes, Integer iconRes) {
            this.fragmentClass = fragmentClass;
            this.titleRes = titleRes;
            this.iconRes = iconRes;
        }
    }

    private List<TabDescriptor> tabData;
    private SectionsPagerAdapter sectionsPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((SherbookApplication) getApplication()).component().inject(this);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        configureTabLayout();
    }

    private void configureTabLayout() {
        tabData = Arrays.asList(
                new TabDescriptor(HomeFragment.class, R.string.section_home, R.drawable.icn_home),
                new TabDescriptor(EmptyFragment.class, R.string.section_friends, R.drawable.icn_friends),
                new TabDescriptor(CompetitionFragment.class, R.string.section_messages, R.drawable.icn_messages),
                new TabDescriptor(EmptyFragment.class, R.string.section_settings, R.drawable.icn_settings)
        );

        sectionsPagerAdapter = new SectionsPagerAdapter(tabData, getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);

        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                refreshTabDisplay(tab, true);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                refreshTabDisplay(tab, false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                refreshTabDisplay(tab, true);
            }
        });

        for (int index = 0; index < tabData.size(); index++) {
            TabLayout.Tab tab = tabs.getTabAt(index);
            tab.setIcon(tabData.get(index).iconRes);
        }

        tabs.getTabAt(0).select();
    }

    private void refreshTabDisplay(TabLayout.Tab tab, boolean selected) {
        int color = selected ? R.color.colorPrimary : R.color.warm_grey;
        tab.getIcon().setColorFilter(getResources().getColor(color), PorterDuff.Mode.SRC_IN);
        if (selected) {
            setTitle(tabData.get(tab.getPosition()).titleRes);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_logout) {
            Snackbar.make(root, R.string.not_implemented_yet, Snackbar.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private List<TabDescriptor> tabs;

        public SectionsPagerAdapter(List<TabDescriptor> tabs, FragmentManager fm) {
            super(fm);
            this.tabs = tabs;
        }

        @Override
        public Fragment getItem(int position) {
            TabDescriptor tab = tabs.get(position);
            Class fragmentClass = tab.fragmentClass;
            try {
                return (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                return null;
            }
        }

        @Override
        public int getCount() {
            return tabs.size();
        }
    }

}
