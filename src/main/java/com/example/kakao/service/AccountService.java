package com.example.kakao.service;

import java.util.List;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kakao.model.AccountResult;
import com.example.kakao.repository.AccountRepository;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@Transactional
@AllArgsConstructor
public class AccountService {

    private AccountRepository accountRepository;

    public List<AccountResult> getAccountInfo(){
        List<AccountResult> accountList = accountRepository.getAccountInfo();

        return accountList;
    }

    public AccountResult getAccountNameByAccountNo(@RequestParam(value = "accountNo") String accountNo){
        AccountResult accountName = accountRepository.getAccountNameByAccountNo(accountNo);

        return accountName;
    }

    public List<String> getAccountNo(){
        List<String> accountList = accountRepository.getAccountNo();

        return accountList;
    }
}