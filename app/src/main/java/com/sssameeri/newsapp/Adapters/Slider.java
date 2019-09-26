package com.sssameeri.newsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.squareup.picasso.Picasso;
import com.sssameeri.newsapp.Models.News;
import com.sssameeri.newsapp.R;

import java.util.ArrayList;
import java.util.List;

public class Slider extends PagerAdapter {

    private ArrayList<News> arrayList;
    private Context context;

    public Slider(Context context, ArrayList<News> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {


        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.item_slider, null);

        TextView title =    view.findViewById(R.id.sliderTitleTextView);
        TextView source =    view.findViewById(R.id.sliderSourceTextView);
        TextView date =    view.findViewById(R.id.sliderDateTextView);
        ImageView imageView = view.findViewById(R.id.sliderImageVIew);

        News news = arrayList.get(position);



        title.setText(news.getName());
        source.setText(news.getSource());
        date.setText(news.getDate());
        Picasso.get().load(news.getImageUrl()).fit().centerCrop().into(imageView);


        ViewPager viewPager = (ViewPager) container;
        viewPager.addView(view, 0);

        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager = (ViewPager) container;
        View view = (View) object;
        viewPager.removeView(view);
    }
}
