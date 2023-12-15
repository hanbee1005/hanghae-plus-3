package com.hanghae.hanghaeplus3.member.repository;

import com.hanghae.hanghaeplus3.member.repository.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
}
