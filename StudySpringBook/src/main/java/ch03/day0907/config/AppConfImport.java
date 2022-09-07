package ch03.day0907.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import ch03.day0907.spring.MemberDao;
import ch03.day0907.spring.MemberPrinter;

@Configuration
@Import({AppConf2.class})
public class AppConfImport {

	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberPrinter memberPrinter() {
		return new MemberPrinter();
	}
}
