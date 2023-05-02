package com.example.tema2.Service;


import com.example.tema2.Repo.MenuREPO;
import com.example.tema2.Model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuREPO menuREPO;

    public void createDish(String name, float price, int stock){

        menuREPO.save(new Dish(name, price, stock));

    }

    public void updateDish(String name, float price, int stock){
        Dish dish = findDishByName(name);
        dish.setName(name);
        dish.setPrice(price);
        dish.setStock(stock);
        menuREPO.save(dish);
    }

    public void deleteDish(String name){
        menuREPO.delete(findDishByName(name));
    }

    public Dish findDishByName(String name){
        System.out.println(name);
        return menuREPO.findAll().stream().filter(dish -> dish.getName().equals(name)).findFirst().orElse(null);
    }

    public MenuREPO getMenuDAO() {
        return menuREPO;
    }

    public List<Dish> getAllDishes() {
        return menuREPO.findAll();
    }
}
