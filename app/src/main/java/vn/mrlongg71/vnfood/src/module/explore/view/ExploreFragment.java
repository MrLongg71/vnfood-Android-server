package vn.mrlongg71.vnfood.src.module.explore.view;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chabbal.slidingdotsplash.SlidingSplashView;
import com.jpardogo.android.googleprogressbar.library.GoogleProgressBar;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.dmoral.toasty.Toasty;
import vn.mrlongg71.vnfood.NavigationActivity;
import vn.mrlongg71.vnfood.R;
import vn.mrlongg71.vnfood.src.model.Images;
import vn.mrlongg71.vnfood.src.model.Product;
import vn.mrlongg71.vnfood.src.module.explore.IOnClickProduct;
import vn.mrlongg71.vnfood.src.module.explore.adapter.NewFoodAdapter;
import vn.mrlongg71.vnfood.src.module.explore.presenter.IProduct;
import vn.mrlongg71.vnfood.src.module.explore.presenter.PresenterProduct;
import vn.mrlongg71.vnfood.src.utils.DialogLoading;
import vn.mrlongg71.vnfood.src.utils.ItemOffsetDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class ExploreFragment extends Fragment implements IProduct.IViewProduct, IOnClickProduct {
    private RecyclerView recyclerNewFood, recyclerAllProduct;
    private SlidingSplashView splashExplore;
    private List<Product> products,productsNew;
    private GoogleProgressBar progressBarExplore;
    private static NotificationBadge badge;
    private NestedScrollView nestedScrollMenu;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_explore, container, false);
        init(view);
        Log.d("LONgKUTE", "onCreateView: ex");
        onScrollListener();
        return view;
    }

    @SuppressLint("WrongConstant")
    private void onScrollListener() {
        nestedScrollMenu.setOnScrollChangeListener((NestedScrollView.OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {

            if (scrollY > oldScrollY) {
                //hide
                NavigationActivity.navigation.setVisibility(View.GONE);

                NavigationActivity.navigation.setVisibility(View.VISIBLE);
                TranslateAnimation animate = new TranslateAnimation(
                        0,                 // fromXDelta
                        0,                 // toXDelta
                        NavigationActivity.navigation.getHeight(),  // fromYDelta
                        0);                // toYDelta
                animate.setDuration(200);
                animate.setFillAfter(true);
                NavigationActivity.navigation.startAnimation(animate);


            } else if (scrollY < oldScrollY) {
                //show
                NavigationActivity.navigation.setVisibility(View.VISIBLE);
                TranslateAnimation animate = new TranslateAnimation(
                        0,                 // fromXDelta
                        0,                 // toXDelta
                        NavigationActivity.navigation.getHeight(),  // fromYDelta
                        0);                // toYDelta
                animate.setDuration(200);
                animate.setFillAfter(true);
                NavigationActivity.navigation.startAnimation(animate);

            }
        });
    }


    private void init(View view) {
        nestedScrollMenu = view.findViewById(R.id.nestedScrollMenu);
        recyclerNewFood = view.findViewById(R.id.recyclerNewFood);
        splashExplore = view.findViewById(R.id.splashExplore);
        progressBarExplore = view.findViewById(R.id.progressExplore);
        recyclerAllProduct = view.findViewById(R.id.recyclerAllProduct);
        FrameLayout framBadge = view.findViewById(R.id.layout_Badge);
        badge = framBadge.findViewById(R.id.badge);
        Button btnViewAllProduct = view.findViewById(R.id.btnViewAllProduct);
        products  = new ArrayList<>();
        productsNew  = new ArrayList<>();


        btnViewAllProduct.setOnClickListener(v -> viewAllProduct());

        if (products != null) {
            products.clear();
            badge.setNumber(NavigationActivity.numberBadge);
        }
        PresenterProduct presenterProduct = new PresenterProduct(this);

        DialogLoading.LoadingGoogle(true, progressBarExplore);

        presenterProduct.getListProduct();
        presenterProduct.getNewListProduct();


    }

    private void viewAllProduct() {
        startActivity(new Intent(getActivity(), AllProductActivity.class));
    }

    private void loadData() {
        ArrayList<Images> images = new ArrayList<>();
        String[] images2 = new String[3];
        images.add(new Images("123", "https://media.cooky.vn/recipe/g6/52287/s320x320/cooky-recipe-cover-r52287.jpg"));
        images.add(new Images("125", "https://media.cooky.vn/recipe/g6/52548/s320x320/cooky-recipe-cover-r52548.jpg"));
        images.add(new Images("126", "https://media.cooky.vn/recipe/g6/52462/s320x320/cooky-recipe-637188772906574414.jpeg"));

        for (int i = 0; i < images.size(); i++) {
            images2[i] = (images.get(i).getImageUrl());
        }

        splashExplore.setImageResources(images2);
        splashExplore.setAutoPage();

        NewFoodAdapter newFoodAdapter = new NewFoodAdapter(getActivity(), R.layout.custom_layout_new_food, productsNew, this, "News");
        NewFoodAdapter allFoodAdapter = new NewFoodAdapter(getActivity(), R.layout.custom_layout_new_food, products, this, "All");

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.margin5);

        recyclerNewFood.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerNewFood.addItemDecoration(new ItemOffsetDecoration(spacingInPixels));
        recyclerNewFood.setNestedScrollingEnabled(false);
        recyclerNewFood.setAdapter(newFoodAdapter);
        newFoodAdapter.notifyDataSetChanged();


        recyclerAllProduct.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerAllProduct.addItemDecoration(new ItemOffsetDecoration(spacingInPixels));
        recyclerAllProduct.setAdapter(allFoodAdapter);
        allFoodAdapter.notifyDataSetChanged();


    }

    @Override
    public void onGetListProductSuccess(List<Product> productList) {
        DialogLoading.LoadingGoogle(false, progressBarExplore);
        products.addAll(productList);
        loadData();

    }

    @Override
    public void onGetListProductFailed(String msg) {
        Toasty.error(Objects.requireNonNull(getActivity()),msg,Toasty.LENGTH_LONG).show();
    }

    @Override
    public void onGetListNewFoodSuccess(List<Product> productList) {
        DialogLoading.LoadingGoogle(false, progressBarExplore);
        productsNew.addAll(productList);
        loadData();
    }

    @Override
    public void onGetListNewFoodFailed(String msg) {
        Toasty.error(Objects.requireNonNull(getActivity()),msg,Toasty.LENGTH_LONG).show();

    }

    @Override
    public void OnClickProductDetails(Product product) {

        Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
        intent.putExtra("product", product);
        startActivity(intent);


    }

    @Override
    public void OnClickBadge() {
        try {
            badge.setNumber(NavigationActivity.numberBadge);

        } catch (Exception e) {
            Log.d("LONgKUTE", "OnClickBadge: " + e.getMessage());
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("LONgKUTE", "onDestroy: explore");
    }


}
