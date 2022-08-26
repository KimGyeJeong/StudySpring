package com.board.aop;

import org.aspectj.lang.ProceedingJoinPoint;

import lombok.extern.log4j.Log4j;

@Log4j
public class Advice {
// 공통기능 구현해놓을 클래스
	
	public void before() {
		log.info("==================================before advice ===================");
		log.info("==================================before advice ===================");
		log.info("==================================before advice ===================");
	}
	public void after() {
		log.info("==================================after advice ===================");
		log.info("==================================after advice ===================");
		log.info("==================================after advice ===================");
	}
	public void afterReturning() {
		log.info("==================================afterReturning advice ===================");
		log.info("==================================afterReturning advice ===================");
		log.info("==================================afterReturning advice ===================");
	}
	public void afterThrowing() {
		log.info("==================================afterThrowing advice ===================");
		log.info("==================================afterThrowing advice ===================");
		log.info("==================================afterThrowing advice ===================");
	}
	public void around() {
		//정상적으로 실행이 되지 않음.
		//before, after 중에 before 문만 실행되었음.
		log.info("==================================around advice ===================");
		log.info("==================================around advice ===================");
		log.info("==================================around advice ===================");
	}
	/*
	around advice 메서드 구현 방법
	첫번째 매개변수 ProceedingJoinPoint j 지정
	Return타입 Object 타입으로, 타겟으로 가기 위해 j객체 안에 있는 j.proceed() 호출하여
	리턴받은 값을 리턴해주는 형태로 작성
	 */
	public Object around2(ProceedingJoinPoint j) throws Throwable{
		log.info("==================================around advice before !정상작동! ===================");
		//위에는 before
		Object obj = j.proceed();	//타겟 메소드 실행
		//아래는 after
		log.info("==================================around advice after !정상작동! ===================");
		
		return obj;
	}

}
