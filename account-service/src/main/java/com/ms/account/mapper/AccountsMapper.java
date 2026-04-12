package com.ms.account.mapper;

import com.ms.account.dto.request.AccountRequestDTO;
import com.ms.account.entity.Account;

public class AccountsMapper {

    public static Account maptoAccount(AccountRequestDTO accountRequestDTO, Account account) {
        account.setAccountNumber(accountRequestDTO.getAccountNumber());
        account.setAccountType(accountRequestDTO.getAccountType());
        account.setBranchAddress(accountRequestDTO.getBranchAddress());
        return account;
    }

    public static AccountRequestDTO maptoAccountDTO(Account account, AccountRequestDTO accountRequestDTO) {
        accountRequestDTO.setAccountNumber(account.getAccountNumber());
        accountRequestDTO.setAccountType(account.getAccountType());
        accountRequestDTO.setBranchAddress(account.getBranchAddress());
        return accountRequestDTO;
    }
}
