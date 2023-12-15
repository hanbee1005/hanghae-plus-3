package com.hanghae.hanghaeplus3.member.repository.entity;


import com.hanghae.hanghaeplus3.member.service.domain.Account;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long memberId;

    @Column
    private Long balance;

    @Column
    private Long creator;

    @CreatedDate
    private LocalDateTime createdAt;

    public Account toDomain() {
        return Account.builder()
                .id(id)
                .memberId(memberId)
                .balance(balance)
                .build();
    }

    public void chargeBalance(Long amount) {
        this.balance += amount;
    }
}
