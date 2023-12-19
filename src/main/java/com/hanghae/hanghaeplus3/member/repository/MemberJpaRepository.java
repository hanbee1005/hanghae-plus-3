package com.hanghae.hanghaeplus3.member.repository;

import com.hanghae.hanghaeplus3.member.repository.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberJpaRepository extends JpaRepository<MemberEntity, Long> {
}
