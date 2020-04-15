package vn.mrlongg71.vnfood.src.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Product implements Parcelable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("price")
    @Expose
    private String price;
    @SerializedName("image")
    @Expose
    private String image;
    @SerializedName("rate")
    @Expose
    private double rate;
    @SerializedName("countComment")
    @Expose
    private int countComment;
    @SerializedName("cateId")
    @Expose
    private String cateId;
    @SerializedName("productId")
    @Expose
    private String productId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("update_at")
    @Expose
    private String updateAt;
    @SerializedName("__v")
    @Expose
    private Integer v;
    @SerializedName("cate")
    @Expose
    private List<Cate> cate = null;

    public Product(String id, String name, String description, String price, String image, double rate, int countComment, String cateId, String productId, String createdAt, String updateAt, Integer v, List<Cate> cate) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.image = image;
        this.rate = rate;
        this.countComment = countComment;
        this.cateId = cateId;
        this.productId = productId;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.v = v;
        this.cate = cate;
    }

    public Product() {
    }

    protected Product(Parcel in) {
        id = in.readString();
        name = in.readString();
        description = in.readString();
        price = in.readString();
        image = in.readString();
        rate = in.readDouble();
        countComment = in.readInt();
        cateId = in.readString();
        productId = in.readString();
        createdAt = in.readString();
        updateAt = in.readString();
        if (in.readByte() == 0) {
            v = null;
        } else {
            v = in.readInt();
        }
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getCountComment() {
        return countComment;
    }

    public void setCountComment(int countComment) {
        this.countComment = countComment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCateId() {
        return cateId;
    }

    public void setCateId(String cateId) {
        this.cateId = cateId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

    public List<Cate> getCate() {
        return cate;
    }

    public void setCate(List<Cate> cate) {
        this.cate = cate;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(description);
        dest.writeString(price);
        dest.writeString(image);
        dest.writeDouble(rate);
        dest.writeInt(countComment);
        dest.writeString(cateId);
        dest.writeString(productId);
        dest.writeString(createdAt);
        dest.writeString(updateAt);
        if (v == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(v);
        }
    }
}