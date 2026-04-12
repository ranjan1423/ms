package com.ms.account.service;

import com.ms.account.dto.request.CustomerRequestDTO;

public interface IAccountService {

    void createAccount(CustomerRequestDTO customerRequestDTO);

    CustomerRequestDTO fetchAccountDetails(String mobileNumber);

    boolean updateAccountDetails(CustomerRequestDTO customerRequestDTO);

    boolean deleteAccount(String mobileNumber);
}
