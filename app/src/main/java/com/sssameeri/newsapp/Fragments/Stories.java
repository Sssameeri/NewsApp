package com.sssameeri.newsapp.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.sssameeri.newsapp.Activities.MainActivity;
import com.sssameeri.newsapp.Adapters.NewsAdapter;
import com.sssameeri.newsapp.Adapters.Slider;
import com.sssameeri.newsapp.Models.News;
import com.sssameeri.newsapp.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class Stories extends Fragment {



    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private ArrayList<News> newsArrayList;
    private RequestQueue requestQueue;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    ArrayList<News> newsList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_stories, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        viewPager = view.findViewById(R.id.slider);
        tabLayout = view.findViewById(R.id.indicator);



        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        newsArrayList = new ArrayList<>();
        newsList = new ArrayList<>();
        requestQueue = Volley.newRequestQueue(getActivity());

        getData();

    }

    public void getData() {
        String url = "http://allcom.lampawork.com/api/v1.0/products/";

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");


                    for(int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        News news1 = new News();
                        News news2 = new News();

                        if(jsonObject.getJSONObject("image").has("url")) {

                            String title = jsonObject.getString("name");
                            String source = jsonObject.getString("id");
                            String date = jsonObject.getString("price");
                            String imageUrl = jsonObject.getJSONObject("image").getString("url");

                            news1.setName(title);
                            news1.setSource(source);
                            news1.setDate(date);
                            news1.setImageUrl(imageUrl);

                            newsList.add(news1);
                            newsArrayList.add(news1);
                        } else {

                            String title = jsonObject.getString("name");
                            String source = jsonObject.getString("id");
                            String date = jsonObject.getString("price");

                            news2.setName(title);
                            news2.setSource(source);
                            news2.setDate(date);

                            newsArrayList.add(news2);
                        }

                    }

                    newsAdapter = new NewsAdapter(getActivity(), newsArrayList);
                    recyclerView.setAdapter(newsAdapter);
                    viewPager.setAdapter(new Slider(getContext(), newsList));
                    tabLayout.setupWithViewPager(viewPager, true);

                    Timer timer = new Timer();
                    timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });


        requestQueue.add(request);

    }


    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
          getActivity().runOnUiThread(new Runnable() {
              @Override
              public void run() {
                  if(viewPager.getCurrentItem() < newsList.size() - 1) {
                      viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                  } else {
                      viewPager.setCurrentItem(0);
                  }
              }
          });
        }
    }

}
