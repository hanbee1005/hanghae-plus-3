package com.hanghae.hanghaeplus3.member.service;

import com.hanghae.hanghaeplus3.member.service.domain.Account;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    private MemberService memberService;

    MemberServiceTest() {
        memberService = new MemberService(new FakeAccountRepository());
    }

    @Test
    @DisplayName("계좌 잔고 조회")
    public void findBalanceOf() {
        // given
        Long memberId = 1L;

        // when
        List<Account> accounts = memberService.findBalanceOf(memberId);

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

        Account account = memberService.findAccount(accountId);

        // when
        Long updatedId = memberService.chargeBalance(account.getMemberId(), account.getId(), amount);

        // then
        Account updatedAccount = memberService.findAccount(updatedId);

        assertThat(updatedAccount.getId()).isEqualTo(accountId);
        assertThat(updatedAccount.getMemberId()).isEqualTo(account.getMemberId());
        assertThat(updatedAccount.getBalance()).isEqualTo(account.getBalance() + amount);
    }
}