package com.hanghae.hanghaeplus3.account.repository.entity;

import com.hanghae.hanghaeplus3.account.service.domain.Account;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    Long memberId;

    @Column
    Long balance;

    @CreatedDate
    LocalDateTime createdAt;

    public Account toDomain() {
        return Account.builder()
                .id(id)
                .memberId(memberId)
                .balance(balance)
                .createdAt(createdAt)
                .build();
    }
}
