package vn.mrlongg71.vnfood.src.module.login.presenter;


public interface IPresenterLogin {
    void handlerLogin(String email,String password);
    void resultLogin(boolean success, String msg);
}
