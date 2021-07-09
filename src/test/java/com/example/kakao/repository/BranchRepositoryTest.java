package com.example.kakao.repository;

import com.example.kakao.model.AccountResult;
import com.example.kakao.model.BranchResult;
import com.example.kakao.model.entity.Account;
import com.example.kakao.model.entity.Branch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BranchRepositoryTest {

    @Autowired
    BranchRepository branchRepository;


    @Test
    void shouldBeAbleToGetBranchByBranchCode() {
        Branch branch = new Branch();

        branch.setBranchCode("A");
        branch.setBranchName("판교점");

        branchRepository.save(branch);

        String branchCode = branch.getBranchCode();

        BranchResult branchResult = branchRepository.getBranchByBranchCode(branchCode);

        assertThat(branchResult.getBranchCode()).isEqualTo("A");
        assertThat(branchResult.getBranchName()).isEqualTo("판교점");

        assertThat(branchResult.getBranchCode()).isNotEqualTo("B");
        assertThat(branchResult.getBranchName()).isNotEqualTo("분당점");

    }
}