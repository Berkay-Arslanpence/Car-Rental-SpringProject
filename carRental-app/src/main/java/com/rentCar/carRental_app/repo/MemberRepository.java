package com.rentCar.carRental_app.repo;

import com.rentCar.carRental_app.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // Custom query to find a member by email
    Member findByEmail(String email);
}
