package com.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.member.domain.MemberVO;
import com.member.persistence.MemberMapper;
import com.member.service.domain.CustomUser;

import lombok.extern.log4j.Log4j;

//스프링 시큐리티의 권한 인증을 처리해주는 비즈니스 로직 서비스 클래스
//시큐리티 내부에서 사용되어지는 UserDetailsService 인터페이스를
// 구현하는 클래스로 작성
@Log4j
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private MemberMapper mapper;

	// username을 주면 회원정보를 UserDetails로 리턴해주는 처리가 되어야함.
	//우리가 사용하는 회원정보(회원테이블)는 MemberVO형태 이며,
	//이 메서드가 리턴해주는 타입은 UserDetails 인터페이스여야 함.
	// --> MemberVO를 UserDetails 타입으로 변환해주는 처리가 필요함.
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		//시큐리티의 username은 우리가 id 로 사용하는 것
		log.info("LoadUserByUsername username : " + username);
		
		//vo 던져주고 user 가져오기
		MemberVO vo = mapper.getMember(username);
		log.info("===============================================");
		log.info(vo.toString());
		
		return vo==null ? null : new CustomUser(vo);
	}

}
