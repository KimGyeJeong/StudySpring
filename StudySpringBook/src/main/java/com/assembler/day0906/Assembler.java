package com.assembler.day0906;

import com.spring.day0906.ChangePasswordService;
import com.spring.day0906.MemberDAO;
import com.spring.day0906.MemberRegisterService;

public class Assembler {

    private MemberDAO memberDAO;
    private MemberRegisterService regSvc;
    private ChangePasswordService pwdSvc;

    public Assembler(){
        memberDAO = new MemberDAO();
        regSvc = new MemberRegisterService(memberDAO);
        pwdSvc = new ChangePasswordService();
        pwdSvc.setMemberDAO(memberDAO);
    }
    public MemberDAO getMemberDAO(){
        return memberDAO;
    }
    public MemberRegisterService getRegSvc(){
        return regSvc;
    }

    public ChangePasswordService getPwdSvc() {
        return pwdSvc;
    }
}
