package com.ms.account.service.impl;

import com.ms.account.dto.CardDTO;
import com.ms.account.dto.CustomerDetailsDTO;
import com.ms.account.dto.LoansDto;
import com.ms.account.dto.request.AccountRequestDTO;
import com.ms.account.entity.Account;
import com.ms.account.entity.Customer;
import com.ms.account.exception.ResourceNotFoundException;
import com.ms.account.mapper.AccountsMapper;
import com.ms.account.mapper.CustomerMapper;
import com.ms.account.repository.AccountRepository;
import com.ms.account.repository.CustomerRepository;
import com.ms.account.service.ICustomerService;
import com.ms.account.service.client.CardsFeignClient;
import com.ms.account.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final CardsFeignClient cardsFeignClient;
    private final LoansFeignClient loansFeignClient;

    @Override
    public CustomerDetailsDTO fetchCustomerDetails(String mobileNumber, String correlationId) {

        // does customer exist
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        // does account exist
        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDTO customerDetailsDTO = CustomerMapper.maptoCustomerDetailsDTO(customer, new CustomerDetailsDTO());
        customerDetailsDTO.setAccountDTO(AccountsMapper.maptoAccountDTO(account, new AccountRequestDTO()));

        ResponseEntity <CardDTO> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
        customerDetailsDTO.setCardDTO(cardsDtoResponseEntity.getBody());

        ResponseEntity <LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
        customerDetailsDTO.setLoansDto(loansDtoResponseEntity.getBody());

        return customerDetailsDTO;
    }
}
