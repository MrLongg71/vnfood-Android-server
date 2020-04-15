package vn.mrlongg71.vnfood.src.module.cate.presenter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

import vn.mrlongg71.vnfood.R;
import vn.mrlongg71.vnfood.src.model.Cate;
import vn.mrlongg71.vnfood.src.model.StoreKey;
import vn.mrlongg71.vnfood.src.module.cate.ICate;
import vn.mrlongg71.vnfood.src.network.EndPoint;

public class CateAdapter extends RecyclerView.Adapter<CateAdapter.ViewHolderCate> {
    private Context context;
    private int resource;
    private List<Cate> listCate;
    private ICate.IOnClick iOnClick;

    public CateAdapter(Context context, int resource, List<Cate> listCate,ICate.IOnClick iOnClick) {
        this.context = context;
        this.resource = resource;
        this.listCate = listCate;
        this.iOnClick = iOnClick;
    }

    @NonNull
    @Override
    public ViewHolderCate onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource, parent, false);
        try {
            ViewGroup.LayoutParams paramsBody = view.getLayoutParams();
            paramsBody.width = (int) (StoreKey.getSize().x /2);
            view.setLayoutParams(paramsBody);
        }catch (Exception ignored){
            Log.d("LONgKUTE", "onCreateViewHolder: " + ignored.getMessage());
        }

        return new ViewHolderCate(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderCate holder, int position) {
        Cate cate = listCate.get(position);
        Glide.with(context).load(EndPoint.BASE_URL_PUBLIC +cate.getImages()).into(holder.imgCate);
        holder.txtCate.setText(cate.getName());
        holder.layoutCateItem.setOnClickListener(v -> iOnClick.onClickGetProductForCate(cate.getCateId(),cate.getName()));
    }

    @Override
    public int getItemCount() {
        return listCate.size();
    }

    public class ViewHolderCate extends RecyclerView.ViewHolder {
        RoundedImageView imgCate;
        TextView txtCate;
        CardView layoutCateItem;
        public ViewHolderCate(@NonNull View itemView) {
            super(itemView);
            layoutCateItem = itemView.findViewById(R.id.layoutCateItem);
            imgCate = itemView.findViewById(R.id.imgCate);
            txtCate = itemView.findViewById(R.id.txtCate);

        }
    }
}
