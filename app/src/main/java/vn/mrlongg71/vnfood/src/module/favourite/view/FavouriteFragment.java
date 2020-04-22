package vn.mrlongg71.vnfood.src.module.favourite.view;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jpardogo.android.googleprogressbar.library.GoogleProgressBar;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import vn.mrlongg71.vnfood.R;
import vn.mrlongg71.vnfood.src.model.Product;
import vn.mrlongg71.vnfood.src.module.explore.IOnClickProduct;
import vn.mrlongg71.vnfood.src.module.explore.adapter.NewFoodAdapter;
import vn.mrlongg71.vnfood.src.module.explore.presenter.IProduct;
import vn.mrlongg71.vnfood.src.module.explore.presenter.PresenterProduct;
import vn.mrlongg71.vnfood.src.module.explore.view.ProductDetailsActivity;
import vn.mrlongg71.vnfood.src.utils.DialogLoading;
import vn.mrlongg71.vnfood.src.utils.ItemOffsetDecoration;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavouriteFragment extends Fragment implements IProduct.IViewProduct, IOnClickProduct {

    private PresenterProduct presenterProduct;
    private RecyclerView recyclerViewFavourite;
    private GoogleProgressBar progress_favourite;
    private LinearLayout layoutFavouriteEmpty, layoutFavourite;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view
                = inflater.inflate(R.layout.fragment_favourite, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        presenterProduct = new PresenterProduct(this);
        recyclerViewFavourite = view.findViewById(R.id.recyclerViewFavourite);
        progress_favourite = view.findViewById(R.id.progress_favourite);
        layoutFavouriteEmpty = view.findViewById(R.id.layoutFavouriteEmpty);
        layoutFavourite = view.findViewById(R.id.layoutFavourite);

        DialogLoading.LoadingGoogle(true, progress_favourite);
        presenterProduct.getListProduct();
    }

    @Override
    public void onGetListProductSuccess(List<Product> productList) {
        DialogLoading.LoadingGoogle(false, progress_favourite);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("favourite", Context.MODE_PRIVATE);
        List<Product> productListFavourite = new ArrayList<>();
        for (Product product : productList) {
            boolean checkedFavourite = sharedPreferences.getBoolean(product.getProductId(), false);
            if (checkedFavourite) {
                productListFavourite.add(product);
            }
        }
        Log.d("LONgKUTE", "onGetListProductSuccess: " + productListFavourite.size());

        if (productListFavourite.size() > 0) {
            layoutFavourite.setVisibility(View.VISIBLE);
            layoutFavouriteEmpty.setVisibility(View.GONE);
            NewFoodAdapter foodAdapter = new NewFoodAdapter(getActivity(), R.layout.custom_layout_new_food, productListFavourite, this, "Favourite");
            int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.margin10);
            recyclerViewFavourite.setLayoutManager(new GridLayoutManager(getActivity(), 2));
            recyclerViewFavourite.addItemDecoration(new ItemOffsetDecoration(spacingInPixels));
            recyclerViewFavourite.setAdapter(foodAdapter);
            foodAdapter.notifyDataSetChanged();
        } else {
            DialogLoading.LoadingGoogle(false, progress_favourite);
            layoutFavourite.setVisibility(View.GONE);
            layoutFavouriteEmpty.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void onGetListProductFailed(String msg) {

        Log.d("LONgKUTE", "onGetListProductFailed: " + msg);
        DialogLoading.LoadingGoogle(false, progress_favourite);
        Toasty.warning(getActivity(), msg, Toasty.LENGTH_LONG).show();
    }

    @Override
    public void onGetListNewFoodSuccess(List<Product> productList) {

    }

    @Override
    public void onGetListNewFoodFailed(String msg) {
        //null
    }

    @Override
    public void onSearchProductSuccess(List<Product> productList) {
        //null
    }

    @Override
    public void onSearchProductFailed(String msg) {

    }

    @Override
    public void OnClickProductDetails(Product product) {

        Intent intent = new Intent(getActivity(), ProductDetailsActivity.class);
        intent.putExtra("product", product);
        startActivity(intent);

    }

    @Override
    public void OnClickBadge() {

    }
}
