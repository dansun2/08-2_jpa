package com.ohgiraffers.mapping.section06.idclass;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable // jpa에서 복합키(n개 이상의 키) 설정 할 때 Serializable을 상속받아야함(직렬화)
public class MemberPK implements Serializable { // Serializable 네트워크로 패키지 전송할 때 쓰이는건데 jpa에서는 복합키클래스 직렬화에 쓰임

    @Column(name = "member_no")
    private int memberNo;

    @Column(name = "member_id")
    private String memberId;

    public MemberPK() {
    }

    public MemberPK(int memberNo, String memberId) {
        this.memberNo = memberNo;
        this.memberId = memberId;
    }

    public int getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(int memberNo) {
        this.memberNo = memberNo;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    // 해시 함수 또는 해시 알고리즘 또는 해시함수알고리즘은 임의의 길이의 데이터를 고정된 길이의 데이터로 매핑하는 함수이다
    // =>비밀번호 암호화에 많이 쓰임
    @Override // 주소값을 기준으로 비교. 오버라이딩 하고 클래스 내부를 재정의해줘야함(바로밑에줄)
    public int hashCode() {
        return Objects.hash(memberNo, memberId);
    }

    @Override // 값을 기준으로 비교. 오버라이딩 하고 클래스 내부를 재정의해줘야함(바로밑에줄)
    public boolean equals(Object obj) { // 클래스간의 비교가 있을 수 있어서? Object 타입으로..

        if (this == obj){ // this=> 힙메모리에 할당된 주소값
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }

        MemberPK memberPK = (MemberPK) obj; //형변환
        return memberNo == memberPK.memberNo && Objects.equals(memberId, memberPK.getMemberId());
    }

    @Override
    public String toString() {
        return "MemberPK{" +
                "memberNo=" + memberNo +
                ", memberId='" + memberId + '\'' +
                '}';
    }
}
