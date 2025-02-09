package com.gymmanagement.gym_subscription.repository;
import com.gymmanagement.gym_subscription.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

// MemberRepository.java
public interface MemberRepository extends JpaRepository<Member, Long> {
}