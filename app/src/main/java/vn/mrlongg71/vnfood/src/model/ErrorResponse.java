package vn.mrlongg71.vnfood.src.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ErrorResponse {
    @Expose
    @SerializedName("statusCode")
    private int statusCode;

    @Expose
    @SerializedName("err")
    private String err;

    public ErrorResponse() {
    }

    public ErrorResponse(int statusCode, String err) {
        this.statusCode = statusCode;
        this.err = err;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }
}
