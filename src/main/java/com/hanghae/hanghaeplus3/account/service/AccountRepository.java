package com.hanghae.hanghaeplus3.account.service;

import com.hanghae.hanghaeplus3.account.service.domain.Account;

import java.util.List;

public interface AccountRepository {
    List<Account> findAccountsOf(Long memberId);
    Account findAccountOf(Long accountId);
    void save(Account account);
}
