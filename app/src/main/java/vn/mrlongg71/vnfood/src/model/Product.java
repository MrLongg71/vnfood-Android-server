package vn.mrlongg71.vnfood.src.model;

import java.util.List;

public class Product {
    private String productID,name,description,cateID;
    private double rate;
    private List<Images> imagesProduct;

    public Product(String productID, String name, String description, String cateID, double rate, List<Images> imagesProduct) {
        this.productID = productID;
        this.name = name;
        this.description = description;
        this.cateID = cateID;
        this.rate = rate;
        this.imagesProduct = imagesProduct;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
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

    public String getCateID() {
        return cateID;
    }

    public void setCateID(String cateID) {
        this.cateID = cateID;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public List<Images> getImagesProduct() {
        return imagesProduct;
    }

    public void setImagesProduct(List<Images> imagesProduct) {
        this.imagesProduct = imagesProduct;
    }
}
