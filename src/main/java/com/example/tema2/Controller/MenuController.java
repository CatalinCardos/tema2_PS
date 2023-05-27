package com.example.tema2.Controller;

import com.example.tema2.Model.Dish;
import com.example.tema2.Model.Image;
import com.example.tema2.Service.ImageService;
import com.example.tema2.Service.MenuService;
import org.hibernate.engine.jdbc.BlobProxy;
import org.hibernate.type.descriptor.java.BlobJavaType;
import org.hibernate.type.descriptor.jdbc.BlobJdbcType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.sql.rowset.serial.SerialBlob;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private ImageService imageService;

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

    record DishAux(String name, float price, int stock) {}

    @PostMapping("/addDish")
    public void addDish(@RequestPart("images") MultipartFile[] images, @RequestParam("name") String name, @RequestParam("price") float price, @RequestParam("stock") int stock) {
        Dish dish = menuService.createDish(name, price, stock);
        try {
            imageService.saveImages(dish, images);
        } catch (IOException e) {
            e.printStackTrace();
        }

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
        Dish dish = menuService.findDishByName(finalName);
        imageService.deleteImages(dish);
        menuService.deleteDish(dish);
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


    record DishToUpdate(String name, float price, int stock, List<Image> images) {}
    @GetMapping("/getBodyDish")
    @ResponseBody
    public DishToUpdate updateDish(@RequestParam(name = "name", required = false, defaultValue = "") String name){
        return new DishToUpdate(name, menuService.findDishByName(name).getPrice(), menuService.findDishByName(name).getStock(), menuService.findDishByName(name).getImages());
    }

    @PutMapping("/modifyDish")
    public void updateDish(@RequestPart(value = "images", required = false) MultipartFile[] images, @RequestParam("name") String name, @RequestParam("price") float price, @RequestParam("stock") int stock) {
        try {
            if(images != null)
            imageService.saveImages(menuService.findDishByName(name), images);
        } catch (IOException e) {
            e.printStackTrace();
        }
        menuService.updateDish(name, price, stock, images);
    }

}
