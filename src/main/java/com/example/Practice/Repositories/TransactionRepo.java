package com.example.Practice.Repositories;

import com.example.Practice.Enum.Status;
import com.example.Practice.Model.Transactiions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepo extends JpaRepository<Transactiions,Integer> {




}
