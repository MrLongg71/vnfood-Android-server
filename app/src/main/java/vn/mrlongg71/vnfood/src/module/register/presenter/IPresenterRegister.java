package vn.mrlongg71.vnfood.src.module.register.presenter;


import vn.mrlongg71.vnfood.src.model.User;

public interface IPresenterRegister {
    void handlerRegister(User user);
    void resultRegister(boolean success, String msg);
}
