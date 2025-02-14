package com.gymmanagement.gym_subscription.service.member;

import java.util.List;
import com.gymmanagement.gym_subscription.model.Member;

public interface MemberService {
    List<Member> getAllMembers();
    Member saveMember(Member member);
    void deleteMember(Long id);
    Member getMemberById(Long id);
}
