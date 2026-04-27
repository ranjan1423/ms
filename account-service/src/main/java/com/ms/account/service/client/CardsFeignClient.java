package com.ms.account.service.client;

import com.ms.account.dto.CardDTO;
import jakarta.validation.constraints.Pattern;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

// FeignClient#3 Feign Client interface declaration
// keep service name as it is.
@FeignClient("card-service")
public interface CardsFeignClient {

    // method name could be anything.
    // return type & input to method should be same.
    // need same HTTP method : GetMapping-- (url, consumes)
    @GetMapping(value = "/api/fetch", consumes = "application/json")
    public ResponseEntity<CardDTO> fetchCardDetails(@RequestHeader ("ms-correlation-id") String correlationId, @RequestParam String mobileNumber);
}
