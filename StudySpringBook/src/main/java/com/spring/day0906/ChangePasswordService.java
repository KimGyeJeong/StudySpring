package com.spring.day0906;

public class ChangePasswordService {
    private MemberDAO memberDAO;

    public void changePassword(String email, String oldPwd, String newPwd){
        Member member = memberDAO.selectByEmail(email);
        if (member == null) {
            throw new MemberNotFoundException();
        }
        member.changePassword(oldPwd, newPwd);
        memberDAO.update(member);
    }
    public void setMemberDAO(MemberDAO memberDAO){
        this.memberDAO = memberDAO;
    }
}
