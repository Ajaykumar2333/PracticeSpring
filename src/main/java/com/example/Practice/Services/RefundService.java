package com.example.Practice.Services;


import com.example.Practice.DTO.RefundDto;
import com.example.Practice.Enum.Status;
import com.example.Practice.Model.Refund;
import com.example.Practice.Model.Transactiions;
import com.example.Practice.Model.User;
import com.example.Practice.Repositories.RefundRepo;
import com.example.Practice.Repositories.TransactionRepo;
import com.example.Practice.Repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RefundService {


    @Autowired
    private RefundRepo refundRepository;
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private TransactionRepo transactionRepo;
    public String createRefund(RefundDto refundDto) throws Exception {

        Refund refund = new Refund();
        User user = userRepo.findById(refundDto.getUserId()).orElseThrow(() -> new Exception("User not found"));

        Transactiions transactiions = transactionRepo.findById(refundDto.getTransactionId()).orElseThrow(() -> new Exception("Transaction not found"));



        if (transactiions.getTrasactionStatus().equals(Status.FAILED)){

            if(refundDto.getAmount()  == transactiions.getAmountDeducted()){
                refund.setTransactiions(transactiions);
                refund.setAmount(transactiions.getAmountDeducted());
                refund.setUser(user);

                user.setAccountBalance(refund.getAmount() + user.getAccountBalance());
                transactiions.setTrasactionStatus(Status.REFUNDED);
            }
            else{
                return "Transaction Cannot be refunded";
            }
        }else {
            return "Your transaction is already successfull";
        }


        refundRepository.save(refund);
        userRepo.save(user);
        transactionRepo.save(transactiions);

        return  "Congratulations your  amount has been " + refundDto.getAmount() + " " + "Refunded Successfully to " + " " + user.getName() +"at time" + LocalDateTime.now();
//
    }

    public String getUserIdWithMaxRefund() {
        // Fetch all refunds from the database
        List<Refund> refunds = refundRepository.findAll();

        // Map to store refund amounts for each user
        Map<Integer, Double> refundAmounts = new HashMap<>();

        // Calculate refund amounts for each user
        for (Refund refund : refunds) {
            int userId = refund.getUser().getId();
            double refundAmount = refund.getAmount();

            // If user is not present in the map, add him with his refund amount
            if (!refundAmounts.containsKey(userId)) {
                refundAmounts.put(userId, refundAmount);
            } else { // If user is present, add his refund amount to existing amount
                double existingAmount = refundAmounts.get(userId);
                refundAmounts.put(userId, existingAmount + refundAmount);
            }
        }

        // Find user with maximum refund amount
        int userIdWithMaxRefund = -1;
        double maxRefundAmount = 0;
        for (Map.Entry<Integer, Double> entry : refundAmounts.entrySet()) {
            int userId = entry.getKey();
            double refundAmount = entry.getValue();

            if (refundAmount > maxRefundAmount) {
                userIdWithMaxRefund = userId;
                maxRefundAmount = refundAmount;
            }
        }

        // Return userId with maximum refund amount
        return "User with ID " + userIdWithMaxRefund + " has maximum refund amount of " + maxRefundAmount;
    }

}

