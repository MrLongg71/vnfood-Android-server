package vn.mrlongg71.vnfood.src.model;

public class OrderDetails {
    private String idOrderDetails;
    private String idOrder;
    private String productId;
    private int amount;
    private double price;

    public OrderDetails(String idOrderDetails, String idOrder, String productId, int amount, double price) {
        this.idOrderDetails = idOrderDetails;
        this.idOrder = idOrder;
        this.productId = productId;
        this.amount = amount;
        this.price = price;

    }

    public OrderDetails() {
    }

    public String getIdOrderDetails() {
        return idOrderDetails;
    }

    public void setIdOrderDetails(String idOrderDetails) {
        this.idOrderDetails = idOrderDetails;
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
