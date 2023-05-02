package com.example.tema2.Service;


import com.example.tema2.Model.User;
import com.example.tema2.Repo.UserREPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Service
public class UserService {

    @Autowired
    private UserREPO userREPO;

    public User login(String username, String password){

       return userREPO.findAll().stream().filter(e -> e.getUsername().equals(username) && e.getPassword().equals(password)).findFirst().orElse(null);
    }

    public static String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
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
