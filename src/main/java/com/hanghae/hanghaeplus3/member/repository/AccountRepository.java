package com.hanghae.hanghaeplus3.member.repository;

import com.hanghae.hanghaeplus3.member.repository.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
    List<AccountEntity> findAccountByMemberId(Long memberId);
}
