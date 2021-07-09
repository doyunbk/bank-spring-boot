package com.example.kakao.infra;
import java.lang.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import com.example.kakao.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.example.kakao.model.entity.Account;
import com.example.kakao.repository.AccountRepository;
import com.example.kakao.model.entity.Branch;
import com.example.kakao.repository.BranchRepository;
import com.example.kakao.model.entity.Transaction;

@Component
public class InitData {

    @Autowired
    AccountRepository accountRepository;

    @PostConstruct
    private void initAccount() throws IOException {
        if (accountRepository.count() == 0) {
            Resource resource = new ClassPathResource("계좌정보.csv");
            List<Account> accountList = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8)
                    .stream().skip(1).map(line -> {
                        String[] split = line.split(",");
                        return Account.builder().accountNo(split[0]).accountName(split[1]).branchCode(split[2])
                                .build();
                    }).collect(Collectors.toList());
            accountRepository.saveAll(accountList);
        }
    }

    @Autowired
    BranchRepository branchRepository;

    @PostConstruct
    private void initBranch() throws IOException{
        if (branchRepository.count() == 0) {
            Resource resource = new ClassPathResource("데이터_관리점정보.csv");
            List<Branch> branchList = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8)
                    .stream().skip(1).map(line -> {
                        String[] split = line.split(",");
                        return Branch.builder().branchCode(split[0]).branchName(split[1])
                                .build();
                    }).collect(Collectors.toList());
            branchRepository.saveAll(branchList);
        }
    }

    @Autowired
    TransactionRepository transactionRepository;

    @PostConstruct
    private void initTransaction() throws IOException{
        if (transactionRepository.count() == 0) {
            Resource resource = new ClassPathResource("데이터_거래내역.csv");
            List<Transaction> transactionListList = Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8)
                    .stream().skip(1).map(line -> {
                        String[] split = line.split(",");
                        return Transaction.builder().transactionDate(split[0]).accountNo(split[1]).transactionNo(split[2])
                                .amount(Integer.parseInt(split[3])).fee(Integer.parseInt(split[4])).isCancelled(split[5])
                                .build();
                    }).collect(Collectors.toList());
            transactionRepository.saveAll(transactionListList);
        }
    }
}
