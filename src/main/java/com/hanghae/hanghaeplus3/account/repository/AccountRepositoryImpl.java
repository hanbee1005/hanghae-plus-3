package com.hanghae.hanghaeplus3.account.repository;

import com.hanghae.hanghaeplus3.account.service.AccountRepository;
import com.hanghae.hanghaeplus3.account.repository.entity.AccountEntity;
import com.hanghae.hanghaeplus3.account.service.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    private final AccountJpaRepository repository;

    @Override
    public List<Account> findAccountsOf(Long memberId) {
        return repository.findByMemberId(memberId).stream()
                .map(AccountEntity::toDomain)
                .toList();
    }

    @Override
    public Account findAccountOf(Long accountId) {
        return repository.findById(accountId)
                .orElseThrow(IllegalArgumentException::new)
                .toDomain();
    }

    @Override
    public void updateAccount(Account account) {

    }
}
