package com.hanghae.hanghaeplus3.account.repository.entity;

import com.hanghae.hanghaeplus3.account.service.domain.Account;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
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

    public static AccountEntity create(Account account) {
        return AccountEntity.builder()
                .memberId(account.getMemberId())
                .balance(account.getBalance())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public Account toAccount() {
        return Account.builder()
                .id(id)
                .memberId(memberId)
                .balance(balance)
                .createdAt(createdAt)
                .build();
    }
}
