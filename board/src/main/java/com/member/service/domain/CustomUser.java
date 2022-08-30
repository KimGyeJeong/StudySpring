 package com.member.service.domain;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.member.domain.MemberVO;

import lombok.Getter;

@Getter
public class CustomUser extends User{//jsp에서 principal
	
	//회원 정보 담을 인스턴스 변수 추가
	private MemberVO member;

	public CustomUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		// 부모에다가 던져줌
		//시큐리티에 필요 한 3가지 정보
		super(username, password, authorities);
		
		//문법적으로 필요하기에 생성했지만
		//실제적으로 사용은 아래로 사용 함.
	}
	
	//외부에서 vo로 들어오면
	public CustomUser(MemberVO vo) {
		//위와 비슷
		//우리는 id, pw라고 사용하지만
		//스프링 시큐어리티에서는 username, password 라고 사용함
		super(vo.getId(), vo.getPw(),vo.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
				//list의 하나가 auth에(변수명은 자유롭게) 담기고
				// authVO의 auth('ROLE_MEMBER'와 같은 문자열)를 하나꺼내서
				// 생성자에 값을 던져주고
				.collect(Collectors.toList())
				);
		//시큐리티에도 추가, 여기의 멤버에도 넣어서 사용할 수 있게 하기.
		//DB가져와서 시큐리티에도 던져주고, 나중에 다른곳에서 편하게 사용할 수 있게 정보 저장
		this.member = vo;
	}

}
