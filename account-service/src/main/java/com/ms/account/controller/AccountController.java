package com.ms.account.controller;

import com.ms.account.dto.request.CustomerRequestDTO;
import com.ms.account.dto.response.ResponseDTO;
import com.ms.account.service.IAccountService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
@Validated
public class AccountController {

    private final IAccountService iAccountService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createAccount(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
        iAccountService.createAccount(customerRequestDTO);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDTO(HttpStatus.CREATED.toString(), "Account created."));
    }

    @GetMapping(path = "/fetch")
    public ResponseEntity<CustomerRequestDTO> fetchAccountDetails(@RequestParam
                                                                  @NotEmpty(message = "Mobile can't be empty.")
                                                                  @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be 10 digit.")
                                                                  String mobileNumber) {
        CustomerRequestDTO customerRequestDTO = iAccountService.fetchAccountDetails(mobileNumber);
        return ResponseEntity.status(HttpStatus.OK)
                .body(customerRequestDTO);
    }

    @PutMapping(path = "/update")
    public ResponseEntity<ResponseDTO> updateAccountDetails(@Valid @RequestBody CustomerRequestDTO customerRequestDTO) {
        boolean isUpdated = iAccountService.updateAccountDetails(customerRequestDTO);
        if (isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(HttpStatus.OK.toString(), "Request processed successfully."));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Request processing failed."));
        }
    }

    /*
        @PatchMapping
        public String patchAccount(@RequestBody Account account) {
            return "Account Controller : patch method called";
        }
    */
    @DeleteMapping(path = "/delete")
    public ResponseEntity<ResponseDTO> deleteAccount(@RequestParam
                                                         @NotEmpty(message = "Mobile can't be empty.")
                                                         @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be 10 digit.")
                                                         String mobileNumber) {
        boolean isDeleted = iAccountService.deleteAccount(mobileNumber);
        if (isDeleted) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDTO(HttpStatus.OK.toString(), "Record deleted successfully."));
        } else {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Delete request failed."));
        }
    }
}