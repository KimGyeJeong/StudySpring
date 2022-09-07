package com.member.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import lombok.extern.log4j.Log4j;

@Log4j
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

    //시큐리티가 알아서 값들은 채워줌
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        log.info("======================================");
        log.info("CustomLoginSuccessHandler. Login Success");

        List<String> roleNames = new ArrayList<String>();

        //collection 타입 리턴
        //CustomUser 보면 collection타입을 볼 수 있음
        //로그인한 사람의 권한 목록 얻어오기
        authentication.getAuthorities().forEach(authority -> roleNames.add(authority.getAuthority()));

        log.info("======================================");
        log.info("CustomLoginSuccessHandler");
        log.info("Role Names : " + roleNames);
		
		/*
		 * 권한에 따른 페이지 이동 처리
		//만약 권한이 어드민이라면 관리자메인페이지로 가겠다.
		if(roleNames.contains("ROLE_ADMIN")) {
			response.sendRedirect("/admin/main");
		}
		//만약 판매자 권한이라면 판매자페이지로 가겠다
		if(roleNames.contains("ROLE_SALES")) {
			response.sendRedirect("/sales/main");
		}
		*/

        //만약에 로그인폼으로 강제 이동되어, 로그인처리 성공후 보던곳으로 가기 위한 처리
        //request로 세션 바로 뽑아낼수 있음
        HttpSession session = request.getSession();
        if (session != null) {
            String redirectURL = (String) session.getAttribute("prevPage");
            if (redirectURL != null) {
                //prevPage에 저장한 값 지워주고 원래 보던 페이지로 이동
                session.removeAttribute("prevPage");
                response.sendRedirect(redirectURL);
            } else {
                //만약에 값이 없다면 메인 이동
                response.sendRedirect("/common/main");
            }
        } else {
            //세션값이 null 이라면 메인이동
            response.sendRedirect("/common/main");
        }
    }

}
