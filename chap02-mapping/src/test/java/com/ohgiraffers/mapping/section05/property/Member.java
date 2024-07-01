package com.ohgiraffers.mapping.section05.property;

import jakarta.persistence.*;

@Entity(name = "member_section05_property")
@Table(name = "tbl_member_section05_property")
/*
 * 클래스 레벨 : 모든 필드에 대해서 Getter 접근 방식을 적용한다.
 * 주의할 점은 @Id 어노테이션이 필드에 있다면 엔티티를 생성하지 못하기 때문에 @Id 어노테이션을 getter 메소드 위로 옮겨야 한다.
 * */
@Access(AccessType.PROPERTY)
public class Member {
    @Column(name = "member_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberNo;
    @Column(name = "member_id")
    private String memberId;
    @Column(name = "member_pwd")
    private String memberPwd;
    @Column(name = "nick_name")
    private String nickName;

    public Member() {
    }

    public Member(int memberNo, String memberId, String memberPwd, String nickName) {
        this.memberNo = memberNo;
        this.memberId = memberId;
        this.memberPwd = memberPwd;
        this.nickName = nickName;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public void setMemberPwd(String memberPwd) {
        this.memberPwd = memberPwd;
    }


    @Id
    public int getMemberNo() {
        return memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public String getMemberPwd() {
        return memberPwd;
    }

    @Access(AccessType.PROPERTY)
    public String getNickName() {
        System.out.println("getnickName을 이용한 호출");
        return nickName + "님";
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberNo=" + memberNo +
                ", memberId='" + memberId + '\'' +
                ", memberPwd='" + memberPwd + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
