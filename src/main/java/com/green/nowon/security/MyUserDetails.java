package com.green.nowon.security;

import com.green.nowon.domain.entity.MemberEntity;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.stream.Collectors;


@Getter
public class MyUserDetails extends User {


    /**
     * 회원가입시 입력한 이메일 :getEmail();
     */
    private String email;
    /**
     * 회원가입시 입력한 이름 :getName();
     */
    private String name;
    /**
     * 회원가입시 입력한 닉네임 :getNickName();
     */
    private String nickName;

    public MyUserDetails(MemberEntity entity) {
        super(entity.getEmail(), entity.getPass(), entity.getRoles().stream()
                .map(role->new SimpleGrantedAuthority("ROLE_"+role.name()))
                .collect(Collectors.toSet()));
        this.email=entity.getEmail();
        this.name=entity.getName();
        this.nickName=entity.getNickName();
        if(this.nickName==null || this.nickName.equals("")){
            this.nickName="닉네임미적용";
        }
    }
}
