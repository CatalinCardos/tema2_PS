package com.example.tema2.Service;


import com.example.tema2.Model.User;
import com.example.tema2.Repo.UserREPO;
import com.example.tema2.Utils.MD5PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserREPO userREPO;

    public User login(String username, String password){

       return userREPO.findAll().stream().filter(e -> e.getUsername().equals(username) && e.getPassword().equals(password)).findFirst().orElse(null);
    }

    public static String getMd5(String input)
    {
        MD5PasswordEncoder md5PasswordEncoder = new MD5PasswordEncoder();
        String md5 = md5PasswordEncoder.encode(input);
        return md5;
    }

    public void createAccount(String name, String username, String password, String role){
        User user = new User(name,username, password, role);
        user.setPassword(getMd5(user.getPassword()));
        userREPO.save(user);
    }

    public User findUserByName(String name) {
        return userREPO.findAll().stream().filter(e -> e.getUsername().equals(name)).findFirst().orElse(null);
    }
}
