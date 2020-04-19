package vn.mrlongg71.vnfood.src.model;

public class Order {
    private String orderId;
        private int totalAmount;
        private double totalPrice;
        private String status;

    public Order(String orderId, int totalAmount, double totalPrice, String status) {
        this.orderId = orderId;
        this.totalAmount = totalAmount;
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public Order() {
    }



    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }



    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

