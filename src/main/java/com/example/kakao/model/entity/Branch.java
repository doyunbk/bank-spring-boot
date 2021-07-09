package com.example.kakao.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "branch")
public class Branch {
    @Id
    private String branchCode;

    private String branchName;


//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "branch", cascade = CascadeType.ALL)
//    private Account account;


//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "branchCode")
//    private List<Account> account;

}
