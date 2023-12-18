package com.hanghae.hanghaeplus3.account.service;

import com.hanghae.hanghaeplus3.account.service.domain.Account;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class FakeAccountRepository implements AccountRepository {
    private final Map<Long, Account> store = new ConcurrentHashMap<>();

    FakeAccountRepository() {
        store.put(1L, Account.builder().id(1L).memberId(1L).balance(10000L).createdAt(LocalDateTime.now()).build());
        store.put(2L, Account.builder().id(2L).memberId(1L).balance(15000L).createdAt(LocalDateTime.now()).build());
        store.put(3L, Account.builder().id(3L).memberId(2L).balance(20000L).createdAt(LocalDateTime.now()).build());
    }

    @Override
    public List<Account> findAccountsOf(Long memberId) {
        return store.values().stream()
                .filter(account -> Objects.equals(account.getMemberId(), memberId))
                .toList();
    }

    @Override
    public Account findAccountOf(Long accountId) {
        Account account = store.get(accountId);
        return Account.builder()
                .id(account.getId())
                .memberId(account.getMemberId())
                .balance(account.getBalance())
                .createdAt(account.getCreatedAt())
                .build();
    }

    @Override
    public void save(Account account) {
        store.put(account.getId(), account);
    }
}
