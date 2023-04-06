package com.example.Practice.Model;

import com.example.Practice.Enum.Status;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "TransactionsTable")
public class Transactiions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double amount;
    private double amountDeducted;

    @Enumerated(EnumType.STRING)
    private Status trasactionStatus;


    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "User_Id",nullable = false)
    private User user;

    @OneToMany(mappedBy = "transactiions" , cascade = CascadeType.ALL)
    List<Refund> refundList ;

    @CreationTimestamp
    private LocalDateTime time;


}
