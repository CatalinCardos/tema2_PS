package com.example.tema2.Repo;


import com.example.tema2.Model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuREPO extends JpaRepository<Dish,Integer> {
    Dish findByName(String name);
}
