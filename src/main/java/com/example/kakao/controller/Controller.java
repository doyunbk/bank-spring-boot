package com.example.kakao.controller;

import java.util.*;

import com.example.kakao.model.DTO.*;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kakao.model.AccountResult;
import com.example.kakao.service.AccountService;
import com.example.kakao.model.BranchResult;
import com.example.kakao.service.BranchService;
import com.example.kakao.model.TransactionResult;
import com.example.kakao.service.TransactionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(tags = "Sample")
@RestController
@RequestMapping("/test/")
public class Controller {
    @Autowired
    private AccountService accountService;

    @Autowired
    private BranchService branchService;

    @Autowired
    private TransactionService transactionService;

    @ApiOperation(value = "sample")
    @GetMapping(value = "/account")
    public List<AccountResult> getAccountInfo() {
        return accountService.getAccountInfo();
    }

    @ApiOperation(value = "sample")
    @GetMapping(value = "/branch")
    public BranchResult getBranchInfo(String branchCode) {
        return branchService.getBranchByBranchCode(branchCode);
    }

    @ApiOperation(value = "sample")
    @GetMapping(value = "/transaction")
    public List<TransactionResult> getTransactionInfo() {
        return transactionService.getTransaction();
    }

    @ApiOperation(value = "sample")
    @GetMapping(value = "/uncancelled-transaction")
    public List<TransactionResult> getNonCancelledTransactionInfo() {
        return transactionService.getUncancelledTransaction();
    }

    @ApiOperation(value = "sample")
    @GetMapping(value = "/highest-amt-transaction")
    public List<TransactionDTO> getHighestAmtTransactionInfo() {
        return transactionService.getHighestAmtTransaction();
    }

    @ApiOperation(value = "sample")
    @GetMapping(value = "/missing-accounts")
    public List<MissingAccountDTO>  getMissingTransactionInfo() {
        return transactionService.getMissingAccountInfo();
    }

    @ApiOperation(value = "sample")
    @GetMapping(value = "/transaction-amt-by-branch")
    public List<TransactionByBranchDTO> getTransactionTotalAmtByBranch() {
        return transactionService.getTransactionTotalAmtByBranch();
    }

    @ApiOperation(value = "sample")
    @GetMapping(value = "/transaction-by-branch")
    public BranchDataDTO getTransactionByBranch(String branchName) {
        return transactionService.getTransactionByBranch(branchName);
    }

}



