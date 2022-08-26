package com.board.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class TestServiceTests {

	@Autowired
	private TestService service;
	/*
	 * @Test public void testClass() { log.info(service);
	 * log.info(service.getClass().getName()); }
	 */

	@Test
	public void testAbc() {
		log.info("==============testABC============");
		
		//핵심기능이라 가정했던 helloAop를 실행하기전에 root에서 aop를 먼저 실행하게됨
		service.helloAop(10, 20);
		//실행 before, after
//		INFO : com.board.service.TestServiceTests - ==============testABC============
//		INFO : com.board.aop.Advice - ==================================before advice ===================
//		INFO : com.board.aop.Advice - ==================================before advice ===================
//		INFO : com.board.aop.Advice - ==================================before advice ===================
//		INFO : com.board.service.TestServiceImpl - helloAOP!
//		INFO : com.board.aop.Advice - ==================================after advice ===================
//		INFO : com.board.aop.Advice - ==================================after advice ===================
//		INFO : com.board.aop.Advice - ==================================after advice ===================		
		
		
		//실행 afterreturning, around
//		INFO : com.board.service.TestServiceTests - ==============testABC============
//		INFO : com.board.aop.Advice - ==================================around advice before !정상작동! ===================
//		INFO : com.board.service.TestServiceImpl - helloAOP!
//		INFO : com.board.aop.Advice - ==================================afterReturning advice ===================
//		INFO : com.board.aop.Advice - ==================================afterReturning advice ===================
//		INFO : com.board.aop.Advice - ==================================afterReturning advice ===================
//		INFO : com.board.aop.Advice - ==================================around advice after !정상작동! ===================
	
	}

}
