package com.member.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		log.info("로그아웃 핸들러 성공!");
		//세션을 여기서 지워도 괜찮음.
		
		//TODO 0901
		//방법1...
		//보던 페이지 추가. (loginSuccessHandler 참고)
//		HttpSession session = request.getSession();
//		if(session != null) {
//			String redirectURL = (String)session.getAttribute("prevPage");
//			if(redirectURL != null) {
//				//prevPage에 저장한 값 지워주고 원래 보던 페이지로 이동
//				session.removeAttribute("prevPage");
//				response.sendRedirect(redirectURL);
//			}else {
//				//만약에 값이 없다면 메인 이동
//				response.sendRedirect("/common/main");
//			}
//		}else {
//		//세션값이 null 이라면 메인이동
//		response.sendRedirect("/common/main");
//		}
		
		
		/*
		//main으로 돌아가기
		response.sendRedirect("/common/main");
		*/
		
		
		//방법2
		//referer 꺼내서 이동
		//referer 는 원래 web 안에 들어있음 . 전의 보던 페이지 들어있음
		//전에 보던 페이지 를 들고와서 가겠다. 라는 명령어
		//commoncontroller 의 @GetMapping("login") 안에 존재
		response.sendRedirect(request.getHeader("referer"));
		
	}

}
