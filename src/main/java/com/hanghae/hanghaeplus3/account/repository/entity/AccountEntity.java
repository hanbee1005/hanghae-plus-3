package com.hanghae.hanghaeplus3.account.repository.entity;

import com.hanghae.hanghaeplus3.common.BaseTimeEntity;
import com.hanghae.hanghaeplus3.account.service.domain.Account;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "account")
public class AccountEntity extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long memberId;

    @Column
    private Long balance;

    @Column
    private Long creator;

    @Column
    private Long lastModifier;

    public static AccountEntity create(Account account) {
        return AccountEntity.builder()
                .memberId(account.getMemberId())
                .balance(account.getBalance())
                .creator(account.getMemberId())
                .build();
    }

    public void updateBalance(Long balance) {
        this.balance += balance;
    }

    public Account toAccount() {
        return Account.builder()
                .id(id)
                .memberId(memberId)
                .balance(balance)
                .createdAt(super.getCreatedAt())
                .build();
    }
}
