package com.gymmanagement.gym_subscription.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gymmanagement.gym_subscription.model.Member;
import com.gymmanagement.gym_subscription.service.member.MemberService;

import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public String listMembers(Model model) {
        model.addAttribute("members", memberService.getAllMembers());
        return "members/list";
    }
    
    @GetMapping("/form")
    public String showForm(Model model) {
        model.addAttribute("member", new Member());
        return "members/form";
    }
    
    @PostMapping("/save")
    public String saveMember(@ModelAttribute Member member) {
        memberService.saveMember(member);
        return "redirect:/members";
    }
    @GetMapping("/edit/{id}")
public String editMember(@PathVariable("id") Long id, Model model) {
    Member member = memberService.getMemberById(id);
    model.addAttribute("member", member);
    return "members/form"; // Réutilise le même formulaire que l'ajout
}
    
    @GetMapping("/delete/{id}")
 public String deleteMember(@PathVariable("id") Long id) {
    memberService.deleteMember(id);
    return "redirect:/members"; // Redirige vers la liste après suppression
}
}
