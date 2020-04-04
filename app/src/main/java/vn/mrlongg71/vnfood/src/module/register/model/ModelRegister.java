package vn.mrlongg71.vnfood.src.module.register.model;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.mrlongg71.vnfood.src.model.ErrorResponse;
import vn.mrlongg71.vnfood.src.model.User;
import vn.mrlongg71.vnfood.src.module.register.presenter.PresenterRegister;
import vn.mrlongg71.vnfood.src.network.APIVnFood;
import vn.mrlongg71.vnfood.src.network.IApiVnFood;
import vn.mrlongg71.vnfood.src.utils.ErrorUtils;

public class ModelRegister {
    public void register(User user, final PresenterRegister presenterRegister) {
        IApiVnFood apiService = APIVnFood.getAPIVnFood().create(IApiVnFood.class);
        Log.d("LONgKUTE", "register: " + user.getEmail());
//        Call<User> call = apiService.handlerRegister(user.getEmail(), user.getPassword(), user.getPhone(), "", user.getUsername());
//        call.enqueue(new Callback<User>() {
//            @Override
//            public void onResponse(@NotNull Call<User> call, @NotNull Response<User> response) {
//                if (response.isSuccessful()) {
//                    presenterRegister.resultRegister(true, "");
//                } else {
//                    ErrorResponse err = ErrorUtils.parseError(response);
//                    presenterRegister.resultRegister(false, err.getStatusCode() + " - " + err.getErr());
//                }
//            }
//
//            @Override
//            public void onFailure(@NotNull Call<User> call, @NotNull Throwable t) {
//                presenterRegister.resultRegister(false, t.getMessage());
//            }
//        });
    }
}
