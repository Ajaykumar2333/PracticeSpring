package com.example.Practice.Model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.sql.ast.tree.from.TableReference;

import javax.xml.crypto.dsig.TransformService;
import java.util.List;

@Entity
@Data
@Table(name = "UserTable")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String accoutNumber;
    
    private double accountBalance;


    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    List<Transactiions> transactiionsList;


    @OneToMany(mappedBy = "user" , cascade = CascadeType.ALL)
    List<Refund> refundList ;
}
