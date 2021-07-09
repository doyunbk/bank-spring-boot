package com.example.kakao.service;

import com.example.kakao.model.entity.Branch;
import com.example.kakao.repository.AccountRepository;
import com.example.kakao.repository.BranchRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BranchServiceTest {

    @Mock
    private BranchRepository branchRepository;

    private BranchService branchService;


    @BeforeEach
    void setUp() {

        branchService = new BranchService(branchRepository);

    }

    @Test
    void getBranchByBranchCode() {

        branchService.getBranchByBranchCode("B");
        verify(branchRepository).getBranchByBranchCode("B");

    }
}