package com.hanghae.hanghaeplus3.member.service.component;

import com.hanghae.hanghaeplus3.member.service.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountManager {
    private final AccountRepository accountRepository;


}
