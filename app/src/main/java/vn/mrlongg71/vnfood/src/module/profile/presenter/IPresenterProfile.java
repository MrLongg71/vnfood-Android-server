package vn.mrlongg71.vnfood.src.module.profile.presenter;

import vn.mrlongg71.vnfood.src.model.User;

public interface IPresenterProfile {
    void getProfile();
    void resultGetProfile(boolean success, User user);
}
