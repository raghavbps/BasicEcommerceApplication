package com.firstproject.basicecommercewebsite.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Product {

    private int product_id;

    public Product() {
    }

    public Product(@NotEmpty(message = "Name cannot be empty") String product_name, @NotEmpty(message = "Type cannot be empty") String product_type, @Min(1) int quantity_available, @Min(1) int price, String product_description) {
        this.product_name = product_name;
        this.product_type = product_type;
        this.quantity_available = quantity_available;
        this.price = price;
        this.product_description = product_description;
    }

    @NotEmpty(message = "Name cannot be empty")
    private String product_name;

    @NotEmpty(message = "Type cannot be empty")
    private String product_type;

    @Min(1)
    private int quantity_available;

    @Min(1)
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    private String product_description;

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

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public int getQuantity_available() {
        return quantity_available;
    }

    public void setQuantity_available(int quantity_available) {
        this.quantity_available = quantity_available;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_type='" + product_type + '\'' +
                ", quantity_available=" + quantity_available +
                ", price=" + price +
                ", product_description='" + product_description + '\'' +
                '}';
    }
}
