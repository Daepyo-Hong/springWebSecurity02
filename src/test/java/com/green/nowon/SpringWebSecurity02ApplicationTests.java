package com.green.nowon;

import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.entity.MemberEntityRepository;
import com.green.nowon.security.MyRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SpringWebSecurity02ApplicationTests {
    @Autowired
    MemberEntityRepository mrepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Test
    void 회원가입테스트() {

        MemberEntity entity = MemberEntity.builder()
                .email("test05@test.com").name("관리자").pass(passwordEncoder.encode("1234"))
                .build()
                .addRole(MyRole.USER)//Set 객체가 생성이 되어 있어야 사용가능(Set이 null이면 오류)
                .addRole(MyRole.ADMIN); //그래서 entity에서 @Builder.Default 추가와 new HashSet<>();초기화함

        mrepository.save(entity);
    }

    @Test
    void 수정시upDate테스트(){

    }

}
