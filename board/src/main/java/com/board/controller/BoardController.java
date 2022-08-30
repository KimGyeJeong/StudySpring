package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.domain.PageDTO;
import com.board.service.BoardService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	private BoardService service;

	@RequestMapping("list")
	public void list(Model model, Criteria cri) {
		log.info("list...");

		// 0824 주석 처리
		// model 로 list jsp로뿌려줌
		//model.addAttribute("list", service.getList());
		
		//페이징 처리 위해 수정
		model.addAttribute("list", service.getListWithPaging(cri));
		
		log.info("CRI찍기 ... "+cri.toString());
		
		//임의 추가.. 임의로 마지막 글의 개수(total) 123개로 줌
		//model.addAttribute("pager", new PageDTO(cri, 123));

		
		//전체 글 수 받아오기.
		//검색 키워드도 받아주기 위해 cri로 보내기
		int total = service.getTotal(cri);	
		log.info("-------------------------------------------------------");
		log.info("TOTAL : "+total);
		
		model.addAttribute("pager", new PageDTO(cri, total));
	}

	// 둘이 보는것은 같음(원래 read와 modify 같이있었지만 권한을 주기 위해 분리시킴_0830)
	// void 타입이기 때문에 페이지 가는거는 상관없음
	@GetMapping("read")
	public void read(Model model, Long bno, @ModelAttribute("cri") Criteria cri) {
		model.addAttribute("board", service.get(bno));
	}
	
	@GetMapping("modify")
	@PreAuthorize("isAuthenticated()")
	public void modifyForm(Model model, Long bno, @ModelAttribute("cri") Criteria cri) {
		model.addAttribute("board", service.get(bno));
	}

	@PostMapping("modify")
	@PreAuthorize("principal.username == #board.writer")	//작성자와 로그인한 사람이 동일해야 수정 가능. #은 변수같은 개념. board가 넘어오기때문에 board의~ 로 작성가능
	public String modify(BoardVO board, RedirectAttributes rttr, Criteria cri) {
		// 수정처리
		if (service.modify(board)) {
			log.info("수정성공!");
			//0824 추가로 작성
			rttr.addFlashAttribute("result","success");
		}
		//0824 수정전
		//return "redirect:/board/list";
		//수정후
		return "redirect:/board/list"+cri.getListLink();
	}

	@PreAuthorize("principal.username == #writer")	//작성자와 로그인한 사람이 동일해야 삭제 가능
	@PostMapping("delete")
	public String delete(Long bno, RedirectAttributes rttr, Criteria cri, String writer) {
		//바인딩 확실시 하기위해서는 @requestparam 사용
		
		// 삭제처리
		if (service.delete(bno)) {
			log.info("삭제성공!");
			//0824 추가로 작성
			rttr.addFlashAttribute("result","success");

		}
		return "redirect:/board/list"+cri.getListLink();
	}
	
	//글 등록 폼
	@GetMapping("write")
	@PreAuthorize("isAuthenticated()")	//먼저 권한이 있는지 확인하기. 로그인한 사용자만 접근 가능하도록..
	public void write() {
		
	}
	
	//글 등록 처리
	@PostMapping("write")
	@PreAuthorize("isAuthenticated()")
	public String writepro(BoardVO board, RedirectAttributes rttr) {
		
		log.info(board.toString());
		
		service.register(board);
		
		//RedirectAttributes : model 처럼 스프링MVC가 자동으로 전달해주는 객체
		// addFlashAttribute(key, value) key-value 형식으로 추가 (좀 더 좋은 사용법)
		//  : url뒤에 데이터가 붙지 않고, 일회성 데이터로 페이지를 새로고침하면 데이터가 날라감
		//	: 값 1개만 전달가능. 2개 이상은 데이터가 소멸하므로 Map 이용해 한번에 보내야 함
		// addAttribute(key, value)
		//	; url 뒤에 데이터가 붙음. 일회성데이터가 아님
		
		//위와 같은 메서드를 이용하여 리다이렉트되는jsp페이지에 데이터 전달할 수 있음
		
		rttr.addFlashAttribute("result", board.getBno());
		//등록 처리 후 글 고유번호 속성으로 추가해서 전달(모달 modal실행).. (list에서 사용)
		
		return "redirect:/board/list";
	}

}
