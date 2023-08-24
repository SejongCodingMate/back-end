package com.creativesemester.SejongCodingMate.domain.member.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(unique = true)
   private String studentId;

   private String password;
   private String major;
   private String grade;



   public static Member of(String studentId, String password, String major, String grade){
      return Member.builder()
              .studentId(studentId)
              .password(password)
              .major(major)
              .grade(grade)
              .build();
   }

   public void changePassword(String newPassword) {
      this.password = newPassword;
   }

}
