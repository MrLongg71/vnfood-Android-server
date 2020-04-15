package vn.mrlongg71.vnfood.src.module.explore.presenter;

import java.util.List;

import vn.mrlongg71.vnfood.src.model.Review;
import vn.mrlongg71.vnfood.src.module.explore.model.ModelProduct;

public class PresenterProductDetails  implements IProductDetails.IPresenterProductTDetails{
    private IProductDetails.IViewProductTDetails iViewProductTDetails;
    private ModelProduct modelProduct;

    public PresenterProductDetails(IProductDetails.IViewProductTDetails iViewProductTDetails) {
        this.iViewProductTDetails = iViewProductTDetails;
        this.modelProduct = new ModelProduct();
    }

    @Override
    public void addCommentToServer(String comment, String productId, int rate) {
        modelProduct.addComment(comment, productId, rate,this);
    }

    @Override
    public void resultAddComment(boolean result, String msg) {
        if(result){
            iViewProductTDetails.onSuccessAddComment(msg);
        }else {
            iViewProductTDetails.onFailedAddComment(msg);

        }
    }

    @Override
    public void getComment(String productId) {
        modelProduct.getComment(productId,this);
    }

    @Override
    public void resultGetComment(boolean result, List<Review> reviews) {
        if(result && reviews != null){
            iViewProductTDetails.onSuccessGetComment(reviews);
        }else {
            iViewProductTDetails.onFailedGetComment("");

        }
    }
}
