package com.gymmanagement.gym_subscription.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.gymmanagement.gym_subscription.model.Member;
import com.gymmanagement.gym_subscription.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }
    
    public Member saveMember(Member member) {
        return memberRepository.save(member);
    }
    
    public void deleteMember(Long id) {
        memberRepository.deleteById(id);
    }
    public Member getMemberById(Long id) {
        return memberRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Membre introuvable"));
    }
    
}
