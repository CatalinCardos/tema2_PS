package com.example.tema2.Model;


import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity
public class OrderFromMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Dish> dishList;
    private Status status;
    private float totalCost;
    private Time time;
    private Date date;

    public OrderFromMenu(){

    }

    public OrderFromMenu(List<Dish> dishList, Status status, float totalCost, Time time, Date date) {
        this.dishList = dishList;
        this.status = status;
        this.totalCost = totalCost;
        this.time = time;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public List<Dish> getDishList() {
        return dishList;
    }

    public void setDishList(List<Dish> dishList) {
        this.dishList = dishList;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(float totalCost) {
        this.totalCost = totalCost;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return  id +
                ";" + dishList.toString() +
                ";" + status.toString() +
                ";" + totalCost +
                ";" + time +
                ";" + date;
    }


}
