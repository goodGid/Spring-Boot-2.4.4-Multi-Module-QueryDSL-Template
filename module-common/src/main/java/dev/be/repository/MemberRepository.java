package dev.be.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.be.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}