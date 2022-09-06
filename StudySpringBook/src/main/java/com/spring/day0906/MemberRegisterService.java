package com.spring.day0906;

import java.time.LocalDateTime;

public class MemberRegisterService {

    private MemberDAO memberDAO;

    public  MemberRegisterService(MemberDAO memberDAO){
        this.memberDAO = memberDAO;
    }

    public Long regist(RegisterRequest req){
        Member member = memberDAO.selectByEmail(req.getEmail());
        if(member!=null){
            throw new DuplicateMemberException("dup email"+req.getEmail());
        }
        Member newMember = new Member(req.getEmail(), req.getPassword(), req.getName(), LocalDateTime.now());
        memberDAO.insert(newMember);
        return newMember.getId();
    }
}
