package vn.mrlongg71.vnfood.src.module.explore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.makeramen.roundedimageview.RoundedImageView;
import com.mindorks.placeholderview.annotations.infinite.LoadMore;

import java.util.List;
import java.util.Random;

import vn.mrlongg71.vnfood.R;
import vn.mrlongg71.vnfood.src.model.Product;
import vn.mrlongg71.vnfood.src.model.StoreKey;
import vn.mrlongg71.vnfood.src.module.explore.IOnClickProduct;
import vn.mrlongg71.vnfood.src.network.EndPoint;

public class NewFoodAdapter extends RecyclerView.Adapter<NewFoodAdapter.ViewHolderNewFood> {
    private Context context;
    private int resource;
    private List<Product> productList;
    private IOnClickProduct iOnClickProduct;
    private String titleMar;


    public NewFoodAdapter(Context context, int resource, List<Product> productList,IOnClickProduct iOnClickProduct,String titleMar){
        this.context = context;
        this.resource = resource;
        this.productList = productList;
        this.iOnClickProduct = iOnClickProduct;
        this.titleMar = titleMar;

    }

    @NonNull
    @Override
    public NewFoodAdapter.ViewHolderNewFood onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
//        ViewGroup.LayoutParams paramsBody = view.getLayoutParams();
//        paramsBody.width = (int) (StoreKey.getSize().x /1.1);
//        view.setLayoutParams(paramsBody);
        return new ViewHolderNewFood(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewFoodAdapter.ViewHolderNewFood holder, int position) {
        Product product = productList.get(position);
        holder.txtNameFood.setText(product.getName());
//        holder.txtRate.setText(String.valueOf(product.getRate()));
        holder.txtComment.setText("10");
        String[] quickAction = {
                "❤️",
                "😍️",
                "🤩",
                "☺️️",
                "😛",
                "😉",
                "👏",
                "🤘"};
        int randomQuick = new Random().nextInt(7);
        holder.txtTitleMar.setText(titleMar + " " +quickAction[randomQuick]);
        Glide.with(context)
                .setDefaultRequestOptions(new RequestOptions().placeholder(R.drawable.ic_cart_loading))
                .load(EndPoint.BASE_URL_PUBLIC + product.getImage())
                .error(R.drawable.ic_error_outline_white_24dp).into(holder.imgFood);

        holder.layout_item_new_food.setOnClickListener(v -> {
            iOnClickProduct.OnClickProductDetails(product);
        });
        holder.txtRate.setText( "❤️" + " 4");
        holder.txtComment.setText( "💬" + " 4");

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolderNewFood extends RecyclerView.ViewHolder {
        TextView txtNameFood, txtRate, txtComment,txtTitleMar;
        RoundedImageView imgFood;
        CardView layout_item_new_food;

        ViewHolderNewFood(@NonNull View itemView) {
            super(itemView);
            layout_item_new_food = itemView.findViewById(R.id.layout_item_new_food);
            txtNameFood = itemView.findViewById(R.id.txtNameFoodNew);
            txtRate = itemView.findViewById(R.id.txtRateFoodNew);
            txtComment = itemView.findViewById(R.id.txtCommentFoodNew);
            txtTitleMar = itemView.findViewById(R.id.txtTitleMar);
            imgFood = itemView.findViewById(R.id.imgFoodNew);
            txtNameFood.setMaxLines(1);
        }
    }

}
