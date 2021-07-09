package com.example.kakao.model.DTO;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class TransactionByBranchDTO {
    private String year;
    private List<BranchDataDTO> dataList;

}
