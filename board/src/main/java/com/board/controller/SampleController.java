package com.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.board.domain.Bank;
import com.board.domain.SampleVO;

import lombok.extern.log4j.Log4j;

/*
---------------- 데이터 타입 --------------------
 produces
 서버에서 생산하여 브라우저에 리턴해주는 데이터의 형태를 지정.
 브라우저에서 요청 시 지정한 accept 요청 헤더값과 일치 해야함
 서버 --> 브라우저
 consumes
 브라우저에서 요청시 지정한 Content-Type 과 일치하게 작성.
 브라우저 --> 서버
 */


@RestController
@Log4j
@RequestMapping("/sample/*")
public class SampleController {
	
	//단순 문자열 리턴
	@GetMapping(value="getText",produces = "text/plain;charset=UTF-8")
//	@GetMapping(value="getText",produces = MediaType.TEXT_PLAIN_VALUE)
//	으로 사용가능하지만 한글이 깨짐...
	public String getText() {
		log.info("====================getText====================");
		log.info("====================" + MediaType.TEXT_PLAIN_VALUE);
		//INFO : com.board.controller.SampleController - ====================text/plain
		
		//return "Hello, World";
		// 기존의 @Controller는 문자열을 리턴하는 경우 JSP파일 이름으로 처리하지만,
		//@RestController의 경우에는 순수한 데이터가 리턴됨
		//produces = "text/plain;charset=UTF-8" 속성은 해당 메서드가 생성하는 MIME 타입을 의미함
		//위와 같이 문자열로 직접 지정할 수도 있고, MediaType이라는 클래스를 이용할 수도 있음.
		return "뱅겁다";
	}
	
	//객체리턴 (produces 생략 가능)
	@GetMapping(value="getVO",produces= {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	//produces = "text/plain;charset=UTF-8" 으로작성하면 500에러
	public SampleVO getVO() {
		//XML으로 만들어짐
		
		//주소에 getVO.json 으로 부르면 json방식으로 옴.
		
		return new SampleVO(123, "이름", "address");
	}
	
	//컬렉션 타입 리턴 : List
	@GetMapping(value="getList")
	public List<SampleVO> getList(){
		List<SampleVO> list = new ArrayList<SampleVO>();
		
		for(int i=0; i<10; i++) {
			list.add(new SampleVO(i,i+"번째이름","주소 : "+i));
		}
		
		return list;
	}
	
	//컬렉션 타입 리턴 : Map
	@GetMapping(value="getMap")
	public Map<String, SampleVO> getMap(){
		Map<String, SampleVO> map = new HashMap<String, SampleVO>();
		
		map.put("First",new SampleVO(1,"First","첫번째"));
		map.put("Second",new SampleVO(2,"Second","두번째"));
		
		//map은 LIFO방식?
		
		return map;
	}
	
	//ResponseEntity 타입 리턴
	@GetMapping(value="gradeCheck",params = {"eng","math"})
	public ResponseEntity<SampleVO> gradeCheck(Integer eng, Integer math){
		//전송해줄 데이터 타입. 한개.
		//여러개 보내주고 싶으면 ResponseEntity<List<SampleVO>>

		//파라미터 받아서 출력
		//영어와 수학점수를 요청 파라미터로 받아서, 60점 미만이면 상태코드 에러 전송.
		
		//vo name에 eng 넣고, addr 에 math 집어넣음
		SampleVO vo = new SampleVO(0, eng+"", math+"");
		
		ResponseEntity<SampleVO> result = null;
		
		if(eng < 60 || math < 60)
			//상태코드 bad_gatewat 넣어주고. 바디에 vo 데이터 넣어줌
			// 상태코드 502 추가, 몸체에 데이터 추가
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		else
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		
		//데이터는 잘 넘어가는 중. 에러 확인은 네트워크에서확인. 상태코드 502, 200 정상 작동
		
		return result;
	}
	
	// @PathVariable 사용
	@GetMapping("/news/{cat}/{nid}")
	public String[] getPathVar(@PathVariable("cat") String cat,@PathVariable("nid") Integer nid) {
		//값을 명시해줌..
		
		log.info("Cat : "+cat + ", NID: "+nid);
		
		/* 
		http://localhost:8080/sample/news/IT/32
		XML
		<Strings>
		<item>category : IT</item>
		<item>news_id : 32</item>
		</Strings>
		--------------
		JSON
		[
		  "category : IT",
		  "news_id : 32"
		]
		 */
		
		return new String[] {"category : "+cat, "news_id : "+nid};
	}

	//TODO 0831 해보기
	//bno 주고 글 가져오기.. bno번호 글 줘!
	@GetMapping("/board/read/{bno}")
	public String getPathVar2(int testNum,@PathVariable("bno") Integer bno) {
		
		// 던져주기 테스트 1...
		//http://localhost:8080/sample/board/read/320?testNum=111
		log.info(bno);
		log.info(testNum);
//		INFO : com.board.controller.SampleController - 320
//		INFO : com.board.controller.SampleController - 111
		
		return "";
	}
	
//	@PostMapping("bank") 처음.
	@PostMapping(value="bank", consumes = "application/json")	
	//옵션 추가. 보낼때 헤더정보에 content-type. application/json으로 보내줌.
	// mime 타입 같게 해주기 위해 추가.
	public Bank convertBank(@RequestBody Bank bank) {
		//@RequestBody
		//요청시 데이터를 JSON으로 보내고, 여기 서버에서 Bank 자바 객체로 변환해서 받기
		
		//외부에서 post로 보낼때 시큐리티때문에 csrf 사용해야하기때문에
		//security-context.xml에서 csrf disabled 시킴.
		
		//데이터 보내줄때 json으로 보내줌
//		{
//			"accountNo": 123,
//			"name": "이름",
//			"bankName": "BANK"
//		}
		log.info("convert to Bank obj : " + bank);
		
		return bank;
	}

}
