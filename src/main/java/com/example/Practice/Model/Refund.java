package com.example.Practice.Model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Refund")
public class Refund {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double amount;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private Transactiions transactiions;

}
