package vn.mrlongg71.vnfood.src.module.explore.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Random;

import vn.mrlongg71.vnfood.R;
import vn.mrlongg71.vnfood.src.model.Product;

public class NewFoodAdapter extends RecyclerView.Adapter<NewFoodAdapter.ViewHolderNewFood> {
    private Context context;
    private int resource;
    private List<Product> productList;

    public NewFoodAdapter(Context context, int resource, List<Product> productList) {
        this.context = context;
        this.resource = resource;
        this.productList = productList;
    }

    @NonNull
    @Override
    public NewFoodAdapter.ViewHolderNewFood onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        return new ViewHolderNewFood(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewFoodAdapter.ViewHolderNewFood holder, int position) {
        Product product = productList.get(position);
        holder.txtNameFood.setText(product.getName());
        holder.txtRate.setText(String.valueOf(product.getRate()));
        holder.txtComment.setText("10");
        Random random = new Random();
        Glide.with(context).load(product.getImagesProduct().get(random.nextInt(3)).getImageUrl()).into(holder.imgFood);
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolderNewFood extends RecyclerView.ViewHolder {
        TextView txtNameFood,txtRate,txtComment;
        RoundedImageView imgFood;
         ViewHolderNewFood(@NonNull View itemView) {
            super(itemView);
            txtNameFood = itemView.findViewById(R.id.txtNameFoodNew);
            txtRate = itemView.findViewById(R.id.txtRateFoodNew);
            txtComment = itemView.findViewById(R.id.txtCommentFoodNew);
            imgFood = itemView.findViewById(R.id.imgFoodNew);
        }
    }
}
