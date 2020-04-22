package vn.mrlongg71.vnfood.src.module.explore.presenter;

import java.util.List;

import vn.mrlongg71.vnfood.src.model.Product;

public interface IProduct {
    interface IPresenterProduct{
        void getListProduct();
        void getNewListProduct();
        void getListProductMore(int page);
        void onResult(boolean success, List<Product> productList,String msg);
        void onResultNewFood(boolean success, List<Product> productList,String msg);

        void handlerSearch(String query);
        void resultSearch(boolean success, List<Product> productList);

    }
    interface IViewProduct{
        void onGetListProductSuccess(List<Product> productList);
        void onGetListProductFailed(String msg);

        void onGetListNewFoodSuccess(List<Product> productList);
        void onGetListNewFoodFailed(String msg);

        void onSearchProductSuccess(List<Product> productList);
        void onSearchProductFailed(String msg);
    }
}
