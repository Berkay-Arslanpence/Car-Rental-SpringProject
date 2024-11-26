package com.rentCar.carRental_app.mapper;

import com.rentCar.carRental_app.model.Member;
import com.rentCar.carRental_app.dto.MemberDTO;

public class MemberMapper {
    public static Member MemberDTOToMember(MemberDTO memberDTO) {
        Member member = new Member();
        member.setName(memberDTO.getName());
        member.setAddress(memberDTO.getAddress());
        member.setEmail(memberDTO.getEmail());
        member.setPhone(memberDTO.getPhone());
        member.setDrivingLicenseNumber(memberDTO.getDrivingLicenseNumber());
        return member;
    }
    public static MemberDTO MemberToMemberDTO(Member member) {
        return new MemberDTO(member.getName(), member.getAddress(), member.getEmail(), member.getPhone(), member.getDrivingLicenseNumber());
    }
}
