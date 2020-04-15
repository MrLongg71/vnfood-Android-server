package vn.mrlongg71.vnfood.src.module.profile.view;

import vn.mrlongg71.vnfood.src.model.User;

public interface IViewProfile {
    void onSuccess(User user);
    void onFailed(String msg);

    void onChangePasswordSuccess(String msg);
    void onChangePasswordFail(String msg);
}
