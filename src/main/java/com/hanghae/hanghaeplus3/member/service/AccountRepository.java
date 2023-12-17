package com.hanghae.hanghaeplus3.member.service;

import com.hanghae.hanghaeplus3.member.service.domain.Account;

import java.util.List;

public interface AccountRepository {
    List<Account> findAccountsOf(Long memberId);
    Account findAccountOf(Long accountId);
    void updateAccount(Account account);
}
