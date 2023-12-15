package com.hanghae.hanghaeplus3.member.service;

import com.hanghae.hanghaeplus3.member.repository.AccountRepository;
import com.hanghae.hanghaeplus3.member.repository.entity.AccountEntity;
import com.hanghae.hanghaeplus3.member.service.domain.Account;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
public class AccountService {
    private final AccountRepository accountRepository;

    @Transactional(readOnly = true)
    public List<Account> findBalanceOf(Long id) {
        // TODO check exist member
        return accountRepository.findAccountByMemberId(id).stream().map(AccountEntity::toDomain).toList();
    }

    public Account chargeBalance(Long memberId, Long accountId, Long amount) {
        // TODO check member's account
        AccountEntity account = accountRepository.findById(accountId)
                .orElseThrow(IllegalArgumentException::new);
        account.chargeBalance(amount);

        return Account.builder()
                .id(account.getId())
                .memberId(account.getMemberId())
                .balance(account.getBalance())
                .build();
    }
}
