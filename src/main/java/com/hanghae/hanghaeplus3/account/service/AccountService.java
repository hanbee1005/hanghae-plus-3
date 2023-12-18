package com.hanghae.hanghaeplus3.account.service;

import com.hanghae.hanghaeplus3.account.service.domain.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account findAccount(Long accountId) {
        return accountRepository.findAccountOf(accountId);
    }

    public List<Account> findBalanceOf(Long memberId) {
        return accountRepository.findAccountsOf(memberId);
    }

    public Long chargeBalance(Long memberId, Long accountId, Long amount) {
        Account account = findAccount(accountId);
        account.checkOwner(memberId);
        account.charge(amount);

        accountRepository.save(account);

        return account.getId();
    }
}
