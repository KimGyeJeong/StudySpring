package com.spring.day0906;

import java.util.HashMap;
import java.util.Map;

public class MemberDAO {

    private static long nextid = 0;
    private Map<String, Member> map = new HashMap<>();

    public Member selectByEmail(String email){
        return map.get(email);
    }

    public void insert(Member member){
        member.setId(++nextid);
        map.put(member.getEmail(), member);
    }

    public void update(Member member){
        map.put(member.getEmail(), member);
    }
}
