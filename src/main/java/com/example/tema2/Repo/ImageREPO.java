package com.example.tema2.Repo;

import com.example.tema2.Model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageREPO extends JpaRepository<Image,Integer> {
    Image findByName(String name);
}
