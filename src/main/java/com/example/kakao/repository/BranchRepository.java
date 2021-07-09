package com.example.kakao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.kakao.model.BranchResult;
import com.example.kakao.model.entity.Branch;


public interface BranchRepository extends JpaRepository<Branch, String>{

    @Query(value = "SELECT branch_code as branchCode, branch_name as branchName FROM branch WHERE branch_code = :branchCode", nativeQuery = true)
    BranchResult getBranchByBranchCode(@Param("branchCode") String branchCode);

}

