package com.example.project2.Controller;

//import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.project2.Entity.User;
import com.example.project2.Repository.UserRepository;



@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    //Creating a user
    @PostMapping("/user")
    public ResponseEntity<?> createUser(@RequestBody User user){
        if(userRepository.existsByUserName(user.getUserName())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username already exists");
        }
        else{
            String generatedPassword = generateRandomPassword(user.getUserName());
            user.setPassword(generatedPassword);
            userRepository.save(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User succesfully created. Password is :"+generatedPassword);
        }
    }

    private String generateRandomPassword(String username) {
        StringBuilder password = new StringBuilder();
        for (char c : username.toCharArray()) {
            int asciiValue = (int) c;
            password.append(asciiValue);
        }
        return password.toString();
        
    }

    //To get all the users
    @GetMapping("/users")
    public List<User> getAllUsers(){
        return userRepository.findByDeletedFalse();
    }

    //To get a user by id
    @GetMapping("/users/{id}")
    public ResponseEntity<?> getUserById(@PathVariable int id ){
        User user = userRepository.findByIdAndDeletedFalse(id);
        if(user!=null){
            return ResponseEntity.ok(user);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("user not found");
        }
    }

    //Update a user by id
    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable int id,@RequestBody User updatedUser){
        User exUser = userRepository.findById(id).orElse(null);
        if(exUser != null){
            exUser.setEmail(updatedUser.getEmail());
            exUser.setName(updatedUser.getName());
            exUser.setUserName(updatedUser.getUserName());
            exUser.setPassword(updatedUser.getPassword());
            return userRepository.save(exUser);
        }
        return null;
    }

    //Delete user by id
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        User user = userRepository.findById(id).orElse(null);
        if(user!=null){
            user.setDeleted(true);
            userRepository.save(user);
            return ResponseEntity.ok("User deleted");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }


}

