package com.sssameeri.newsapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.sssameeri.newsapp.Models.News;
import com.sssameeri.newsapp.R;

import org.w3c.dom.Text;

import java.io.PipedInputStream;
import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    News news = new News();



    @Override
    public int getItemViewType(int position) {
        news = newsArrayList.get(position);
        if(news.getImageUrl() != null)
            return 0;
        return 1;
    }

    private ArrayList<News> newsArrayList;

    public NewsAdapter(Context context, ArrayList<News> newsArrayList) {
        this.context = context;
        this.newsArrayList = newsArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType) {
            case 0: return new NewsViewHolderWithImage(LayoutInflater.from(context).inflate(R.layout.news_item, parent, false));
            case 1: return new NewViewHolderWithoutImage(LayoutInflater.from(context).inflate(R.layout.news_item_without_image, parent, false));
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {





        switch (holder.getItemViewType()) {
            case 0:
                NewsViewHolderWithImage newsViewHolderWithImage = (NewsViewHolderWithImage) holder;
                News news = newsArrayList.get(position);

                String title = news.getName();
                String source = news.getSource();
                String date = news.getDate();
                String imageUrl = news.getImageUrl();


                newsViewHolderWithImage.title.setText(title);
                newsViewHolderWithImage.source.setText(source);
                newsViewHolderWithImage.date.setText(date);
                Picasso.get().load(imageUrl).fit().centerCrop().into(((NewsViewHolderWithImage) holder).imageView);

                break;
            case 1:
                NewViewHolderWithoutImage newViewHolderWithoutImage = (NewViewHolderWithoutImage) holder;
                News news2 = newsArrayList.get(position);

                String title2 = news2.getName();
                String source2 = news2.getSource();
                String date2 = news2.getDate();

                newViewHolderWithoutImage.title.setText(title2);
                newViewHolderWithoutImage.source.setText(source2);
                newViewHolderWithoutImage.date.setText(date2);

                break;

        }

    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }


    public class NewViewHolderWithoutImage extends RecyclerView.ViewHolder {

        TextView title, source, date;

        public NewViewHolderWithoutImage(@NonNull View itemView) {
            super(itemView);


            title = itemView.findViewById(R.id.titleTextView2);
            source = itemView.findViewById(R.id.sourceTextView2);
            date = itemView.findViewById(R.id.dateTextView2);

        }
    }

    public class NewsViewHolderWithImage extends RecyclerView.ViewHolder {


        TextView title, source, date;
        ImageView imageView;


        public NewsViewHolderWithImage(@NonNull View itemView) {
            super(itemView);


            imageView = itemView.findViewById(R.id.imageView);
            title = itemView.findViewById(R.id.titleTextView);
            source = itemView.findViewById(R.id.sourceTextView);
            date = itemView.findViewById(R.id.dateTextView);

        }
    }

}
