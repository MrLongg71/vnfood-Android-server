package vn.mrlongg71.vnfood.src.module.myorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.text.DecimalFormat;
import java.util.List;

import vn.mrlongg71.vnfood.R;
import vn.mrlongg71.vnfood.src.model.OrderProvisional;
import vn.mrlongg71.vnfood.src.model.Product;
import vn.mrlongg71.vnfood.src.network.EndPoint;

public class AdapterCartProvisional extends RecyclerView.Adapter<AdapterCartProvisional.ViewHolderCartProvisional> {
    private Context context;
    private List<OrderProvisional> orderProvisionals;
    private IOnClickCart iOnClickCart;


    public AdapterCartProvisional(Context context, List<OrderProvisional> orderProvisionals,IOnClickCart iOnClickCart) {
        this.context = context;
        this.orderProvisionals = orderProvisionals;
        this.iOnClickCart = iOnClickCart;
    }

    @NonNull
    @Override
    public ViewHolderCartProvisional onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item_order, parent, false);
        return new ViewHolderCartProvisional(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCartProvisional holder, int position) {
        OrderProvisional orderProvisional = orderProvisionals.get(position);
        Glide.with(context).load(EndPoint.BASE_URL_PUBLIC + orderProvisional.getProduct().getImage()).into(holder.imgProductCart);
        holder.txtNameProductCart.setText(orderProvisional.getProduct().getName());

        holder.txtPriceProductCart.setText(orderProvisional.getProduct().getPrice() + "");
        holder.numberCart.setText(orderProvisional.getAmount() + "");
        holder.increase.setOnClickListener(v ->{
            iOnClickCart.increase(orderProvisional,holder.numberCart);
        });
        holder.reduce.setOnClickListener(v ->{
            iOnClickCart.reduce(orderProvisional,holder.numberCart);
        });
    }

    @Override
    public int getItemCount() {
        return orderProvisionals.size();
    }

    public class ViewHolderCartProvisional extends RecyclerView.ViewHolder {
        RoundedImageView imgProductCart;
        TextView txtNameProductCart,txtPriceProductCart,
                increase,numberCart,reduce;
        public ViewHolderCartProvisional(@NonNull View itemView) {
            super(itemView);
            imgProductCart = itemView.findViewById(R.id.imgProductCart);
            txtNameProductCart = itemView.findViewById(R.id.txtNameProductCart);
            txtPriceProductCart = itemView.findViewById(R.id.txtPriceProductCart);
            increase = itemView.findViewById(R.id.increase);
            reduce = itemView.findViewById(R.id.reduce);
            numberCart = itemView.findViewById(R.id.numberCart);
        }
    }
}
