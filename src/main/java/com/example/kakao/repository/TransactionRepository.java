package com.example.kakao.repository;

import java.util.List;

import com.example.kakao.model.BranchResult;
import com.example.kakao.model.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.kakao.model.TransactionResult;
import com.example.kakao.model.entity.Transaction;


public interface TransactionRepository extends JpaRepository<Transaction, String>{

    @Query(value = "SELECT transaction_no as transactionNo, account_no as accountNo, " +
            "transaction_date as transactionDate, amount as amount, fee as fee, " +
            "is_cancelled as isCancelled FROM transaction", nativeQuery = true)
    List<TransactionResult> getTransaction();

    @Query(value = "SELECT transaction_no as transactionNo, account_no as accountNo, " +
            "transaction_date as transactionDate, amount as amount, fee as fee, " +
            "is_cancelled as isCancelled FROM transaction WHERE is_cancelled = 'N'", nativeQuery = true)
    List<TransactionResult> getUncancelledTransaction();

    @Query(value = "SELECT transaction FROM Transaction transaction JOIN transaction.account account " +
            "WHERE transaction.isCancelled = 'N'" )
    List<TransactionResult> getUncancelledTransactionWithAccount();

//    @Query(value = "SELECT transaction FROM Transaction transaction, Account account " +
//            "JOIN transaction.account account " +
//            "JOIN account.branch branch " +
//            "WHERE transaction.isCancelled = 'N'" )
//    List<TransactionResult> getUncancelledTransactionWithAccountAndBranch();

}
