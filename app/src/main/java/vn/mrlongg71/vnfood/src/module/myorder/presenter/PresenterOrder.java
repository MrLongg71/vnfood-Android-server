package vn.mrlongg71.vnfood.src.module.myorder.presenter;

import java.util.List;

import vn.mrlongg71.vnfood.src.model.Gift;
import vn.mrlongg71.vnfood.src.model.Order;
import vn.mrlongg71.vnfood.src.model.OrderDetails;
import vn.mrlongg71.vnfood.src.module.myorder.IOrder;
import vn.mrlongg71.vnfood.src.module.myorder.model.ModelOrder;

public class PresenterOrder implements IOrder.IPresenterOrder {
    IOrder.IViewOrder iViewOrder;
    ModelOrder modelOrder;

    public PresenterOrder(IOrder.IViewOrder iViewOrder) {
        this.iViewOrder = iViewOrder;
        modelOrder = new ModelOrder();
    }


    @Override
    public void addCartToServer(Order order, List<OrderDetails> orderDetails) {
        modelOrder.addToCart(order,orderDetails);
    }

    @Override
    public void resultAddCart(boolean result, String msg) {
        if(result){
            iViewOrder.onSuccess(msg);
        }else {
            iViewOrder.onFailed(msg);
        }
    }

    @Override
    public void checkGift(String codeGift) {
        modelOrder.checkGift(codeGift,this);
    }

    @Override
    public void resultCheckGift(boolean result, Gift gift, String msg) {
        if(result){
            iViewOrder.onCheckGiftSuccess(gift);
        }else{
            iViewOrder.onCheckGiftFailed(msg);
        }
    }




}
