package vn.mrlongg71.vnfood.src.module.myorder.model;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.mrlongg71.vnfood.src.model.BaseResponse;
import vn.mrlongg71.vnfood.src.model.ErrorResponse;
import vn.mrlongg71.vnfood.src.model.Gift;
import vn.mrlongg71.vnfood.src.model.Order;
import vn.mrlongg71.vnfood.src.model.OrderDetails;
import vn.mrlongg71.vnfood.src.module.myorder.presenter.PresenterOrder;
import vn.mrlongg71.vnfood.src.network.APIVnFood;
import vn.mrlongg71.vnfood.src.network.IApiVnFood;
import vn.mrlongg71.vnfood.src.utils.ErrorUtils;

public class ModelOrder {
    IApiVnFood apiService = APIVnFood.getAPIVnFood().create(IApiVnFood.class);

    public void addToCart(Order order,  List<OrderDetails> orderDetails){
        Log.d("LONgKUTE", "addToCart: " + orderDetails.size());
        Gson gson = new GsonBuilder().create();
        Log.d("LONgKUTE", "addToCart: " + gson.toJson(orderDetails));

        Call<ResponseBody> callOrder = apiService.addOrder(gson.toJson(orderDetails));

        callOrder.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
    public void checkGift(String codeGift, PresenterOrder presenterOrder){
        Call<BaseResponse<Gift>> callGift = apiService.checkGift(codeGift);
        callGift.enqueue(new Callback<BaseResponse<Gift>>() {
            @Override
            public void onResponse(Call<BaseResponse<Gift>> call, Response<BaseResponse<Gift>> response) {
                if(response.isSuccessful()){
                    presenterOrder.resultCheckGift(true, response.body().getData(),"");
                }else {
                    ErrorResponse err = ErrorUtils.parseError(response);
                    presenterOrder.resultCheckGift(false, null,err.getErr());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<Gift>> call, Throwable t) {
                presenterOrder.resultCheckGift(false, null,t.getMessage());

            }
        });
    }
}
