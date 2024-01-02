package com.hanghae.hanghaeplus3.account.service;

import com.hanghae.hanghaeplus3.account.service.component.MemberReader;
import com.hanghae.hanghaeplus3.account.service.domain.Account;
import com.hanghae.hanghaeplus3.member.service.domain.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AccountService {
    private final AccountRepository accountRepository;

    private final MemberReader memberReader;


    public Account findAccount(Long accountId) {
        return accountRepository.findAccountOf(accountId);
    }

    public List<Account> findBalanceOf(Long memberId) {
        Member member = memberReader.getMember(memberId);  // 이미 회원이 있는지 확인하고 들어온다는 가정하에 필요 없는 부분 아닐까?
        return accountRepository.findAccountsOf(member.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    public Long chargeBalance(Long memberId, Long accountId, Long amount) {
        Account account = findAccount(accountId);
        account.checkOwner(memberId);
        account.charge(amount);

        accountRepository.save(account);
        return account.getId();
    }

    @Transactional(rollbackFor = Exception.class)
    public Long deductBalance(Long memberId, Long accountId, Long amount) {
        Account account = findAccount(accountId);
        account.checkOwner(memberId);
        account.deduct(amount);

        accountRepository.save(account);
        return account.getId();
    }
}
