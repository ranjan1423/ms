package com.ms.account.service;

import com.ms.account.dto.CustomerDetailsDTO;

public interface ICustomerService {

    CustomerDetailsDTO fetchCustomerDetails(String mobileNumber, String correlationId);
}
