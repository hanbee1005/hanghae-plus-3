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
@Transactional(rollbackFor = Exception.class)
public class AccountService {
    private final AccountRepository accountRepository;

    private final MemberReader memberReader;

    @Transactional(readOnly = true)
    public Account findAccount(Long accountId) {
        return accountRepository.findAccountOf(accountId);
    }

    @Transactional(readOnly = true)
    public List<Account> findBalanceOf(Long memberId) {
        Member member = memberReader.getMember(memberId);// MemberRepository를 직접 주입받는 대신 이 형태를 사용하는게 괜찮을지...?
        return accountRepository.findAccountsOf(member.getId());
    }

    public Long chargeBalance(Long memberId, Long accountId, Long amount) {
        Account account = findAccount(accountId);
        account.checkOwner(memberId);
        account.charge(amount);

        accountRepository.update(account);

        return account.getId();
    }
}
