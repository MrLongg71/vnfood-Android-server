package vn.mrlongg71.vnfood.src.module.cate.model;

import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.mrlongg71.vnfood.src.model.BaseResponse;
import vn.mrlongg71.vnfood.src.model.Cate;
import vn.mrlongg71.vnfood.src.model.ErrorResponse;
import vn.mrlongg71.vnfood.src.model.Product;
import vn.mrlongg71.vnfood.src.module.cate.presenter.PresenterCate;
import vn.mrlongg71.vnfood.src.module.explore.presenter.PresenterProduct;
import vn.mrlongg71.vnfood.src.network.APIVnFood;
import vn.mrlongg71.vnfood.src.network.IApiVnFood;
import vn.mrlongg71.vnfood.src.utils.ErrorUtils;

public class ModelCate {
    IApiVnFood apiService = APIVnFood.getAPIVnFood().create(IApiVnFood.class);

    public void getListCate(PresenterCate presenterCate){

        Log.d("LONgKUTE", "getListCate: ");
        Call<BaseResponse<List<Cate>>> callProduct = apiService.getListCate();
        callProduct.enqueue(new Callback<BaseResponse<List<Cate>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Cate>>> call, Response<BaseResponse<List<Cate>>> response) {
                if(response.isSuccessful()){
                    presenterCate.resultGetListCate(true,response.body().getData(),"");
                }else {
                    ErrorResponse err = ErrorUtils.parseError(response);
                    presenterCate.resultGetListCate(false, null,err.getErr());
                }

            }

            @Override
            public void onFailure(Call<BaseResponse<List<Cate>>> call, Throwable t) {
                presenterCate.resultGetListCate(false, null,t.getMessage());

            }
        });

    }
    public void listProductForCate(String cateId, PresenterCate presenterCate) {
        Log.d("LONgKUTE", "listProductForCate: " + cateId);
        Call<BaseResponse<List<Product>>> callProduct = apiService.getListProductForCate(cateId);
        callProduct.enqueue(new Callback<BaseResponse<List<Product>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Product>>> call, Response<BaseResponse<List<Product>>> response) {
                if (response.isSuccessful()) {
                    Log.d("LONgKUTE", "onResponse: " + response.body().getData().size());
                    presenterCate.resultGetProductForCate(true, response.body().getData(), "");
                } else {
                    ErrorResponse err = ErrorUtils.parseError(response);
                    presenterCate.resultGetProductForCate(false, null, err.getErr());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<List<Product>>> call, Throwable t) {
                presenterCate.resultGetProductForCate(false, null, t.getMessage());
            }
        });

    }

}
