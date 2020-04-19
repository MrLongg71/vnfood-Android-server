package vn.mrlongg71.vnfood.src.module.explore.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.mrlongg71.vnfood.R;
import vn.mrlongg71.vnfood.src.model.Review;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolderReview> {
    private Context context;
    private List<Review> reviews;

    public ReviewAdapter(Context context, List<Review> reviews) {
        this.context = context;
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public ViewHolderReview onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_layout_review, parent, false);

        return new ViewHolderReview(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderReview holder, int position) {
        Review.ReviewDetails review =  reviews.get(position).getReviews();
        holder.txtNameUserReview.setText(review.getUserId().get(0).getUsername() + "");
        holder.txtRateReview.setText(review.getRate() + "");
        holder.txtComment.setText(review.getComment());
    }

    @Override
    public int getItemCount() {
        return reviews.size();
    }

    public class ViewHolderReview extends RecyclerView.ViewHolder {
        TextView txtNameUserReview, txtRateReview, txtComment;
        CircleImageView imgAvatarUserReview;

        public ViewHolderReview(@NonNull View itemView) {
            super(itemView);
            txtNameUserReview = itemView.findViewById(R.id.txtNameUserReview);
            txtRateReview = itemView.findViewById(R.id.txtRateReview);
            txtComment = itemView.findViewById(R.id.txtComment);
            imgAvatarUserReview = itemView.findViewById(R.id.imgAvatarReview);
        }
    }
}
