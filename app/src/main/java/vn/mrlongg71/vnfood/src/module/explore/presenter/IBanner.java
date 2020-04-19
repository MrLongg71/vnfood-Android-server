package vn.mrlongg71.vnfood.src.module.explore.presenter;

public interface IBanner {
    interface IPresenterBanner{
        void getBanner();
        void resultGetBanner(boolean success, String[] listBanner,String msg);
    }
    interface IViewBanner{
        void onSuccessGetBanner(String[] listBanner);
        void onFailGetBanner(String msg);
    }
}
