package com.ms.account.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CustomerRequestDTO {

    @NotEmpty (message = "Name can't be empty.")
    @Size(min = 5, max = 30, message = "Name should be min-5 and max-30 character.")
    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Name must contain only letters and spaces.")
    private String name;

    @NotEmpty (message = "Email can't be empty.")
    @Email (message = "Email should be proper value.")
    private String email;

    @NotEmpty (message = "Mobile can't be empty.")
    @Pattern(regexp = "^[0-9]{10}$", message = "Mobile number should be 10 digit.")
    private String mobileNumber;

    private AccountRequestDTO accountRequestDTO;
}