package com.example.kakao.repository;

import com.example.kakao.model.AccountResult;
import com.example.kakao.model.TransactionResult;
import com.example.kakao.model.entity.Account;
import com.example.kakao.model.entity.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.hibernate.exception.ConstraintViolationException;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.InstanceOfAssertFactories.predicate;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class TransactionRepositoryTest {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void shouldBeAbleToGetUncancelledTransaction() {
        Account account = new Account();

        account.setAccountNo("54321");
        account.setAccountName("에이스");
        account.setBranchCode("B");

        accountRepository.save(account);

        List<Transaction> transaction = Arrays.asList(
                new Transaction(
                "20210705",
                "54321",
                "2",
                200000,
                200,
                "N",
                        account
                ),
                new Transaction(
                        "20210703",
                        "54321",
                        "3",
                        300000,
                        300,
                        "Y",
                        account
                )
        );

        transactionRepository.saveAll(transaction);

        List<TransactionResult> transactionResults = transactionRepository.getUncancelledTransaction();

        assertThat(transactionResults.get(0).getTransactionDate()).isEqualTo("20210705");
        assertThat(transactionResults.get(0).getAccountNo()).isEqualTo("54321");
        assertThat(transactionResults.get(0).getTransactionNo()).isEqualTo("2");
        assertThat(transactionResults.get(0).getAmount()).isEqualTo(200000);
        assertThat(transactionResults.get(0).getFee()).isEqualTo(200);
        assertThat(transactionResults.get(0).getIsCancelled()).isEqualTo("N");

        assertEquals(transactionResults.size(), 1);

    }
}
