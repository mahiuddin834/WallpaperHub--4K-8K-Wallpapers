package com.itnation.wallpaper.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.itnation.wallpaper.Model.CategoryModel;
import com.itnation.wallpaper.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {


    ArrayList<CategoryModel> CategoryModelArraylist;
    Context context;

    categoryClickInterface categoryClickInterface;


    public CategoryAdapter(ArrayList<CategoryModel> categoryModelArraylist, Context context, CategoryAdapter.categoryClickInterface categoryClickInterface) {
        CategoryModelArraylist = categoryModelArraylist;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }




    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View categoryView = LayoutInflater.from(context).inflate(R.layout.category_item, parent, false);
        return new CategoryViewHolder(categoryView);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {

        CategoryModel categoryModel = CategoryModelArraylist.get(position);
        holder.category_text.setText(categoryModel.getCategoryName());
        Glide.with(context).load(categoryModel.getCategoryImageUrl()).into(holder.category_image);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                categoryClickInterface.onCategoryClick(position);


            }
        });




    }

    @Override
    public int getItemCount() {
        return CategoryModelArraylist.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        ImageView category_image;
        TextView category_text;



        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            category_image = itemView.findViewById(R.id.image_category);
            category_text= itemView.findViewById(R.id.category_text);



        }
    }



    public interface categoryClickInterface{

        void onCategoryClick(int position);


    }

}

