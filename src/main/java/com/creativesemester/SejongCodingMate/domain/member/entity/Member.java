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

   @Column(unique = true)
   private String password;



   public static Member of(String studentId, String password){
      return Member.builder()
              .studentId(studentId)
              .password(password)
              .build();
   }


}
