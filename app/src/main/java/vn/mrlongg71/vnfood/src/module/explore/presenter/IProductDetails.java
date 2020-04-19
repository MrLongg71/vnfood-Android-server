package vn.mrlongg71.vnfood.src.module.explore.presenter;

import java.util.List;

import vn.mrlongg71.vnfood.src.model.Images;
import vn.mrlongg71.vnfood.src.model.Review;

public interface IProductDetails {
    interface IPresenterProductTDetails{
        void addCommentToServer(String comment,String productId,int rate);
        void resultAddComment(boolean result, String msg);

        void getComment(String productId);
        void resultGetComment(boolean result, List<Review> reviews);

        void getImages(String productId);
        void resultGetImages(boolean result, List<Images> imagesList);



    }
    interface IViewProductTDetails{
        void onSuccessAddComment(String msg);
        void onFailedAddComment(String msg);

        void onSuccessGetComment(List<Review> reviews);
        void onFailedGetComment(String msg);

        void onSuccessGetImages(String[] images);
        void onFailGetImages(String msg);
    }
}
