package vn.mrlongg71.vnfood.src.module.myorder;

import java.util.List;

import vn.mrlongg71.vnfood.src.model.Gift;
import vn.mrlongg71.vnfood.src.model.Order;
import vn.mrlongg71.vnfood.src.model.OrderDetails;

public interface IOrder {
    interface IPresenterOrder{
        void addCartToServer(Order order,  List<OrderDetails> orderDetails);
        void resultAddCart(boolean result, String msg);


        void checkGift(String codeGift);
        void resultCheckGift(boolean result, Gift gift, String msg);


    }
    interface IViewOrder{
        void onSuccess(String msg);
        void onFailed(String msg);

        void onCheckGiftSuccess(Gift gift);
        void onCheckGiftFailed(String msg);
    }
}
