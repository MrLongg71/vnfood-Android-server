package vn.mrlongg71.vnfood.src.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Gift {
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("idGift")
    @Expose
    private String idGift;
    @SerializedName("codeGift")
    @Expose
    private String codeGift;
    @SerializedName("amount")
    @Expose
    private String amount;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("expiration_at")
    @Expose
    private String expirationAt;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("update_at")
    @Expose
    private String updateAt;
    @SerializedName("__v")
    @Expose
    private Integer v;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdGift() {
        return idGift;
    }

    public void setIdGift(String idGift) {
        this.idGift = idGift;
    }

    public String getCodeGift() {
        return codeGift;
    }

    public void setCodeGift(String codeGift) {
        this.codeGift = codeGift;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getExpirationAt() {
        return expirationAt;
    }

    public void setExpirationAt(String expirationAt) {
        this.expirationAt = expirationAt;
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

}
