package vn.mrlongg71.vnfood.src.model;

public class OrderProvisional {
    private Product product;
    private int amount;

    public OrderProvisional(Product product, int amount) {
        this.product = product;
        this.amount = amount;
    }

    public OrderProvisional() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
