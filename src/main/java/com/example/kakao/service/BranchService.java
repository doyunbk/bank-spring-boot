package com.example.kakao.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.kakao.model.BranchResult;
import com.example.kakao.repository.BranchRepository;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@Transactional
@AllArgsConstructor
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public BranchResult getBranchByBranchCode(@RequestParam(value = "branchCode") String branchCode){
        BranchResult branch = branchRepository.getBranchByBranchCode(branchCode);
        return branch;
    }

}
