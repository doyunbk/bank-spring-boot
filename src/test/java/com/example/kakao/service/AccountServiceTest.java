package com.example.kakao.service;

import com.example.kakao.model.entity.Account;
import com.example.kakao.repository.AccountRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;

import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceTest {

    @Mock
    private AccountRepository accountRepository;

    private AccountService accountService;


    @BeforeEach
    void setUp() {

        accountService = new AccountService(accountRepository);

    }

    @Test
    void canGetAccountInfo() {

        accountService.getAccountInfo();
        verify(accountRepository).getAccountInfo();

    }
    @Test
    void getAccountNameByAccountNo() {

        accountService.getAccountNameByAccountNo("11111111");
        verify(accountRepository).getAccountNameByAccountNo("11111111");

    }
}