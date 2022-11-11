package com.green.nowon.domain.entity;

import com.green.nowon.security.MyRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor     //빌더패턴 적용하려고
@NoArgsConstructor      //
@Getter
@SequenceGenerator(name = "g_mem_seq",
        sequenceName = "mem_seq", initialValue = 1,allocationSize = 1)
@Table(name = "mem")
@Entity
public class MemberEntity extends BaseDateTimeColumn {


    @Id
    @GeneratedValue(generator = "g_mem_seq", strategy = GenerationType.SEQUENCE)
    private long mno;

    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String pass;
    @Column(nullable = false)
    private String name;
    @Column(unique = true)
    private String nickName;

    //social user 여부
    //탈퇴여부 필드
    //휴면여부 필드
    //user ip 등 회원가입에 필요한 사항들 추가로 적용


    @Enumerated(EnumType.STRING)        // DB 저장유형 : 지정하지않으면 EnumType.ORDINAL 적용
    @CollectionTable(name = "my_role")      //물리테이블이름
    @ElementCollection(fetch = FetchType.EAGER)
    //fetch = FetchType.EAGER (즉시로딩): MemberEntity 읽어올 때 MyRole 데이터까지 읽어옵니다.
    //fetch = FetchType.LAZY: MemberEntity 읽어올 때 MyRole 데이터는 읽어오지 않고,
    //roles 필드명을 사용시 읽어옵니다. (지연로딩)
    @Builder.Default
    private Set<MyRole> roles = new HashSet<>();
    //1:M   MemberEntity : MyRole
    // MemberEntity 연관테이블 MyRole 에 roles 필드를 이용하여 데이터에 접근가능
    // 편의메서드를 만들면 쉽게 컨트롤 가능
    public MemberEntity addRole(MyRole role){
        roles.add(role);
        return this;
    }
    public MemberEntity removeRole(MyRole role){
        roles.remove(role);
        return this;
    }
}
