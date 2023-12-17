package com.hanghae.hanghaeplus3.member.repository;

import com.hanghae.hanghaeplus3.member.repository.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountJpaRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findByMemberId(Long memberId);
}
