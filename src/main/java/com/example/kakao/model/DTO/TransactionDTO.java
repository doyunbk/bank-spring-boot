package com.example.kakao.model.DTO;

import com.example.kakao.model.TransactionResult;

import lombok.Data;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class TransactionDTO {
    private String year;
    private String name;
    private String accountNo;
    private Integer amount;

    public TransactionDTO transactionToDto(TransactionResult transaction){
        TransactionDTO transactionDTO = new TransactionDTO();

        transactionDTO.setAccountNo(transaction.getAccountNo());
        transactionDTO.setAmount((transaction.getAmount() - transaction.getFee()));
        transactionDTO.setYear(transaction.getTransactionDate().substring(0, 4));
        transactionDTO.setName(transaction.getAccount().getAccountName());

        return transactionDTO;
    }

    public List<TransactionDTO> transactionToDtoList(List<TransactionResult> transactionResults){
        return transactionResults.stream().map(x -> transactionToDto(x)).collect(Collectors.toList());
    }


}
