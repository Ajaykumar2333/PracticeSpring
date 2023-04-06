package com.example.Practice.Services;


import com.example.Practice.DTO.AddBalnce;
import com.example.Practice.DTO.UserDto;
import com.example.Practice.Model.User;
import com.example.Practice.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServices {

    @Autowired
    UserRepo userRepo;

    public String addUser(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setAccountBalance(0.0);
        user.setAccoutNumber(userDto.getAccoutNumber());

        userRepo.save(user);

        return "Succesfully added";
    }

    public String addBalancce(AddBalnce addBalnce){
        int id= addBalnce.getUserId();
        User user = userRepo.findById(id).get();
        double accountBalance = user.getAccountBalance();
        double newAccountBalance = accountBalance + addBalnce.getAddAmount();
        user.setAccountBalance(newAccountBalance);
        userRepo.save(user);
        return "Amount added Succesfully to  user " + user.getName()+" " + "amount" + addBalnce.getAddAmount() + " "+"TotalAmount" +" "+ user.getAccountBalance();
    }
}
