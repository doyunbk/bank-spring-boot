package com.example.kakao.model.DTO;

import com.example.kakao.model.TransactionResult;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class MissingAccountDTO {
    private String year;
    private String name;
    private String accountNo;

    public MissingAccountDTO missingAccountToDto(TransactionResult transaction){
        MissingAccountDTO missingTransactionDTO = new MissingAccountDTO();

        missingTransactionDTO.setAccountNo(transaction.getAccountNo());
        missingTransactionDTO.setYear(transaction.getTransactionDate().substring(0, 4));
        missingTransactionDTO.setName(transaction.getAccount().getAccountName());


        return missingTransactionDTO;
    }

    public List<MissingAccountDTO> missingAccountDTOList(List<TransactionResult> transactionResults){
        return transactionResults.stream().map(x -> missingAccountToDto(x)).collect(Collectors.toList());
    }

}
