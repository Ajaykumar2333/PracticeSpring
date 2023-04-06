package com.example.Practice.DTO;


import lombok.Data;

@Data
public class RefundDto {

    private double amount;
    private int userId;
    private int transactionId;
}
