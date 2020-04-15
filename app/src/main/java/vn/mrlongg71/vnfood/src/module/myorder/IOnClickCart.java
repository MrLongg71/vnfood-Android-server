package vn.mrlongg71.vnfood.src.module.myorder;

import android.widget.TextView;

import vn.mrlongg71.vnfood.src.model.OrderProvisional;

public interface IOnClickCart {
    void increase(OrderProvisional orderProvisional, TextView numberCart);
    void reduce(OrderProvisional orderProvisional, TextView numberCart);
}
