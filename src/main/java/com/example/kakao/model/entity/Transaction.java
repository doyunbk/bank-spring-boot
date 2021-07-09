package com.example.kakao.model.entity;

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
@Table(name = "transaction")
public class Transaction {
    @Id
    private String transactionDate;

    private String accountNo;

    private String transactionNo;

    private Integer amount;

    private Integer fee;

    private String isCancelled;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "accountNo", insertable = false, updatable = false)
    private Account account;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "branchCode", insertable = false, updatable = false)
//    private Branch branch;

}