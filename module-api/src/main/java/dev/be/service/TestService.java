package dev.be.service;

import static dev.be.domain.QMember.member;

import java.util.List;

import org.springframework.stereotype.Service;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import dev.be.domain.Member;
import dev.be.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {

    private final MemberRepository memberRepository;
    private final JPAQueryFactory jpaQueryFactory;

    public String checkDB() {

        BooleanBuilder builder = new BooleanBuilder();
        builder.and(member.id.eq(1L));
        builder.or(member.id.eq(2L));

        // use QueryDSL
        List<Member> memberList = jpaQueryFactory.selectFrom(member)
                                                 .where(builder)
                                                 .fetch();

        log.info("memberList.size() : {}", memberList.size());

        // use CrudRepository interface
        memberRepository.save(Member.builder()
                                    .name("goodGid")
                                    .build());
        return memberRepository.findAll().size() + "";
    }
}