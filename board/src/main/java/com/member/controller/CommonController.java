package com.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.member.domain.MemberVO;
import com.member.service.MemberService;

import lombok.extern.log4j.Log4j;

//비로그인시 사용할 컨트롤러
@Controller
@Log4j
@RequestMapping("/common/*")
public class CommonController {

	@Autowired
	private MemberService service;

	@GetMapping("main")
	public void main() {
		log.info("===========================================================");
		log.info("main....");
		log.info("===========================================================");
	}

	// 로그인 폼 요청. 처리는 시큐리티가 처리 해줌.
	@GetMapping("login")
	public void login(String error, HttpServletRequest request) {
		log.info("===========================================================");
		log.info("login....");
		log.info("===========================================================");

		// error 생성시
		log.info("commonController.login. Value error : " + error);

		// 만약 접근제한때문에 로그인폼으로 강제이동되었다면 직전 url 정보 뽑아서 session 에 임시저장
		String referer = request.getHeader("Referer");
		request.getSession().setAttribute("prevPage", referer);	//prevPage에 referer으로 전에 보던 페이지 저장
		
	}

	// 회원가임 폼.
	@GetMapping("signup")
	public void signup() {
		log.info("===========================================================");
		log.info("signup....");
		log.info("===========================================================");
	}

	@PostMapping("signup")
	public String signupPro(MemberVO member, String au, RedirectAttributes rttr) {
		log.info("-----------------------------------------------------------");
		log.info(member.toString());
		log.info(au);
		log.info("-----------------------------------------------------------");

		int result = service.addMember(member);
		int result2 = service.addAuth(au, member.getId());

		if (result == 1) {
			rttr.addFlashAttribute("msg", "success");
		}

		log.info("result : " + result);
		log.info("result2 : " + result2);

		return "redirect:/common/main";
	}

	// 접근 제한시 보여줄 페이지 경로 매핑(403에러시 보여줄 페이지)
	@GetMapping("accessError")
	public void accessError(Authentication auth) {
		// 일반회원이 관리자 페이지에 들어갈때에도 작동.
		log.info("CommonController. ===========접근제한==========");
		log.info("auth : " + auth);

	}
	
	// TODO 0901 로그아웃 커스텀 해주기
	@GetMapping("customLogout")
	public String customLogout() {
		log.info("커스텀 로그아웃....");
		
		//TODO 0901 페이지 만들어주기... view...없이...
		return "common/logout";
	}

}
