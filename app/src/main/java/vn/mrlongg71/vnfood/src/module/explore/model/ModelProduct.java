package vn.mrlongg71.vnfood.src.module.explore.model;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.mrlongg71.vnfood.src.model.BaseResponse;
import vn.mrlongg71.vnfood.src.model.ErrorResponse;
import vn.mrlongg71.vnfood.src.model.Images;
import vn.mrlongg71.vnfood.src.model.Product;
import vn.mrlongg71.vnfood.src.model.Review;
import vn.mrlongg71.vnfood.src.module.explore.presenter.PresenterProduct;
import vn.mrlongg71.vnfood.src.module.explore.presenter.PresenterProductDetails;
import vn.mrlongg71.vnfood.src.network.APIVnFood;
import vn.mrlongg71.vnfood.src.network.IApiVnFood;
import vn.mrlongg71.vnfood.src.utils.ErrorUtils;

public class ModelProduct {
    IApiVnFood apiService = APIVnFood.getAPIVnFood().create(IApiVnFood.class);

    public void listProduct(PresenterProduct presenterProduct) {
        Call<BaseResponse<List<Product>>> callProduct = apiService.getListProduct();
        callProduct.enqueue(new Callback<BaseResponse<List<Product>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Product>>> call, Response<BaseResponse<List<Product>>> response) {
                if (response.isSuccessful()) {
                    presenterProduct.onResult(true, response.body().getData(), "");
                } else {
                    ErrorResponse err = ErrorUtils.parseError(response);
                    presenterProduct.onResult(false, null, err.getErr());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Product>>> call, Throwable t) {
                presenterProduct.onResult(false, null, t.getMessage());
            }
        });

    }
    public void listNewProduct(PresenterProduct presenterProduct) {
        Log.d("LONgKUTE", "listProduct: ");
        Call<BaseResponse<List<Product>>> callProduct = apiService.getNewListProduct();
        callProduct.enqueue(new Callback<BaseResponse<List<Product>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Product>>> call, Response<BaseResponse<List<Product>>> response) {
                if (response.isSuccessful()) {
                    presenterProduct.onResultNewFood(true, response.body().getData(), "");
                } else {
                    ErrorResponse err = ErrorUtils.parseError(response);
                    presenterProduct.onResultNewFood(false, null, err.getErr());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Product>>> call, Throwable t) {
                presenterProduct.onResultNewFood(false, null, t.getMessage());
            }
        });

    }
    public void listProductMore(int page,PresenterProduct presenterProduct) {
        Log.d("LONgKUTE", "getListProductMore: " + page);

        Call<BaseResponse<List<Product>>> callProduct = apiService.getListProductPaging(page);
        callProduct.enqueue(new Callback<BaseResponse<List<Product>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Product>>> call, Response<BaseResponse<List<Product>>> response) {
                if (response.isSuccessful()) {
                    presenterProduct.onResult(true, response.body().getData(), "");
                } else {
                    ErrorResponse err = ErrorUtils.parseError(response);
                    presenterProduct.onResult(false, null, err.getErr());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Product>>> call, Throwable t) {
                presenterProduct.onResult(false, null, t.getMessage());
            }
        });

    }

    public void addComment(String comment, String productId, int rate, PresenterProductDetails presenterProductDetails) {
        Call<BaseResponse<Review.ReviewDetails>> callReview = apiService.addComment(comment, rate, productId);
        callReview.enqueue(new Callback<BaseResponse<Review.ReviewDetails>>() {
            @Override
            public void onResponse(Call<BaseResponse<Review.ReviewDetails>> call, Response<BaseResponse<Review.ReviewDetails>> response) {
                if(response.isSuccessful()){
                    presenterProductDetails.resultAddComment(true,"Thành công");
                }else {
                    presenterProductDetails.resultAddComment(false,"failed");
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Review.ReviewDetails>> call, Throwable t) {
                presenterProductDetails.resultAddComment(false,t.getMessage());
            }
        });
    }


    public void getComment(String productId,PresenterProductDetails presenterProductDetails){
        Call<BaseResponse<List<Review>>> callReview = apiService.getListReview(productId);
        callReview.enqueue(new Callback<BaseResponse<List<Review>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Review>>> call, Response<BaseResponse<List<Review>>> response) {
                if(response.isSuccessful()){

                    presenterProductDetails.resultGetComment(true,response.body().getData());
                }else {
                    presenterProductDetails.resultGetComment(false,  null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Review>>> call, Throwable t) {
                presenterProductDetails.resultGetComment(false,  null);

            }
        });
    }


    public void getImages(String productId, PresenterProductDetails presenterProductDetails){
        Call<BaseResponse<List<Images>>> callImageProduct = apiService.getImagesProduct(productId);
        callImageProduct.enqueue(new Callback<BaseResponse<List<Images>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Images>>> call, Response<BaseResponse<List<Images>>> response) {
                if(response.isSuccessful()){
                    presenterProductDetails.resultGetImages(true,response.body().getData());
                }else {
                    presenterProductDetails.resultGetImages(false,null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Images>>> call, Throwable t) {
                presenterProductDetails.resultGetImages(false,null);
            }
        });
    }

    public void handlerSearch(String query,PresenterProduct presenterProduct){
        Call<BaseResponse<List<Product>>> callSearch = apiService.getSearchProduct(query);
        callSearch.enqueue(new Callback<BaseResponse<List<Product>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Product>>> call, Response<BaseResponse<List<Product>>> response) {
                Log.d("LONgKUTE", "onResponse: search" + response.code());
                if(response.isSuccessful()){
                    presenterProduct.resultSearch(true,response.body().getData());
                }else{
                    presenterProduct.resultSearch(false,null);
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Product>>> call, Throwable t) {
                presenterProduct.resultSearch(false,null);
            }
        });
    }
}

