package com.example.kakao.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {
    @Id
    private String accountNo;

    private String accountName;

    private String branchCode;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "accountNo", cascade = CascadeType.ALL)
    private List<Transaction> transaction = new ArrayList<>();

//    @OneToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "branch_branchCode", insertable = false, updatable = false)
//    private Branch branch;


//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinTable(name = "acc_branch",
//    joinColumns=
//            {@JoinColumn(name = "accountNo")},
//    inverseJoinColumns =
//            {@JoinColumn(name = "branchCode")})
//    private Branch branch;


}
