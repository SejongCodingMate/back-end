package com.creativesemester.SejongCodingMate.domain.member.entity;


import com.creativesemester.SejongCodingMate.domain.chapter.entity.Chapter;
import com.creativesemester.SejongCodingMate.domain.story.entity.Story;
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

   @OneToOne
   @JoinColumn(name = "STORY_ID")
   private Story story;

   @OneToOne
   @JoinColumn(name = "CHAPTER_ID")
   private Chapter chapter;

   @Column(unique = true, nullable = false)
   private String memberId;

   @Column(nullable = false)
   private String password;

   @Column(nullable = false)
   private Boolean hasTemporaryPassword;

   @Column(nullable = false)
   private String name;


   public static Member of (String memberId, String password, Story story, Chapter chapter, Boolean hasTemporaryPassword, String name){
      return Member.builder()
              .memberId(memberId)
              .password(password)
              .story(story)
              .chapter(chapter)
              .hasTemporaryPassword(hasTemporaryPassword)
              .name(name)
              .build();
   }

   public void changePassword(String newPassword) {
      this.password = newPassword;
   }

   public void changeName(String newName) {
      this.name = newName;
   }

   public void changeStory(Story story){
      this.story = story;
   }

   public void changeChapter(Chapter chapter){
      this.chapter = chapter;
   }

   public void changeHasTemporaryPassword(Boolean hasTemporaryPassword){
      this.hasTemporaryPassword = hasTemporaryPassword;
   }
}
