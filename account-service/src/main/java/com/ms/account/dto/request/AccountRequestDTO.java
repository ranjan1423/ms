package com.ms.account.dto.request;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AccountRequestDTO {

    @NotEmpty(message = "Account number can't be empty.")
    @Pattern(regexp = "^[0-9]{10}$", message = "Account number must be 10 digit.")
    private Long accountNumber;

    @NotEmpty(message = "Account type can't be empty.")
    private String accountType;

    @NotEmpty(message = "Branch address can't be empty.")
    private String branchAddress;
}