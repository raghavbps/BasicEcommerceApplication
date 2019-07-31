package com.firstproject.basicecommercewebsite.model;

import javax.validation.constraints.Min;

public class OrderPlaced {

    private int order_id;

    private int user_id;

    private int product_id;

    @Min(1)
    private int quantity_bought;

    private String placing_time;

    public OrderPlaced() {
    }

    public OrderPlaced(int user_id, int product_id, @Min(1) int quantity_bought, String placing_time) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.quantity_bought = quantity_bought;
        this.placing_time = placing_time;
    }

    public OrderPlaced(int user_id, int product_id, @Min(1) int quantity_bought) {
        this.user_id = user_id;
        this.product_id = product_id;
        this.quantity_bought = quantity_bought;
    }

    public String getPlacing_time() {
        return placing_time;
    }

    public void setPlacing_time(String placing_time) {
        this.placing_time = placing_time;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getQuantity_bought() {
        return quantity_bought;
    }

    public void setQuantity_bought(int quantity_bought) {
        this.quantity_bought = quantity_bought;
    }

    @Override
    public String toString() {
        return "OrderPlaced{" +
                "order_id=" + order_id +
                ", user_id=" + user_id +
                ", product_id=" + product_id +
                ", quantity_bought=" + quantity_bought +
                ", placing_time='" + placing_time + '\'' +
                '}';
    }
}
