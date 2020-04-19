package vn.mrlongg71.vnfood.src.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Review {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("reviews")
    @Expose
    private ReviewDetails reviews = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ReviewDetails getReviews() {
        return reviews;
    }

    public void setReviews(ReviewDetails reviews) {
        this.reviews = reviews;
    }

    public class ReviewDetails {

        @SerializedName("comment")
        @Expose
        private String comment;
        @SerializedName("rate")
        @Expose
        private Double rate;
        @SerializedName("reviewId")
        @Expose
        private String reviewId;
        @SerializedName("userId")
        @Expose
        private List<User> userId = null;

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

        public Double getRate() {
            return rate;
        }

        public void setRate(Double rate) {
            this.rate = rate;
        }

        public String getReviewId() {
            return reviewId;
        }

        public void setReviewId(String reviewId) {
            this.reviewId = reviewId;
        }

        public List<User> getUserId() {
            return userId;
        }

        public void setUserId(List<User> userId) {
            this.userId = userId;
        }

    }

}

