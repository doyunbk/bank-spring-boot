package com.example.kakao.model.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class BranchDataDTO {
    private String branchCode;
    private String branchName;
    private int sumAmt;

//    private TransactionResult transactionResult;
//
//    public BranchDataDTO BranchDTO(BranchResult branchResult){
//        BranchDataDTO branchDataDTO = new BranchDataDTO();

//        branchDataDTO.setBranchCode(branchResult.getBranchCode());
//        branchDataDTO.setBranchCode(branchResult.getBranchName());
//        branchDataDTO.setSumAmt(transactionResult.getAmount() - transactionResult.getFee());
//
//        return branchDataDTO;
//    }

//    public List<BranchDataDTO> BranchDTOtoList(List<BranchResult> branchResult){
//        return branchResult.stream().map(x -> BranchDTO(x)).collect(Collectors.toList());
//    }

}
