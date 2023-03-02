package com.example.pigeonbreedermanagementapplication.Product;

public class ProductGetSet {

    private int product_id;
    private String product_name;
    private int product_price;
    private String product_quantity;
    private String use_per_week;
    private int profile_id;


    public ProductGetSet(int product_id, String product_name, int product_price, String product_quantity, String use_per_week, int profile_id) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_quantity = product_quantity;
        this.use_per_week = use_per_week;
        this.profile_id = profile_id;
    }

    @Override
    public String toString() {
        return "ProductGetSet{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_price=" + product_price +
                ", product_quantity='" + product_quantity + '\'' +
                ", use_per_week='" + use_per_week + '\'' +
                ", profile_id=" + profile_id +
                '}';
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public String getProduct_quantity() {
        return product_quantity;
    }

    public void setProduct_quantity(String product_quantity) {
        this.product_quantity = product_quantity;
    }

    public String getUse_per_week() {
        return use_per_week;
    }

    public void setUse_per_week(String use_per_week) {
        this.use_per_week = use_per_week;
    }

    public int getProfile_id() {
        return profile_id;
    }

    public void setProfile_id(int profile_id) {
        this.profile_id = profile_id;
    }
}
