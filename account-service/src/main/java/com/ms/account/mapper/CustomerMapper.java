package com.ms.account.mapper;

import com.ms.account.dto.CustomerDetailsDTO;
import com.ms.account.dto.request.CustomerRequestDTO;
import com.ms.account.entity.Customer;

public class CustomerMapper {

    public static Customer maptoCustomer(CustomerRequestDTO customerRequestDTO, Customer customer) {
        customer.setName(customerRequestDTO.getName());
        customer.setEmail(customerRequestDTO.getEmail());
        customer.setMobileNumber(customerRequestDTO.getMobileNumber());
        return customer;
    }

    public static CustomerRequestDTO maptoCustomerDTO(Customer customer, CustomerRequestDTO customerRequestDTO) {
        customerRequestDTO.setName(customer.getName());
        customerRequestDTO.setEmail(customer.getEmail());
        customerRequestDTO.setMobileNumber(customer.getMobileNumber());
        return customerRequestDTO;
    }

    public static CustomerDetailsDTO maptoCustomerDetailsDTO(Customer customer, CustomerDetailsDTO customerDetailsDTO) {
        customerDetailsDTO.setName(customer.getName());
        customerDetailsDTO.setEmail(customer.getEmail());
        customerDetailsDTO.setMobileNumber(customer.getMobileNumber());
        return customerDetailsDTO;
    }
}
