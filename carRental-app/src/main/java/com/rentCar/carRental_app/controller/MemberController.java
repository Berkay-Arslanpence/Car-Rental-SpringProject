package com.rentCar.carRental_app.controller;

import com.rentCar.carRental_app.dto.MemberDTO;
import com.rentCar.carRental_app.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable Long id) {
        MemberDTO member = memberService.getMemberById(id);
        if (member == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(member);
    }

    @PostMapping
    public ResponseEntity<MemberDTO> createOrUpdateMember(@RequestBody MemberDTO memberDTO) {
        MemberDTO updatedMember = memberService.saveMember(memberDTO);
        return ResponseEntity.ok(updatedMember);
    }
}
