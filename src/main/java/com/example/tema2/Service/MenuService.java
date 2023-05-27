package com.example.tema2.Service;


import com.example.tema2.Model.Image;
import com.example.tema2.Repo.ImageREPO;
import com.example.tema2.Repo.MenuREPO;
import com.example.tema2.Model.Dish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {

    @Autowired
    private MenuREPO menuREPO;


    public Dish createDish(String name, float price, int stock){

        return menuREPO.save(new Dish(name, price, stock, new ArrayList<>()));

    }


    public void updateDish(String name, float price, int stock, MultipartFile[] images){
        Dish dish = findDishByName(name);

        dish.setName(name);
        dish.setPrice(price);
        dish.setStock(stock);
        menuREPO.save(dish);
    }

    public void deleteDish(Dish dish){
        menuREPO.delete(dish);
    }

    public Dish findDishByName(String name){
        System.out.println(name);
        return menuREPO.findByName(name);
    }

    public MenuREPO getMenuDAO() {
        return menuREPO;
    }

    public List<Dish> getAllDishes() {
        return menuREPO.findAll();
    }
}
