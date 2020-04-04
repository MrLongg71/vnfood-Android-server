package vn.mrlongg71.vnfood.src.model;

public class Images {
    private String imageID,imageUrl;

    public Images(String imageID, String imageUrl) {
        this.imageID = imageID;
        this.imageUrl = imageUrl;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
