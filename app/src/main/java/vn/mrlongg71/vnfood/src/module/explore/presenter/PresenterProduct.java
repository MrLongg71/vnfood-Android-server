package vn.mrlongg71.vnfood.src.module.explore.presenter;

import android.util.Log;

import java.util.List;

import vn.mrlongg71.vnfood.src.model.Product;
import vn.mrlongg71.vnfood.src.module.explore.model.ModelProduct;

public class PresenterProduct implements IProduct.IPresenterProduct {
    private IProduct.IViewProduct iViewProduct;
    private ModelProduct modelProduct;

    public PresenterProduct(IProduct.IViewProduct iViewProduct) {
        this.iViewProduct = iViewProduct;
        modelProduct = new ModelProduct();

    }

    @Override
    public void getListProduct() {
        modelProduct.listProduct(this);
    }



    @Override
    public void getNewListProduct() {
        modelProduct.listNewProduct(this);
    }

    @Override
    public void getListProductMore(int page) {
        modelProduct.listProductMore(page,this);
    }

    @Override
    public void onResult(boolean success, List<Product> productList, String msg) {

        if (success) {
            iViewProduct.onGetListProductSuccess(productList);
        } else {
            iViewProduct.onGetListProductFailed(msg);
        }
    }

    @Override
    public void onResultNewFood(boolean success, List<Product> productList, String msg) {
        if (success) {
            iViewProduct.onGetListNewFoodSuccess(productList);
        } else {
            iViewProduct.onGetListNewFoodFailed(msg);
        }
    }
}
