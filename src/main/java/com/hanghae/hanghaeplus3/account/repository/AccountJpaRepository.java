package com.hanghae.hanghaeplus3.account.repository;

import com.hanghae.hanghaeplus3.account.repository.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findByMemberId(Long memberId);
}
