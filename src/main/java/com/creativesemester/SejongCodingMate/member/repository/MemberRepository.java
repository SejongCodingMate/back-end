package com.creativesemester.SejongCodingMate.member.repository;


import com.creativesemester.SejongCodingMate.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository <Member,Long> {

    Optional<Member> findByStudentId(String studentId);
}
