package com.ms.account.entity;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account extends BaseEntity {

    @Id
    @Column(name = "account_number")
    private Long accountNumber;

    @Column (name="customer_id")
    private Long customerId;

    @Column (name="account_type")
    private String accountType;

    @Column (name="branch_address")
    private String branchAddress;

}
