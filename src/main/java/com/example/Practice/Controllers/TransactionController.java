package com.example.Practice.Controllers;

import com.example.Practice.DTO.TransactionDto;
import com.example.Practice.DTO.UserDto;
import com.example.Practice.Services.TransactionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    TransactionServices transactionServices;

    @PostMapping("/addTransaction")
    private ResponseEntity<String> addTransaction(@RequestBody() TransactionDto transactionDto){

        String result = transactionServices.addTransaction(transactionDto);
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @DeleteMapping("/deleteFailedTransactions")
    private ResponseEntity<String > deleteTransactions(){
        String result = transactionServices.deleteAllFailedTransactions();
        return new ResponseEntity<>(result,HttpStatus.ACCEPTED);
    }

}
