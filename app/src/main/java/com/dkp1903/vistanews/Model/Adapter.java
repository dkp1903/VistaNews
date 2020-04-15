package com.dkp1903.vistanews.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dkp1903.vistanews.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    Context context;
    List<Articles> articles;

    public Adapter(Context context, List<Articles> articles) {
        this.context = context;
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Articles a = articles.get(position);
        holder.headline.setText(a.getTitle());

        String imageURL = a.getUrlToImage();

        Picasso.with(context).load(imageURL).into(holder.newsImage);

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView headline;
        ImageView newsImage, sentScore, plus;
        CardView cardView;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            headline = itemView.findViewById(R.id.title);
            newsImage = itemView.findViewById(R.id.newsImage);
            sentScore = itemView.findViewById(R.id.sentScore);
            cardView = itemView.findViewById(R.id.cardView);
            plus = itemView.findViewById(R.id.plus);
        }
    }
}
