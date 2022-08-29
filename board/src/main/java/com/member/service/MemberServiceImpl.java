package com.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.member.domain.AuthVO;
import com.member.domain.MemberVO;
import com.member.persistence.MemberMapper;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberMapper mapper;

	// 비밀번호 암호화를 위한 객체 자동 주입.
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;

	@Override
	public int addMember(MemberVO member) {
		log.info("SERVICE================= member.pw : " + member.getPw() + "================");

		member.setPw(bcryptPasswordEncoder.encode(member.getPw()));
		log.info("SERVICE================= member.pw : " + member.getPw() + "================");

		return mapper.addMember(member);
	}

	@Override
	public int addAuth(String auth, String id) {
		int result = -1;
		AuthVO authVo = new AuthVO();
		authVo.setId(id);
		if (auth.equals("member")) {
			//일반회원가입시 권한 추가
			authVo.setAuth("ROLE_MEMBER");
			result = mapper.addAuth(authVo);
		} else if (auth.equals("admin")) {
			//관리자로 가입시 권한 추가
			authVo.setAuth("ROLE_MEMBER");
			result = mapper.addAuth(authVo);
			authVo.setAuth("ROLE_ADMIN");
			result = mapper.addAuth(authVo);
		}
		return result;
	}

	@Override
	public MemberVO getMember(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int modifyMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(MemberVO member) {
		// TODO Auto-generated method stub
		return 0;
	}

}
