package vn.mrlongg71.vnfood.src.module.login.model;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.mrlongg71.vnfood.src.model.BaseResponse;
import vn.mrlongg71.vnfood.src.model.ErrorResponse;
import vn.mrlongg71.vnfood.src.model.User;
import vn.mrlongg71.vnfood.src.module.login.presenter.PresenterLogin;
import vn.mrlongg71.vnfood.src.network.APIVnFood;
import vn.mrlongg71.vnfood.src.network.IApiVnFood;
import vn.mrlongg71.vnfood.src.utils.ErrorUtils;

public class ModelLogin {
    public void loginUser(String email, String password, final PresenterLogin presenterLogin) {
        Log.d("LONgKUTE", "loginUser: " + email);
        IApiVnFood apiService = APIVnFood.getAPIVnFood().create(IApiVnFood.class);
        Call<BaseResponse<User>> call = apiService.handlerLogin(email, password);
        call.enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(@NotNull Call<BaseResponse<User>> call, @NotNull Response<BaseResponse<User>> response) {
                //success code >= 200 <= 300
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    presenterLogin.resultLogin(true, response.body().getData().getToken());
                    Log.d("LONgKUTE", "onResponse: " + response.body().getData().getToken());
                } else {
                    ErrorResponse err = ErrorUtils.parseError(response);
                    presenterLogin.resultLogin(false, err.getStatusCode() + " - " + err.getErr());
                }
            }
            @Override
            public void onFailure(@NotNull Call<BaseResponse<User>> call, @NotNull Throwable t) {
                presenterLogin.resultLogin(false,t.getMessage());
            }
        });
    }
}
