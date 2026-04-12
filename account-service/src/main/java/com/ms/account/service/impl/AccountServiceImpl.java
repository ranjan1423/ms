package com.ms.account.service.impl;

import com.ms.account.dto.request.AccountRequestDTO;
import com.ms.account.dto.request.CustomerRequestDTO;
import com.ms.account.entity.Account;
import com.ms.account.entity.Customer;
import com.ms.account.exception.CustomerExistsException;
import com.ms.account.exception.ResourceNotFoundException;
import com.ms.account.mapper.AccountsMapper;
import com.ms.account.mapper.CustomerMapper;
import com.ms.account.repository.AccountRepository;
import com.ms.account.repository.CustomerRepository;
import com.ms.account.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void createAccount(CustomerRequestDTO customerRequestDTO) {
        Optional<Customer> customerOptional = customerRepository.findByMobileNumber(customerRequestDTO.getMobileNumber());
        if (customerOptional.isPresent()) {
            throw new CustomerExistsException("Customer already exists with mobile number " + customerRequestDTO.getMobileNumber());
        }

        Customer customer = CustomerMapper.maptoCustomer(customerRequestDTO, new Customer());
        customer.setCreatedBy("Anonymous");
        customer.setCreatedAt(LocalDateTime.now());
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));
    }

    private Account createNewAccount(Customer customer) {
        Account account = new Account();
        account.setCustomerId(customer.getCustomerId());
        long random = 1000000000L + new Random().nextInt(90000000);
        account.setAccountNumber(random);
        account.setAccountType("Savings");
        account.setCreatedBy("Anonymous");
        account.setCreatedAt(LocalDateTime.now());
        account.setBranchAddress("Malviya Nagar, New Delhi");
        return account;
    }

    @Override
    public CustomerRequestDTO fetchAccountDetails(String mobileNumber) {

        // does customer exist
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );

        // does account exist
        Account account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerRequestDTO customerRequestDTO = CustomerMapper.maptoCustomerDTO(customer, new CustomerRequestDTO());
        customerRequestDTO.setAccountRequestDTO(AccountsMapper.maptoAccountDTO(account, new AccountRequestDTO()));
        return customerRequestDTO;
    }

    @Override
    public boolean updateAccountDetails(CustomerRequestDTO customerRequestDTO) {
        boolean result = false;
        AccountRequestDTO accountRequestDTO = customerRequestDTO.getAccountRequestDTO();

        if (accountRequestDTO != null) {
            Account account = accountRepository.findById(accountRequestDTO.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "account number", accountRequestDTO.getAccountNumber().toString())
            );
            AccountsMapper.maptoAccount(accountRequestDTO, account);
            account = accountRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.maptoCustomer(customerRequestDTO, customer);
            customerRepository.save(customer);
            result = true;
        }
        return result;
    }

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer custmer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        accountRepository.deleteByCustomerId(custmer.getCustomerId());
        customerRepository.deleteById(custmer.getCustomerId());
        return true;
    }
}
