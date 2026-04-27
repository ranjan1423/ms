package com.ms.account.controller;

import com.ms.account.dto.CustomerDetailsDTO;
import com.ms.account.dto.request.CustomerRequestDTO;
import com.ms.account.dto.response.ResponseDTO;
import com.ms.account.service.IAccountService;
import com.ms.account.service.ICustomerService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    private final ICustomerService iCustomerService;

    @GetMapping(path = "/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDTO> fetchAccountDetails(@RequestHeader("ms-correlation-id") String correlationId,
                                                                  @RequestParam
                                                                  @NotEmpty(message = "Mobile can't be empty.")
                                                                  @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be 10 digit.")
                                                                  String mobileNumber) {
        logger.debug("Fetching account details from customer with mobile number {}", mobileNumber);
        logger.debug("ms-correlationId found {}", correlationId);

        CustomerDetailsDTO customerDetailsDTO = iCustomerService.fetchCustomerDetails(mobileNumber, correlationId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerDetailsDTO);
    }
}