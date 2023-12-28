package com.hanghae.hanghaeplus3.account.service;

import com.hanghae.hanghaeplus3.account.service.component.MemberReader;
import com.hanghae.hanghaeplus3.account.service.domain.Account;
import com.hanghae.hanghaeplus3.common.exception.CustomException;
import com.hanghae.hanghaeplus3.member.service.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;

@ExtendWith({MockitoExtension.class})
class AccountServiceTest {
    @InjectMocks
    private AccountService accountService;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private MemberReader memberReader;

    @Test
    @DisplayName("계좌 잔고 조회")
    public void findBalanceOf() {
        // given
        Long memberId = 1L;
        Member member = Member.builder().id(memberId).name("testerA").build();

        given(memberReader.getMember(anyLong())).willReturn(member);
        given(accountRepository.findAccountsOf(anyLong())).willReturn(getMockAccounts());

        // when
        List<Account> accounts = accountService.findBalanceOf(memberId);

        // then
        assertThat(accounts).isNotEmpty();
        assertThat(accounts.size()).isGreaterThanOrEqualTo(1);
        assertThat(accounts.stream().map(Account::getMemberId).toList()).containsOnly(memberId);
    }

    @Test
    @DisplayName("계좌 잔고 충전")
    public void chargeBalance() {
        // given
        Long amount = 500L;

        Account account = getMockAccount();
        Long accountId = account.getId();
        Long memberId = account.getMemberId();
        Long balance = account.getBalance();

        given(accountRepository.findAccountOf(anyLong())).willReturn(account);

        // when
        Long updatedId = accountService.chargeBalance(memberId, accountId, amount);

        // then
        Account updatedAccount = accountService.findAccount(updatedId);

        assertThat(updatedAccount.getId()).isEqualTo(accountId);
        assertThat(updatedAccount.getMemberId()).isEqualTo(memberId);
        assertThat(updatedAccount.getBalance()).isEqualTo(balance + amount);
    }

    @Test
    @DisplayName("계좌 잔고 충전 실패 - 본인의 계좌 잔고만 충전이 가능합니다.")
    public void chargeBalanceFailByMember() {
        // given
        long amount = 100L;

        Account account = getMockAccount();
        Long accountId = account.getId();
        Long memberId = -1L;

        given(accountRepository.findAccountOf(anyLong())).willReturn(account);

        // when
        // then
        assertThrows(CustomException.class, () -> accountService.chargeBalance(memberId, accountId, amount));
    }

    @Test
    @DisplayName("계좌 잔고 충전 실패 - 충전 요청 금액이 음수이면 안됩니다.")
    public void chargeBalanceFailByAmount() {
        // given
        long amount = -1L;

        Account account = getMockAccount();
        Long accountId = account.getId();
        Long memberId = account.getMemberId();

        given(accountRepository.findAccountOf(anyLong())).willReturn(account);

        // when
        // then
        assertThrows(CustomException.class, () -> accountService.chargeBalance(memberId, accountId, amount));
    }

    private Account getMockAccount() {
        return getMockAccounts().stream()
                .findAny()
                .orElseGet(() -> Account.builder().id(1L).memberId(1L).balance(10000L).createdAt(LocalDateTime.now()).build());

    }

    private static List<Account> getMockAccounts() {
        return List.of(
                Account.builder().id(1L).memberId(1L).balance(10000L).createdAt(LocalDateTime.now()).build(),
                Account.builder().id(2L).memberId(1L).balance(15000L).createdAt(LocalDateTime.now()).build(),
                Account.builder().id(3L).memberId(1L).balance(20000L).createdAt(LocalDateTime.now()).build()
        );
    }
}