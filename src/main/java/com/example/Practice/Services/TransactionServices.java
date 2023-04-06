package com.example.Practice.Services;

import com.example.Practice.DTO.RefundDto;
import com.example.Practice.DTO.TransactionDto;
import com.example.Practice.Enum.Status;
import com.example.Practice.Model.Transactiions;
import com.example.Practice.Model.User;
import com.example.Practice.Repositories.TransactionRepo;
import com.example.Practice.Repositories.UserRepo;
import jakarta.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServices {

@Autowired
RefundService refundService;
    @Autowired
    TransactionRepo transactionRepo;

    @Autowired
    UserRepo userRepo;


    public String addTransaction(TransactionDto transactionDto) {
        Transactiions transaction = new Transactiions();
        transaction.setTrasactionStatus(Status.PENDING);

        User user = userRepo.findById(transactionDto.getUserId()).get();
        double accountBalance = user.getAccountBalance();
        double transactionAmount = transactionDto.getAmount();
        try {
            if (accountBalance >= transactionAmount) {
                double newAccountBalance = accountBalance - transactionAmount;
                user.setAccountBalance(newAccountBalance);
                transaction.setAmount(transactionAmount);
                transaction.setAmountDeducted(transactionDto.getAmount());
                transaction.setUser(user);
                transaction.setTrasactionStatus(Status.SUCCESS);
            } else {
                return "Insufficient Balance";
            }

            userRepo.save(user);
            transactionRepo.save(transaction);

            return "Transaction successful";
        }
        catch (Exception e){
            transaction.setAmount(transactionAmount);
            transaction.setAmountDeducted(transactionAmount);
            transaction.setUser(user);
            transaction.setTrasactionStatus(Status.FAILED);
            transactionRepo.save(transaction);
            user.setAccountBalance(accountBalance - transactionAmount);
            userRepo.save(user);
        }

        userRepo.save(user);

        return "Transaction Failed";
    }



    public String deleteAllFailedTransactions(){
        List<Transactiions> transactiionsList = transactionRepo.findAll();
        int numDeleted = 0;
        try {
            for (Transactiions transactiions : transactiionsList) {
                if (transactiions.getTrasactionStatus().equals(Status.FAILED)) {
                    transactionRepo.delete(transactiions);
                    numDeleted++;
                }
            }
        }
        catch (Exception e){
            return "Error deleting transactions: " + e.getMessage();
        }
        return "Successfully deleted " + numDeleted + " transactions";

    }


}