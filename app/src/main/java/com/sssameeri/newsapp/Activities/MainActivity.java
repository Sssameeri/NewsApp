package com.sssameeri.newsapp.Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.sssameeri.newsapp.Adapters.NewsAdapter;
import com.sssameeri.newsapp.Fragments.Favourites;
import com.sssameeri.newsapp.Models.News;
import com.sssameeri.newsapp.R;
import com.sssameeri.newsapp.Fragments.Stories;
import com.sssameeri.newsapp.Fragments.Video;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.baseline_menu_24);

        ViewPager viewPager = findViewById(R.id.view_pager);

        if(viewPager != null) {
            PagerAdapter pagerAdapter = new NewsTabAdapter(getSupportFragmentManager());
            viewPager.setAdapter(pagerAdapter);

            TabLayout tabLayout = findViewById(R.id.tab_layout);
            tabLayout.setupWithViewPager(viewPager);
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    class NewsTabAdapter extends FragmentPagerAdapter {



        public NewsTabAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0 :
                    return new Stories();
                case 1:
                    return new Video();
                case 2:
                    return new Favourites();
                    default:
                        return null;
            }
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch(position) {
                case 0:
                    return getString(R.string.tab_stories);
                case 1:
                    return getString(R.string.tab_video);
                case 2:
                    return getString(R.string.tab_favourites);
                    default:
                        return null;

            }
        }


    }

}
