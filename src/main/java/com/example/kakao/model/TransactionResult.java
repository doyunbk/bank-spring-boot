package com.example.kakao.model;

import com.example.kakao.model.entity.Account;

public interface TransactionResult {

    String getTransactionNo();
    String getAccountNo();
    String getTransactionDate();
    Integer getAmount();
    Integer getFee();
    String getIsCancelled();
    Account getAccount();

}
