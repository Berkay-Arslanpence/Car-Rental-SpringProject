package com.rentCar.carRental_app.service;

import com.rentCar.carRental_app.dto.MemberDTO;
import com.rentCar.carRental_app.mapper.MemberMapper;
import com.rentCar.carRental_app.model.Member;
import com.rentCar.carRental_app.repo.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    public MemberDTO getMemberById(Long id) {
        Member member = memberRepository.findMemById(id);
        return member != null ? MemberMapper.MemberToMemberDTO(member) : null;
    }

    public MemberDTO saveMember(MemberDTO memberDTO) {
        Member member = MemberMapper.MemberDTOToMember(memberDTO);
        member = memberRepository.save(member);
        return MemberMapper.MemberToMemberDTO(member);
    }
}
