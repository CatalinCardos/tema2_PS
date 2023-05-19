package com.example.tema2.Controller;

import com.example.tema2.Model.Dish;
import com.example.tema2.Service.MenuService;
import jxl.read.biff.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/admin")
    public String adminPage(Model model){
        model.addAttribute("dishes", menuService.getAllDishes());
        return "admin";
    }
    
    @GetMapping("/addOrder")
    public String getAllDishes(Model model){
        model.addAttribute("dishes", menuService.getAllDishes());
        return "addOrder";
    }

    @GetMapping("/addDish")
    public String addDish(){
        return "addDish";
    }

    record DishAux(String name, float price, int stock, List<File> images) {}
    @PostMapping("/addDish")
    public void addDish(@RequestBody DishAux dishAux){
        System.out.println(dishAux.name() + " " + dishAux.price() + " " + dishAux.stock() + " " + dishAux.images());
        //menuService.createDish(dishAux.name, dishAux.price, dishAux.stock);
    }

    @GetMapping("/deleteDish")
    public String deleteDish(Model model){
        model.addAttribute("dishes", menuService.getAllDishes());
        return "deleteDish";

    }

    @DeleteMapping("/deleteDish")
    public void deleteDish(@RequestBody String dishName){
        dishName = dishName.substring(1, dishName.length()-1); // remove the "" from the string (from the JSON
        String finalName = dishName;
        menuService.deleteDish(finalName);
    }

    @GetMapping("/modifyDish")
    public String updateDish(@RequestParam(name = "name", required = false, defaultValue = "") String name,
                                Model model){

        model.addAttribute("dishes", menuService.getAllDishes());
        if(name.equals("")){
            return "modifyDish";
        }
        model.addAttribute("dishSelected", menuService.findDishByName(name));
        return "modifyDish";
    }

    record DishToSent(String name, float price, int stock, String images) {}
    @GetMapping("/getBodyDish")
    @ResponseBody
    public DishToSent updateDish(@RequestParam(name = "name", required = false, defaultValue = "") String name){
        Dish dish = menuService.findDishByName(name);
        DishToSent dishToSent = new DishToSent(dish.getName(), dish.getPrice(), dish.getStock(), dish.getImages());
        return dishToSent;
    }

    @PutMapping("/modifyDish")
    public void updateDish(@RequestBody DishAux dish){
        menuService.updateDish(dish.name(), dish.price(), dish.stock());
    }

}
