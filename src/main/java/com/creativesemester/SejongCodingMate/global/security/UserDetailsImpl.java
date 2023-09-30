package com.creativesemester.SejongCodingMate.global.security;

import com.creativesemester.SejongCodingMate.domain.member.entity.Member;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private final Member member;
    private final String userEmail;

    public UserDetailsImpl(Member member, String userEmail){
        this.member = member;
        this.userEmail = userEmail;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String authority = member.getMemberId();

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);

        return authorities;
    }

    public Member getMember(){
        return member;
    }

    @Override
    public String getUsername() {
        return this.userEmail;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


}