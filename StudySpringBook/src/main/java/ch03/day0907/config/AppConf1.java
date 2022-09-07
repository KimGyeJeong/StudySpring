package ch03.day0907.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch03.day0907.spring.MemberDao;
import ch03.day0907.spring.MemberPrinter;

@Configuration
public class AppConf1 {

	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
	
}
