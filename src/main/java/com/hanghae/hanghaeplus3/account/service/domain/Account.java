package com.hanghae.hanghaeplus3.account.service.domain;

import com.hanghae.hanghaeplus3.common.exception.CustomException;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.hanghae.hanghaeplus3.common.constant.CustomExceptionStatus.*;

@Getter
public class Account {
    private Long id;
    private Long memberId;
    private Long balance;
    private LocalDateTime createdAt;

    @Builder
    public Account(Long id, Long memberId, Long balance, LocalDateTime createdAt) {
        this.id = id;
        this.memberId = memberId;
        this.balance = balance;
        this.createdAt = createdAt;
    }

    public void checkOwner(Long memberId) {
        if (!Objects.equals(memberId, this.memberId)) {
            throw new CustomException(ACCOUNT_NOT_MATCH_OWNER);
        }
    }

    public void charge(long amount) {
        isPositive(amount);
        balance += amount;
    }

    public void deduct(long amount) {
        canDeduct(amount);
        balance -= amount;
    }

    private void isPositive(long amount) {
        if (amount < 0) {
            throw new CustomException(ACCOUNT_NOT_CHARGE_MINUS);
        }
    }

    private void canDeduct(long amount) {
        if (amount < 0 || balance - amount < 0) {
            throw new CustomException(ACCOUNT_NOT_ENOUGH_BALANCE);
        }
    }
}
