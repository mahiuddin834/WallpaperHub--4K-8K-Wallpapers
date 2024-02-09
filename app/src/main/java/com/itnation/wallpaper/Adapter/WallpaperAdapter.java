package com.itnation.wallpaper.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.itnation.wallpaper.R;
import com.itnation.wallpaper.WallpaperActivity;

import java.util.ArrayList;

public class WallpaperAdapter extends RecyclerView.Adapter<WallpaperAdapter.WallpaperViewHolder> {

    ArrayList<String> wallpaperArrayList;
    Context context;

    public WallpaperAdapter(ArrayList<String> wallpaperArrayList, Context context) {
        this.wallpaperArrayList = wallpaperArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public WallpaperAdapter.WallpaperViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View myView = LayoutInflater.from(context).inflate(R.layout.wallpaper_item, parent, false);

        return new WallpaperViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull WallpaperAdapter.WallpaperViewHolder holder, int position) {

        Glide.with(context).load(wallpaperArrayList.get(position)).into(holder.wallpaper_image);



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(context, WallpaperActivity.class);
                i.putExtra("wallpaperURL", wallpaperArrayList.get(position));
                context.startActivity(i);

            }
        });


    }

    @Override
    public int getItemCount() {

        return wallpaperArrayList.size();
    }

    public static class WallpaperViewHolder extends RecyclerView.ViewHolder {

        ImageView wallpaper_image;
        public WallpaperViewHolder(@NonNull View itemView) {
            super(itemView);

            wallpaper_image=itemView.findViewById(R.id.wallpaper_image);


        }
    }
}
