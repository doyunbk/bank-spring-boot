package com.example.kakao.service;

import com.example.kakao.exception.RequestException;
import com.example.kakao.model.DTO.*;
import com.example.kakao.model.AccountResult;
import com.example.kakao.model.BranchResult;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.example.kakao.model.TransactionResult;
import com.example.kakao.repository.TransactionRepository;

import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor
public class TransactionService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private BranchService branchService;

    @Autowired
    private TransactionDTO transactionDTO;

    @Autowired
    private MissingAccountDTO missingAccountDTO;


    public List<TransactionResult> getTransaction(){
        List<TransactionResult> transactionList = transactionRepository.getTransaction();

        return transactionList;
    }

    public List<TransactionResult> getUncancelledTransaction(){
        List<TransactionResult> transactionList = transactionRepository.getUncancelledTransaction();

        return transactionList;
    }

    public List<TransactionResult> getUncancelledTransactionWithAccount(){
        List<TransactionResult> transactionList = transactionRepository.getUncancelledTransactionWithAccount();

        return transactionList;
    }

    public List<TransactionDTO> getHighestAmtTransaction() {
        List<TransactionResult> transactionList = getUncancelledTransactionWithAccount();
        List<TransactionDTO> transactionDTOResult = transactionDTO.transactionToDtoList(transactionList);

        List<TransactionDTO> result = transactionDTOResult.stream()
                .collect(Collectors.groupingBy(TransactionDTO::getYear,
                        Collectors.maxBy(Comparator.comparing(TransactionDTO::getAmount))))
                .values().stream()
                .map(Optional::get)
                .collect(Collectors.toList());

        return result;

    }


    public List<MissingAccountDTO>  getMissingAccountInfo() {
        List<TransactionResult> transactionList = getUncancelledTransactionWithAccount();

        List<String> accountNoList = accountService.getAccountNo();

        List<MissingAccountDTO> missingAccountDTOList = missingAccountDTO.missingAccountDTOList(transactionList);

        Map<String, List<String>> missingAccountsPerYear = missingAccountDTOList.stream()
                .collect(Collectors.groupingBy(
                        MissingAccountDTO::getYear,
                        Collectors.mapping(
                                MissingAccountDTO::getAccountNo,
                                Collectors.toList())));

        List<MissingAccountDTO> missingAccountsDTOList = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : missingAccountsPerYear.entrySet()){
            List<String> missingAccountNoList = accountNoList.stream()
                    .filter(e -> ! entry.getValue().contains(e))
                    .collect(Collectors.toList());

            for (String missingAccountNo : missingAccountNoList){
                AccountResult missingAccount = accountService.getAccountNameByAccountNo(missingAccountNo);
                String name = missingAccount.getAccountName();

                MissingAccountDTO missingTransactionDTO = new MissingAccountDTO();
                missingTransactionDTO.setName(name);
                missingTransactionDTO.setAccountNo(missingAccountNo);
                missingTransactionDTO.setYear(entry.getKey());

                missingAccountsDTOList.add(missingTransactionDTO);
            }
        }

        return missingAccountsDTOList;
    }


    public List<TransactionByBranchDTO> getTransactionTotalAmtByBranch(){
        List<TransactionResult> transactionList = getUncancelledTransaction();
        List<TransactionByBranchDTO> transactionByBranchDTOList = new ArrayList<>();

        for (TransactionResult transaction : transactionList) {
            TransactionByBranchDTO transactionByBranchDTO = new TransactionByBranchDTO();

            List<BranchDataDTO> branchDataList = new ArrayList<>();

            String accountNo = transaction.getAccountNo();
            AccountResult accountResult = accountService.getAccountNameByAccountNo(accountNo);

            String branchCode = accountResult.getBranchCode();
            BranchResult branchResult = branchService.getBranchByBranchCode(branchCode);
            String branchName = branchResult.getBranchName();

            BranchDataDTO branchDataDTO = new BranchDataDTO();

            branchDataDTO.setBranchCode(branchCode);
            branchDataDTO.setBranchName(branchName);
            branchDataDTO.setSumAmt(transaction.getAmount() - transaction.getFee());

            branchDataList.add(branchDataDTO);

            transactionByBranchDTO.setYear(transaction.getTransactionDate().substring(0, 4));
            transactionByBranchDTO.setDataList(branchDataList);

            transactionByBranchDTOList.add(transactionByBranchDTO);

            }

        Map<String, List<TransactionByBranchDTO>>  transactionsByYear =
                transactionByBranchDTOList.stream()
                        .collect(Collectors.groupingBy(transaction -> transaction.getYear()));

        List<TransactionByBranchDTO> transactionTotalAmtByBranch = new ArrayList<>();
        transactionsByYear.keySet().forEach(year -> {
            List<TransactionByBranchDTO> transactionByYearList = transactionsByYear.get(year);

            Map<String, BranchDataDTO>
                    dataListPerBranch = transactionByYearList.stream().flatMap(branchData -> branchData.getDataList().stream()).collect(Collectors.toMap(BranchDataDTO::getBranchCode, Function.identity(),
                    (branchData1, branchData2) -> {

                        branchData1.setSumAmt(branchData1.getSumAmt() + branchData2.getSumAmt());
                        return branchData1;
                    }));
            TransactionByBranchDTO transactionByBranch = new TransactionByBranchDTO();
            transactionByBranch.setYear(year);
            transactionByBranch.setDataList(new ArrayList<>(dataListPerBranch.values()));
            transactionTotalAmtByBranch.add(transactionByBranch);
        });

        return transactionTotalAmtByBranch;
    }



    public BranchDataDTO getTransactionByBranch(@RequestParam(value = "branchName") String branchName){

        List<TransactionResult> transactionList = getUncancelledTransaction();
        List<BranchDataDTO> branchDataDTOList = new ArrayList<>();

//        List<TransactionResult> transactionList = transactionRepository.getUncancelledTransactionWithAccountAndBranch();
//        List<BranchDataDTO> branchDataDTOList = branchDataDTO.BranchDTOtoList(transactionList);


        if (branchName.equals("분당점")){
            throw new RequestException("br code not found error");
        }

        for (TransactionResult transaction : transactionList) {
            BranchDataDTO branchDataDTO = new BranchDataDTO();
            String accountNo = transaction.getAccountNo();
            Integer sumAmt = transaction.getAmount() - transaction.getFee();

            AccountResult accountResult = accountService.getAccountNameByAccountNo(accountNo);

            String brCode = accountResult.getBranchCode();

            BranchResult branchResult = branchService.getBranchByBranchCode(brCode);

            String brName = branchResult.getBranchName();

            branchDataDTO.setBranchCode(brCode);
            branchDataDTO.setBranchName(brName);
            branchDataDTO.setSumAmt(sumAmt);

            branchDataDTOList.add(branchDataDTO);
        }

        Map<String, List<BranchDataDTO>> branchDataByBranchCode =
                branchDataDTOList.stream()
                        .collect(Collectors.groupingBy(branchData -> branchData.getBranchCode()));

        List<BranchDataDTO> sortedBranchDataByBranchCode = new ArrayList<>();

        for (Map.Entry<String, List<BranchDataDTO>> entry : branchDataByBranchCode.entrySet()) {
            for (BranchDataDTO branchData : entry.getValue()) {
                BranchDataDTO newBranchData = new BranchDataDTO();
                if (branchData.getBranchCode().equals("B")) {
                    branchData.setBranchName("판교점");
                    branchData.setBranchCode("A");
                    branchData.setSumAmt(branchData.getSumAmt());
                }
                newBranchData.setBranchCode(branchData.getBranchCode());
                newBranchData.setBranchName(branchData.getBranchName());
                newBranchData.setSumAmt(branchData.getSumAmt());
                sortedBranchDataByBranchCode.add(newBranchData);
            }
        }

        Map<String, List<BranchDataDTO>> newBranchDataByBranchCode =
                sortedBranchDataByBranchCode.stream()
                        .collect(Collectors.groupingBy(branchData -> branchData.getBranchCode()));


        Collection<BranchDataDTO> branchDataResult =
                newBranchDataByBranchCode.values().stream()
                        .flatMap(branchData -> branchData.stream())
                        .collect(Collectors.toMap(branchData -> branchData.getBranchCode(), Function.identity(), (branchData1, branchData2) -> {
                            branchData1.setSumAmt(branchData1.getSumAmt() + branchData2.getSumAmt());
                            return branchData1;
                        })).values();


        for (BranchDataDTO branchDataDTO : branchDataResult) {
            if (branchDataDTO.getBranchName().equals(branchName)){
                return branchDataDTO;
            }
        }

        return new BranchDataDTO();
    }
}