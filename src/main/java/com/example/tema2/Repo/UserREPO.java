package com.example.tema2.Repo;


import com.example.tema2.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserREPO extends JpaRepository<User,Integer> {
}
