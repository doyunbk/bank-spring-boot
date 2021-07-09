package com.example.kakao.service;

import com.example.kakao.model.DTO.MissingAccountDTO;
import com.example.kakao.model.DTO.TransactionDTO;
import com.example.kakao.model.TransactionResult;
import com.example.kakao.model.entity.Account;
import com.example.kakao.model.entity.Transaction;
import com.example.kakao.repository.AccountRepository;
import com.example.kakao.repository.BranchRepository;
import com.example.kakao.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private AccountService accountService;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private BranchService branchService;

    @Mock
    private TransactionDTO transactionDTO;

    @Mock
    private MissingAccountDTO missingAccountDTO;

    @Mock
    private AccountRepository accountRepository;

    private TransactionService transactionService;

    @BeforeEach
    void setUp() {

        transactionService = new TransactionService(
                accountService,
                transactionRepository,
                branchService,
                transactionDTO,
                missingAccountDTO
        );

    }


    @Test
    void getTransaction() {

        transactionService.getTransaction();
        verify(transactionRepository).getTransaction();

    }

    @Test
    void getUncancelledTransaction() {

        transactionService.getUncancelledTransaction();
        verify(transactionRepository).getUncancelledTransaction();

    }

    @Test
    void getUncancelledTransactionWithAccount() {

        transactionService.getUncancelledTransactionWithAccount();
        verify(transactionRepository).getUncancelledTransactionWithAccount();

    }

    @Test
    void getLargestAmtTransaction() {
    }

    @Test
    void getMissingTransactionAccount() {
    }

    @Test
    void getTransactionTotalAmtByBranch() {
    }

    @Test
    void getTransactionByBranch() {
    }
}