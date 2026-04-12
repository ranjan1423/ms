package com.ms.account.repository;

import com.ms.account.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByName(String name);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByMobileNumber(String email);

    Optional<Customer> findByEmailAndMobileNumber(String email, String mobileNumber);
}
