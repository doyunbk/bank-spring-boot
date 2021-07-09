package com.example.kakao.model.DTO;

import com.example.kakao.model.BranchResult;

import lombok.Data;
import org.springframework.stereotype.Component;


import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class BranchDTO {
    private String branchCode;
    private String branchName;


    public BranchDTO BranchDTO(BranchResult branchResult){
        BranchDTO branchDTO = new BranchDTO();

        branchDTO.setBranchCode(branchResult.getBranchCode());
        branchDTO.setBranchName(branchResult.getBranchName());

        return branchDTO;
    }

    public List<BranchDTO> BranchDTOtoList(List<BranchResult> branchResult){
        return branchResult.stream().map(x -> BranchDTO(x)).collect(Collectors.toList());
    }

}
