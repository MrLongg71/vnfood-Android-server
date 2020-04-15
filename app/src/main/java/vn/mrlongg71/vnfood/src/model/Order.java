package vn.mrlongg71.vnfood.src.model;

public class Order {
        private String idOrder;
        private int totalAmount;
        private double totalPrice;
        private String date;
        private String status;

    public Order(String idOrder, int totalAmount, double totalPrice, String date, String status) {
        this.idOrder = idOrder;
        this.totalAmount = totalAmount;
        this.totalPrice = totalPrice;
        this.date = date;
        this.status = status;
    }

    public Order() {
    }

    public String getIdOrder() {
        return idOrder;
    }

    public void setIdOrder(String idOrder) {
        this.idOrder = idOrder;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

