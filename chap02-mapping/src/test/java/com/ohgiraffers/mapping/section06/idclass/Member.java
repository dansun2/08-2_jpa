package com.ohgiraffers.mapping.section06.idclass;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "member_section06")
@Table(name = "tbl_member_section06") // 어떤 테이블과 매핑할건지
public class Member {

    // 두가지 이상의 값이 조인되어 생성될 때?
    // PK가 한 테이블에 두가지 일 경우나 PK가 없을때 링크테이블 안에서 값을 식별할 경우 프리미티브 자료형?이 안됨
    // 레퍼런스타입(클래스자료형)으로 만들어줘야함
    @EmbeddedId
    private MemberPK memberNo;

    @Column(name = "member_pwd")
    private String memberPwd;

    @Column(name = "nickName")
    private String nickName;

    public Member() {
    }

    public Member(MemberPK memberNo, String memberPwd, String nickName) {
        this.memberNo = memberNo;
        this.memberPwd = memberPwd;
        this.nickName = nickName;
    }

    public MemberPK getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(MemberPK memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberPwd() {
        return memberPwd;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
