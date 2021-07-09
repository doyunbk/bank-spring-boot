package com.example.kakao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.kakao.model.AccountResult;
import com.example.kakao.model.entity.Account;



public interface AccountRepository extends JpaRepository<Account, String> {

    @Query(value = "SELECT account_no as accountNo, account_name as accountName, branch_code as branchCode FROM account ", nativeQuery = true)
    List<AccountResult> getAccountInfo();

    @Query(value = "SELECT account_no as accountNo, account_name as accountName, branch_code as branchCode FROM account WHERE account_no = :accountNo", nativeQuery = true)
    AccountResult getAccountNameByAccountNo(@Param("accountNo") String accountNo);

    @Query(value = "SELECT account_no as accountNo FROM account", nativeQuery = true)
    List<String> getAccountNo();

}

