package vn.mrlongg71.vnfood.src.module.profile.model;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.mrlongg71.vnfood.src.model.BaseResponse;
import vn.mrlongg71.vnfood.src.model.ErrorResponse;
import vn.mrlongg71.vnfood.src.model.User;
import vn.mrlongg71.vnfood.src.module.profile.presenter.PresenterProfile;
import vn.mrlongg71.vnfood.src.network.APIVnFood;
import vn.mrlongg71.vnfood.src.network.IApiVnFood;
import vn.mrlongg71.vnfood.src.utils.ErrorUtils;

public class ModelProfile {
    private IApiVnFood apiService = APIVnFood.getAPIVnFood().create(IApiVnFood.class);

    public void profile(PresenterProfile presenterProfile) {
        Call<BaseResponse<User>> callProfile = apiService.getProfile();
        callProfile.enqueue(new Callback<BaseResponse<User>>() {
            @Override
            public void onResponse(@NotNull Call<BaseResponse<User>> call, @NotNull Response<BaseResponse<User>> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    presenterProfile.resultGetProfile(true, response.body().getData(), null);
                } else {
                    ErrorResponse err = ErrorUtils.parseError(response);
                    presenterProfile.resultGetProfile(false, null, err.getErr());
                }
            }

            @Override
            public void onFailure(@NotNull Call<BaseResponse<User>> call, @NotNull Throwable t) {
                presenterProfile.resultGetProfile(false, null, t.getMessage());

            }
        });
    }

    public void uploadImageToServer(MultipartBody.Part value) {
        Call<String> callUpload = apiService.uploadImages(value);
        callUpload.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()) {
                    Log.d("LONgKUTE", "onResponse: thanh cio");
                } else {
                    Log.d("LONgKUTE", "onResponse: " + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    public void changePassword(String odlPassword, String newPassword, PresenterProfile presenterProfile) {
        Call<BaseResponse<String>> callChangePass = apiService.handlerChangePassword(odlPassword, newPassword);
        callChangePass.enqueue(new Callback<BaseResponse<String>>() {
            @Override
            public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                Log.d("LONgKUTE", "onResponse: r" +response.code());
                if (response.isSuccessful()) {
                    presenterProfile.resultChangePassword(true, response.body().getData());
                } else {
                    ErrorResponse err = ErrorUtils.parseError(response);
                    presenterProfile.resultChangePassword(false, err.getErr());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse<String>> call, Throwable t) {
                presenterProfile.resultChangePassword(false, t.getMessage());

            }
        });
    }
}
