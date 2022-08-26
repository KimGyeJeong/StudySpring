package com.board.service;

import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class TestServiceImpl implements TestService{
	
	
	//핵심 기능이라고 가정.
	@Override
	public void helloAop(Integer a, Integer b) { 
		
		//exception 발생
		//afterThrowing 실행
		//log.info(0/0);
		
		//아래 기능이 핵심기능이라고 가정.
		log.info("helloAOP!");
	}

}
