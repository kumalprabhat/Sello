package com.example.sello;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class sello_model implements Serializable {

    private String product_name;
    private String product_price;
    private String product_og_price;
    private String product_description;
    private byte[] image;

    public sello_model(String product_name, String product_price, String product_og_price, String product_description, byte[] image) {
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_og_price = product_og_price;
        this.product_description = product_description;
        this.image = image;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }

    public void setProduct_og_price(String product_og_price) {
        this.product_og_price = product_og_price;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_price() {
        return product_price;
    }

    public String getProduct_og_price() {
        return product_og_price;
    }

    public String getProduct_description() {
        return product_description;
    }

    public byte[] getImage() {
        return image;
    }

    @NonNull
    @Override
    public String toString() {
        return getProduct_name();
    }
}
