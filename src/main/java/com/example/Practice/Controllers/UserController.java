package com.example.Practice.Controllers;


import com.example.Practice.DTO.AddBalnce;
import com.example.Practice.DTO.UserDto;
import com.example.Practice.Model.User;
import com.example.Practice.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/User")
@RestController
public class UserController {

    @Autowired
    UserServices userServices;

    @PostMapping("/addUser")
    private ResponseEntity<String> addUser(@RequestBody()  UserDto userDto){

         String result = userServices.addUser(userDto);
         return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/addAmount")
    private ResponseEntity<String> addAmount(@RequestBody()AddBalnce addBalnce){
        String Result = userServices.addBalancce(addBalnce);
        return new ResponseEntity<>(Result,HttpStatus.CREATED);
    }
}
