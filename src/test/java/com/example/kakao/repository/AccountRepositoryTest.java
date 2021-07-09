package com.example.kakao.repository;

import com.example.kakao.model.AccountResult;
import com.example.kakao.model.entity.Account;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void shouldBeAbleToGetAccountInfo() {
        Account account = new Account();

        account.setAccountNo("12345");
        account.setAccountName("에이스");
        account.setBranchCode("B");

        accountRepository.save(account);

        List<AccountResult> accountResult = accountRepository.getAccountInfo();

        assertFalse(accountResult.isEmpty());
        assertThat(accountResult.get(0).getAccountName()).isEqualTo("에이스");
        assertThat(accountResult.get(0).getAccountNo()).isEqualTo("12345");
        assertThat(accountResult.get(0).getBranchCode()).isEqualTo("B");

        assertThat(accountResult.get(0).getAccountName()).isNotEqualTo("제이");
        assertThat(accountResult.get(0).getAccountNo()).isNotEqualTo("54321");
        assertThat(accountResult.get(0).getBranchCode()).isNotEqualTo("A");
    }

    @Test
    void shouldAbleToGetAccountNameByAccountNo() {
        Account account = new Account();

        account.setAccountNo("12345");
        account.setAccountName("에이스");
        account.setBranchCode("B");

        accountRepository.save(account);

        String accountNo = account.getAccountNo();

        AccountResult accountResult = accountRepository.getAccountNameByAccountNo(accountNo);

        assertThat(accountResult.getAccountName()).isEqualTo("에이스");
        assertThat(accountResult.getAccountNo()).isEqualTo("12345");
        assertThat(accountResult.getBranchCode()).isEqualTo("B");

        assertThat(accountResult.getAccountName()).isNotEqualTo("제이");
        assertThat(accountResult.getAccountNo()).isNotEqualTo("54321");
        assertThat(accountResult.getBranchCode()).isNotEqualTo("A");
    }
}