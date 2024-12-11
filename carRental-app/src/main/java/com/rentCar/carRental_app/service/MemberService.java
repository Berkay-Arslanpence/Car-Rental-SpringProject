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
    MemberRepository memberRepository;

    public MemberDTO getMemberById(Long id) {
        return MemberMapper.MemberToMemberDTO(memberRepository.findMemById(id));
   }


    public MemberDTO saveMember(MemberDTO memberDTO) {
        Member member = MemberMapper.MemberDTOToMember(memberDTO);
        memberRepository.save(member);
        return memberDTO;
    }
}
