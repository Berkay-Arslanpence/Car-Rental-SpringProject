package com.rentCar.carRental_app.service;

import com.rentCar.carRental_app.dto.MemberDTO;
import com.rentCar.carRental_app.mapper.MemberMapper;
import com.rentCar.carRental_app.model.Member;
import com.rentCar.carRental_app.repo.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public MemberDTO getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Member not found with id " + id));
        return memberMapper.MemberToMemberDTO(member);
    }

    public MemberDTO getMemberByEmail(String email) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new IllegalArgumentException("Member not found with email " + email);
        }
        return memberMapper.MemberToMemberDTO(member);
    }
}
