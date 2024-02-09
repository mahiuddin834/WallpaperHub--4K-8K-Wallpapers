package com.itnation.wallpaper;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.MainThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.itnation.wallpaper.Adapter.CategoryAdapter;
import com.itnation.wallpaper.Adapter.WallpaperAdapter;
import com.itnation.wallpaper.Model.CategoryModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.categoryClickInterface {

    LinearLayout search_bar;
    EditText search_EditText;
    ImageView search_icon;
    ProgressBar progressBar;
    RecyclerView RV_Category, RV_Wallpaper;

    ArrayList<String> wallpaperArrayList;

    ArrayList<CategoryModel> categoryModelArrayList;
    CategoryAdapter categoryAdapter;
    WallpaperAdapter wallpaperAdapter;


    //1PQ8rtNVnbpnM2CRvjk8RUF8afM1ZwLQ5AnbF4bddtM3bEOqgywZv1jm

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        search_bar= findViewById(R.id.search_bar);
        search_EditText= findViewById(R.id.search_EditText);
        search_icon= findViewById(R.id.search_icon);
        progressBar= findViewById(R.id.progress_bar);
        RV_Category= findViewById(R.id.RV_Category);
        RV_Wallpaper= findViewById(R.id.RV_Wallpaper);

        wallpaperArrayList= new ArrayList<>();
        categoryModelArrayList=new ArrayList<>();






        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false);
        RV_Category.setLayoutManager(linearLayoutManager);
        categoryAdapter = new CategoryAdapter(categoryModelArrayList, this, this::onCategoryClick);
        RV_Category.setAdapter(categoryAdapter);


        GridLayoutManager gridLayoutManager= new GridLayoutManager(MainActivity.this, 3);
        RV_Wallpaper.setLayoutManager(gridLayoutManager);
        wallpaperAdapter = new WallpaperAdapter(wallpaperArrayList,MainActivity.this);
        RV_Wallpaper.setAdapter(wallpaperAdapter);


        //================================================






        //=========================================



        getCategorys();
        getWallpapers();


        search_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String searchString = search_EditText.getText().toString();

                if (searchString.isEmpty()){
                    Toast.makeText(MainActivity.this, "Please Entre Your Search Query",Toast.LENGTH_SHORT).show();
                }else {

                    getWallpapersByCategory(searchString);

                }


            }
        });


    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>==================================



    private void getWallpapersByCategory(String category){

        wallpaperArrayList.clear();
        progressBar.setVisibility(View.VISIBLE);
        String searchURL = "https://api.pexels.com/v1/search?query="+ category +"&per_page=78&page=1";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, searchURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressBar.setVisibility(View.GONE);
                JSONArray photosArray=null;

                try {

                    photosArray = response.getJSONArray("photos");
                    for (int i=0; i<photosArray.length(); i++){
                        JSONObject photoObject = photosArray.getJSONObject(i);
                        String ImageUrl= photoObject.getJSONObject("src").getString("portrait");
                        wallpaperArrayList.add(ImageUrl);


                    }

                    wallpaperAdapter.notifyDataSetChanged();


                }catch (JSONException e){
                    e.printStackTrace();
                }




            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Failed to load wallpaper", Toast.LENGTH_SHORT).show();


            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "1PQ8rtNVnbpnM2CRvjk8RUF8afM1ZwLQ5AnbF4bddtM3bEOqgywZv1jm");

                return headers;


            }
        };

        requestQueue.add(jsonObjectRequest);



    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>==================================
    private void getWallpapers() {


        wallpaperArrayList.clear();
        progressBar.setVisibility(View.VISIBLE);

        String URL= "https://api.pexels.com/v1/search?query=8kwallpaper&per_page=78&page=1";
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                progressBar.setVisibility(View.GONE);

                try {

                    JSONArray photosArray = response.getJSONArray("photos");
                    for (int i=0; i<photosArray.length(); i++){
                        JSONObject photoObject = photosArray.getJSONObject(i);
                        String ImageUrl= photoObject.getJSONObject("src").getString("portrait");
                        wallpaperArrayList.add(ImageUrl);


                    }

                    wallpaperAdapter.notifyDataSetChanged();


                }catch (JSONException e){
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "Failed to load wallpaper", Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {

                HashMap<String, String> headers = new HashMap<>();
                headers.put("Authorization", "1PQ8rtNVnbpnM2CRvjk8RUF8afM1ZwLQ5AnbF4bddtM3bEOqgywZv1jm");

                return headers;


            }
        };

        requestQueue.add(jsonObjectRequest);



    }



    //>>>>>>>>>>>>>>>>>>>>>>>>>==================================




    private void getCategorys() {

        categoryModelArrayList .add(new CategoryModel("Natural Wallpaper", "https://www.shutterstock.com/image-photo/magic-pink-rhododendron-flowers-on-260nw-97696616.jpg"));
        categoryModelArrayList .add(new CategoryModel("Food Wallpaper", "https://t3.ftcdn.net/jpg/05/89/70/46/360_F_589704609_b84XoVDaeopctL2Iz0lG4IQT86Dzm7xz.jpg"));
        categoryModelArrayList .add(new CategoryModel("Micro Wallpaper", "https://images.ctfassets.net/h6goo9gw1hh6/35Foq0zNN5DGAg5lWqgnik/1de2cad8bb3decbfbe53ad34efb6c96a/macro-micro-difference-macro-ladybug.jpg?w=1200&h=831&fl=progressive&q=70&fm=jpg"));
        categoryModelArrayList .add(new CategoryModel("Neon Wallpaper", "https://images5.alphacoders.com/132/thumb-1920-1329526.png"));
        categoryModelArrayList .add(new CategoryModel("Luxury Wallpaper", "https://cdn.vectorstock.com/i/preview-1x/55/54/luxury-background-gradient-design-vector-47405554.jpg"));
        categoryModelArrayList .add(new CategoryModel("Cyberpunk Wallpaper", "https://static.vecteezy.com/system/resources/previews/026/481/531/non_2x/the-neon-lit-streets-of-a-cyberpunk-anime-night-city-with-this-captivating-4k-wallpaper-generated-ai-free-photo.jpg"));
        categoryModelArrayList .add(new CategoryModel("Bird Wallpaper", "https://img.freepik.com/premium-photo/colorful-bird-sits-branch-with-flowers-background_871881-266.jpg"));
        categoryModelArrayList .add(new CategoryModel("Fish Wallpaper", "https://c4.wallpaperflare.com/wallpaper/729/59/830/bubbles-goldfish-blue-bokeh-sea-fish-fishes-underwater-water-gold-desktop-wallpaper-preview.jpg"));
        categoryModelArrayList .add(new CategoryModel("Sea Wallpaper", "https://i.natgeofe.com/k/cd784533-e5ef-439a-8167-2ba61b0a9a4b/wave_16x9.jpg?w=1200"));
        categoryModelArrayList .add(new CategoryModel("Wildlife Wallpaper", "https://www.webdesignbooth.com/wp-content/swift-ai/images/wp-content/uploads/2023/09/Choosing-the-Right-Wildlife-Wallpaper-961446135-jpg.webp"));
        categoryModelArrayList .add(new CategoryModel("Sunset Wallpaper", "https://images4.alphacoders.com/133/thumb-1920-1332018.png"));

        categoryAdapter.notifyDataSetChanged();

    }




//>>>>>>>>>>>>>>>>>>>>>>>>>==================================

    @Override
    public void onCategoryClick(int position) {

        String category =categoryModelArrayList.get(position).getCategoryName();
        getWallpapersByCategory(category);


    }


/*
    @Override
    public void onBackPressed() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }

 */



}