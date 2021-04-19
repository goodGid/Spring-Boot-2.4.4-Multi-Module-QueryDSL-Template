package dev.be.service;

import org.springframework.stereotype.Service;

import dev.be.domain.Member;
import dev.be.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class TestService {

    private final MemberRepository memberRepository;

    public String checkDB() {
        // use CrudRepository interface
        memberRepository.save(Member.builder()
                                    .name("goodGid")
                                    .build());
        return memberRepository.findAll().size() + "";
    }
}