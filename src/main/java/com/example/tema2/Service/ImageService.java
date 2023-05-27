package com.example.tema2.Service;

import com.example.tema2.Model.Dish;
import com.example.tema2.Model.Image;
import com.example.tema2.Repo.ImageREPO;
import com.example.tema2.Repo.MenuREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageREPO imageREPO;

    @Autowired
    MenuREPO menuREPO;
    public List<Image> imageUploader(Dish dish, MultipartFile[] images) throws IOException{
        String folder = "D:\\TEMA2\\src\\main\\resources\\static\\images\\";
        List<Image> imageListForDish = new ArrayList<>();
        for (MultipartFile image : images) {
            String imageName = image.getOriginalFilename();
            imageListForDish.add(imageREPO.save(new Image(imageName, dish)));
            byte[]  bytes = image.getBytes();
            Path path = Paths.get(folder + imageName);
            Files.write(path, bytes);
        }
        return imageListForDish;
    }
    public void saveImages(Dish dish, MultipartFile[] images) throws IOException  {

        dish.setImages(imageUploader(dish, images));
        menuREPO.save(dish);
    }
    public void updateImages(Dish dish, MultipartFile[] images) throws IOException {
        List<Image> imageList = imageUploader(dish, images);
        dish.getImages().addAll(imageList);
        menuREPO.save(dish);
    }
    public void deleteImages(Dish dish) {
        imageREPO.deleteAll(dish.getImages());
    }
}
