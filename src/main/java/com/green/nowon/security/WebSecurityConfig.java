package com.green.nowon.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

//https://docs.spring.io/spring-security/reference/servlet/configuration/java.html

@EnableWebSecurity
public class WebSecurityConfig {

    //매번 다르게 암호화된다.(matches 메서드로 일치여부판단) 복호화 불가.(단방향)
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        MyRole role = MyRole.ADMIN;
//        MyRole role2 = MyRole.USER;
        http
                .authorizeRequests(authorize -> authorize           //링크 중복 : 먼저 적용된것이 적용됩니다.
                        .antMatchers("/css/**", "/js/**", "/images/**").permitAll()  // 공유폴더 접근가능해야 적용됨
                        .antMatchers("/").permitAll()    //누구나 접근가능
                        .antMatchers("/admin/**").hasRole("ADMIN")      //인증된 유저이며 ROLE_ADMIN
                        .anyRequest().authenticated())              //나머지 주소는 인증된(ROLE 상관 없이 로그인한) 유저만 허용
                .csrf(csrf -> csrf.disable())              //람다식        //csrf토큰 사용하면 번거로워서 일단 미적용
                //.csrf(AbstractHttpConfigurer::disable)   //메서드참조
                //.csrf().disable()                        //메서드호출..
                .formLogin(login -> login                  //세팅정보는 ()안에 람다식으로 표기해야 헷갈리지 않음!
                        .loginPage("/signin")              //인증이 필요한 주소로 요청시 redirect 됩니다. --컨트롤러에서 연결
                        .usernameParameter("email")
                        .passwordParameter("pass")
                        .loginProcessingUrl("/signin-action")   //login페이지의 from태그의 action
                        .permitAll())

        ;

        return http.build();
    }
}
