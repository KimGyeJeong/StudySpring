package com.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	//로그인 폼 요청. 처리는 시큐리티가 처리 해줌.
	@GetMapping("login")
	public void login() {
		log.info("===========================================================");
		log.info("login....");
		log.info("===========================================================");
	}
	
	//회원가임 폼. 
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
		
		if(result == 1) {
			rttr.addFlashAttribute("msg","success");
		}
		
		log.info("result : "+result);
		log.info("result2 : "+result2);
		
		return "redirect:/common/main";
	}

}
