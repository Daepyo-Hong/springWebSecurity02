package com.green.nowon.security;

import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.entity.MemberEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceProcess implements UserDetailsService {
    //DaoAuthenticationProvider -> DaoAuthenticationProvider ->
    // UserDetailsService : username(로그인페이지의 아이디 또는 이메일)

    private final MemberEntityRepository repository;
    //Constructor DI : @RequiredArgsConstructor + final field

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // username : 로그인페이지에서 입력했던 아이디 또는 이메일
        //System.out.println("username: "+username);

        //DB에서 회원정보를 읽어오기
        //이메일을 이용하여 회원정보를 읽어오기 : 쿼리메서드 새로 생성
        //find:select By:where Email:엔티티의 변수명!!!(물리디비 말고!!)
        //Optional
//        MemberEntity result = repository.findByEmail(username)
//                .orElseThrow(()-> new UsernameNotFoundException(">>>>유저가 존제하지 않아요"));
////
//        System.out.println("--------------------------------------");
//        String email=result.getEmail();
//        System.out.println("email: "+email);
//        String pass=result.getPass();
//        System.out.println("pass: "+pass);
//        Set<MyRole> roles = result.getRoles();
//        Set<SimpleGrantedAuthority> grantedAuthorities = new HashSet<>();
//        for(MyRole role : roles){
//            grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
//        }
        //java 8 이상
        /*
        Set<SimpleGrantedAuthority> grantedAuthorities = result.getRoles().stream()
                .map(role->new SimpleGrantedAuthority("ROLE_"+role.name()))
                .collect(Collectors.toSet());
        */
        //return new MyUserDetails(result);
        return new MyUserDetails(repository.findByEmail(username).orElseThrow());
    }
}
