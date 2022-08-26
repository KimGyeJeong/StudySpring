package com.board.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component	//스프링 빈으로 등록되기 위한 어노테이션
@Aspect		//해당 클래스가 Aspect를 구현한 것임을 나타냄(aop관련 클래스)
@Log4j
public class AnnotationAdvice {
	//어노테이션 활용해 aop 사용
	/*
	 * 방법 1.
	@Pointcut("execution(* test*(..))")
	private void testPointCut() {};	//외부에서 부르지 않음 private 사용
	
	@Before("testPointCut()")		//advice 의 before
	public void before() {
		//공통 기능 구현 코드..
	}
	*/
	
	// 방법 2
//	@Before("execution(* test*(..))")
//	public void before() {
//		//공통 기능 구현 코드..
//		
//	}
	
	@Around("execution(* com.board.service.TestService*.*(..))")
	public Object around(ProceedingJoinPoint j) throws Throwable{
		//before
		log.info("Before.................................");
		
	//	log.info(Arrays.toString(j.getArgs()));	//[10, 20]
		
		Object[] args = j.getArgs();
		for(Object o:args) {
			if(o instanceof HttpServletRequest) {
				log.info("request ! ");
				log.info("o : "+o);
				
				HttpServletRequest request = (HttpServletRequest)o;
				//로그인 했는지 확인 
				//처럼 사용할 수 있음
				request.getSession();
				//쿠키도 꺼낼수 있음..
			}
		}
		
		Object obj = j.proceed();
		//after
		log.info("..................................after");
		return obj;
	}
	
	//에러 발생시
	//예외 발생한 로그 찍는것. 예외 자체를 객체로 받을 수 있음
//	@AfterThrowing(pointcut="execution(* com.board.service.TestService*.*(..))",throwing"e")
//	public void afterTh(Exception e) {
//		//Exception 보다 크게 받으려면 interface인 Throwable을 받을수 있음.
//		//사용예 public void afterTh(Throwable e){}
//		log.info(e.getMessage());
//		
//	}
	

}
