package vn.mrlongg71.vnfood.src.module.explore.view;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.StrictMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chabbal.slidingdotsplash.SlidingSplashView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import vn.mrlongg71.vnfood.R;
import vn.mrlongg71.vnfood.src.model.Images;
import vn.mrlongg71.vnfood.src.model.Product;
import vn.mrlongg71.vnfood.src.module.explore.adapter.NewFoodAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreFragment extends Fragment {
    private  RecyclerView recyclerNewFood,recyclerHotFood;
    SlidingSplashView splashExplore;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        init(view);
        loadData();
        return view;
    }

    private void init(View view) {
        recyclerNewFood = view.findViewById(R.id.recyclerNewFood);
        recyclerHotFood = view.findViewById(R.id.recyclerHotFood);
        splashExplore = view.findViewById(R.id.splashExplore);


    }
    private void loadData(){
        ArrayList<Images> images = new ArrayList<>();
        String[] images2 = new String[3];
        images.add(new Images("123","https://media.cooky.vn/recipe/g6/52287/s320x320/cooky-recipe-cover-r52287.jpg"));
        images.add(new Images("125","https://media.cooky.vn/recipe/g6/52548/s320x320/cooky-recipe-cover-r52548.jpg"));
        images.add(new Images("126","https://media.cooky.vn/recipe/g6/52462/s320x320/cooky-recipe-637188772906574414.jpeg"));

        for(int i =0; i < images.size(); i++){
            images2[i] =(images.get(i).getImageUrl());
        }

        splashExplore.setImageResources(images2);
        splashExplore.setAutoPage();

        List<Product> products = new ArrayList<>();
        products.add(new Product("123","Gà viên sốt cà chua","","",4.5,images));
        products.add(new Product("123","Gà viên sốt cà chua","","",4.5,images));
        products.add(new Product("123","Gà viên sốt cà chua","","",4.5,images));
        products.add(new Product("123","Gà viên sốt cà chua","","",4.5,images));
        products.add(new Product("123","Gà viên sốt cà chua","","",4.5,images));
        NewFoodAdapter newFoodAdapter = new NewFoodAdapter(getActivity(),R.layout.custom_layout_new_food,products);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);
        RecyclerView.LayoutManager layoutManager1 = new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false);

        recyclerNewFood.setLayoutManager(layoutManager);
        recyclerNewFood.setAdapter(newFoodAdapter);
        recyclerHotFood.setLayoutManager(layoutManager1);
        recyclerHotFood.setAdapter(newFoodAdapter);
        newFoodAdapter.notifyDataSetChanged();
    }

}
