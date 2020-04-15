package vn.mrlongg71.vnfood.src.module.myorder.view;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import vn.mrlongg71.vnfood.NavigationActivity;
import vn.mrlongg71.vnfood.R;
import vn.mrlongg71.vnfood.src.model.Gift;
import vn.mrlongg71.vnfood.src.model.Order;
import vn.mrlongg71.vnfood.src.model.OrderDetails;
import vn.mrlongg71.vnfood.src.model.OrderProvisional;
import vn.mrlongg71.vnfood.src.module.myorder.AdapterCartProvisional;
import vn.mrlongg71.vnfood.src.module.myorder.IOnClickCart;
import vn.mrlongg71.vnfood.src.module.myorder.IOrder;
import vn.mrlongg71.vnfood.src.module.myorder.presenter.PresenterOrder;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyOrderActivity extends AppCompatActivity implements IOnClickCart, IOrder.IViewOrder {

    private RecyclerView recyclerProductCart;
    private TextView txtTotalPrice, txtTotalAmount;
    private LinearLayout layoutCartEmpty, layoutCart;
    private AdapterCartProvisional adapterCartProvisional;
    private Button btnCheckoutCart, btnCheckGift;
    private PresenterOrder presenterOrder;
    private TextView txtResultCheckGift;
    private TextInputEditText edtGift;
    private double totalPrice = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_my_order);
        initView();

    }


    private void initView() {
        recyclerProductCart = findViewById(R.id.recyclerProductCart);
        txtTotalAmount = findViewById(R.id.txtTotalAmountCart);
        txtTotalPrice = findViewById(R.id.txtTotalPriceCart);
        layoutCartEmpty = findViewById(R.id.layoutCartEmpty);
        layoutCart = findViewById(R.id.layoutCart);
        btnCheckoutCart = findViewById(R.id.btnCheckoutCart);
        txtResultCheckGift = findViewById(R.id.txtResultCheckGift);
        btnCheckGift = findViewById(R.id.btnCheckGift);
        edtGift = findViewById(R.id.edtGift);
        presenterOrder = new PresenterOrder(this);

        btnCheckGift.setOnClickListener(v -> getCodeGift());
        Toolbar toolbar_MyOrder = findViewById(R.id.toolbar_MyOrder);
        toolbar_MyOrder.setNavigationOnClickListener(v -> finish());


        checkVisibleCart();
    }

    private void getCodeGift() {
        String codeGift = edtGift.getText().toString();
        if (!codeGift.isEmpty()) {
            presenterOrder.checkGift(codeGift);
        } else {
            Toasty.error(MyOrderActivity.this, "Code Empty", Toasty.LENGTH_LONG).show();
        }
    }

    private void handlerGetDataCart() {
        adapterCartProvisional = new AdapterCartProvisional(MyOrderActivity.this, NavigationActivity.orderDetails, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MyOrderActivity.this, LinearLayoutManager.VERTICAL, false);
        recyclerProductCart.setLayoutManager(layoutManager);
        recyclerProductCart.setAdapter(adapterCartProvisional);
        handlerTotalPrice();
        adapterCartProvisional.notifyDataSetChanged();
        btnCheckoutCart.setOnClickListener(v -> checkOut());

    }

    private void checkOut() {
        Order order = new Order();
        List<OrderDetails> orderDetailsList = new ArrayList<>();
        for (OrderProvisional i : NavigationActivity.orderDetails) {
            OrderDetails orderDetails = new OrderDetails();
            orderDetails.setAmount(i.getAmount());
            orderDetails.setPrice(Integer.parseInt(i.getProduct().getPrice()) * i.getAmount());
            orderDetails.setProductIdl(i.getProduct().getProductId());
            orderDetailsList.add(orderDetails);
        }


        presenterOrder.addCartToServer(order, orderDetailsList);
    }

    @Override
    public void increase(OrderProvisional orderProvisional, TextView numberCart) {
        orderProvisional.setAmount(orderProvisional.getAmount() + 1);
        numberCart.setText(orderProvisional.getAmount() + "");
        handlerTotalPrice();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void reduce(OrderProvisional orderProvisional, TextView numberCart) {

        if (orderProvisional.getAmount() == 1) {
            new AlertDialog.Builder(MyOrderActivity.this)
                    .setTitle("Thông báo")
                    .setMessage("Bạn có muốn xoá sản phẩm " + orderProvisional.getProduct().getName())
                    .setPositiveButton("Có", (dialog, which) -> {
                        NavigationActivity.orderDetails.remove(orderProvisional);
                        NavigationActivity.numberBadge = NavigationActivity.numberBadge - 1;
                        adapterCartProvisional.notifyDataSetChanged();
                        handlerTotalPrice();
                        checkVisibleCart();
                    }).setNegativeButton("Không", (dialog, which) -> dialog.dismiss()).show();
        } else {
            orderProvisional.setAmount(orderProvisional.getAmount() - 1);
            numberCart.setText(orderProvisional.getAmount() + "");
            handlerTotalPrice();

        }


    }

    private void handlerTotalPrice() {
        totalPrice = 0;
        for (OrderProvisional i : NavigationActivity.orderDetails) {
            totalPrice += i.getAmount() * Double.parseDouble(i.getProduct().getPrice());
        }
        txtTotalPrice.setText(totalPrice + " VND");
    }

    private void checkVisibleCart() {
        if (NavigationActivity.orderDetails.size() <= 0) {
            layoutCartEmpty.setVisibility(View.VISIBLE);
            layoutCart.setVisibility(View.GONE);
        } else {
            layoutCart.setVisibility(View.VISIBLE);
            layoutCartEmpty.setVisibility(View.GONE);
            handlerGetDataCart();


        }
    }

    @Override
    public void onSuccess(String msg) {

    }

    @Override
    public void onFailed(String msg) {

    }

    @Override
    public void onCheckGiftSuccess(Gift gift) {
        txtResultCheckGift.setVisibility(View.VISIBLE);
        txtResultCheckGift.setTextColor(Color.BLUE);
        txtResultCheckGift.setText("√ Giảm giá " + gift.getNumber());
        try {
            txtTotalPrice.setText(totalPrice * (1 - (Double.parseDouble(gift.getNumber()) / 100)) + " VND");

        } catch (Exception e) {
            Log.d("LONgKUTE", "onCheckGiftSuccess: Exception " + e.getMessage());
        }

    }

    @Override
    public void onCheckGiftFailed(String msg) {
        txtResultCheckGift.setVisibility(View.VISIBLE);
        txtResultCheckGift.setTextColor(Color.RED);
        txtResultCheckGift.setText(msg);
    }
}
