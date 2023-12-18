package com.hanghae.hanghaeplus3.account.service;

import com.hanghae.hanghaeplus3.account.service.domain.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AccountServiceTest {
    private final AccountService accountService = new AccountService(new FakeAccountRepository());

    @Test
    @DisplayName("계좌 잔고 조회")
    public void findBalanceOf() {
        // given
        Long memberId = 1L;

        // when
        List<Account> accounts = accountService.findBalanceOf(memberId);

        // then
        assertThat(accounts).isNotEmpty();
        assertThat(accounts.size()).isGreaterThanOrEqualTo(1);
    }

    @Test
    @DisplayName("계좌 잔고 충전")
    public void chargeBalance() {
        // given
        Long accountId = 1L;
        Long amount = 500L;

        Account account = accountService.findAccount(accountId);

        // when
        Long updatedId = accountService.chargeBalance(account.getMemberId(), account.getId(), amount);

        // then
        Account updatedAccount = accountService.findAccount(updatedId);

        assertThat(updatedAccount.getId()).isEqualTo(accountId);
        assertThat(updatedAccount.getMemberId()).isEqualTo(account.getMemberId());
        assertThat(updatedAccount.getBalance()).isEqualTo(account.getBalance() + amount);
    }
}