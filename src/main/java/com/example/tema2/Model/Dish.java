package com.example.tema2.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(mappedBy = "dishList")
    private List<OrderFromMenu> orders;

    private String name;
    private float price;
    private int stock;

    public Dish() {
    }

    public Dish(String name, float price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<OrderFromMenu> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderFromMenu> orders) {
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return " " + name;
    }
}
