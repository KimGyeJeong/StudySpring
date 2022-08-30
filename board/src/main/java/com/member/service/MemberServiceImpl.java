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
			// 일반회원가입시 권한 추가
			authVo.setAuth("ROLE_MEMBER");
			result = mapper.addAuth(authVo);
		} else if (auth.equals("admin")) {
			// 관리자로 가입시 권한 추가
			authVo.setAuth("ROLE_MEMBER");
			result = mapper.addAuth(authVo);
			authVo.setAuth("ROLE_ADMIN");
			result = mapper.addAuth(authVo);
		}
		return result;
	}

	@Override
	public MemberVO getMember(String id) {
		return mapper.getMember(id);
	}

	@Override
	public int modifyMember(MemberVO member) {
		// id, pw 체크 추가
		int result = 0;
		MemberVO dbmember = getMember(member.getId());
		if (bcryptPasswordEncoder.matches(member.getPw(), dbmember.getPw())) {
			// 암호화된 두 비밀번호가 같으면 true. 즉 안쪽 명령어 실행
			result = mapper.updateMember(member);
		}
/*
		result = mapper.idpwCheck(member);
		// 수정
		if (result == 1) {
			mapper.updateMember(member);
		}
*/
		return result;
	}

	@Override
	public int deleteMember(MemberVO member) {
		// id, pw 체크 추가
		int result = 0;
		MemberVO dbmember = getMember(member.getId());
		if(bcryptPasswordEncoder.matches(member.getPw(), dbmember.getPw())) {
			result = 1;
			
			//FK 제약조건때문에 Auth 먼저 삭제하고 member 삭제
			int deleteRes = mapper.deleteAuth(member.getId());
			log.info("************* deleteMemberAuth res : "+deleteRes);
			deleteRes = mapper.deleteMember(member.getId());
			log.info("************* deleteMember res : "+deleteRes);
			
		}
/*		
		result = mapper.idpwCheck(member);
		// 수정
		if (result == 1) {
			mapper.deleteMember(member.getId());
			// 권한 리스트에 따라 여러개면 다 지워야 함
			// member.getAuthList().forEach(auth->mapper.deleteAuth(member.getId()));
			// 권한 삭제
			mapper.deleteAuth(member.getId());
		}
*/
		return result;
	}

}
