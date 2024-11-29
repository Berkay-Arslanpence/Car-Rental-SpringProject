package com.rentCar.carRental_app.repo;

import com.rentCar.carRental_app.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // Find a member by email
    Member findByEmail(String email);

    // Find a member by phone
    @Query(value="select m from Member m where m.id= :id")
    Member findMemById(Long id);
}
