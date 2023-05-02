package com.example.tema2.Repo;

import com.example.tema2.Model.OrderFromMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderREPO extends JpaRepository<OrderFromMenu, Integer> {
}
