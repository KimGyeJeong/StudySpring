package ch03.day0907.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import ch03.day0907.spring.ChangePasswordService;
import ch03.day0907.spring.MemberDao;
import ch03.day0907.spring.MemberInfoPrinter;
import ch03.day0907.spring.MemberListPrinter;
import ch03.day0907.spring.MemberPrinter;
import ch03.day0907.spring.MemberRegisterService;
import ch03.day0907.spring.VersionPrinter;

@Configuration
public class AppCtx4GetBean {
	private MemberPrinter printer = new MemberPrinter();
	
	@Bean
	public MemberDao memberDao() {
		return new MemberDao();
	}
	
	@Bean
	public MemberRegisterService memberRegSvc() {
		return new MemberRegisterService(memberDao());
	}
	
	@Bean
	public ChangePasswordService changePwdSvc() {
		ChangePasswordService pwdSvc = new ChangePasswordService();
		pwdSvc.setMemberDao(memberDao());
		return pwdSvc;
	}
	
	@Bean
	public MemberListPrinter listPrinter() {
		return new MemberListPrinter(memberDao(), printer);
	}
	
	@Bean
	public MemberInfoPrinter infoPrinter() {
		MemberInfoPrinter infoPrinter = new MemberInfoPrinter();
		infoPrinter.setMemberDao(memberDao());
		infoPrinter.setPrinter(printer);
		return infoPrinter;
	}
	
	@Bean
	public VersionPrinter versionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(5);
		versionPrinter.setMinorVersion(0);
		return versionPrinter;
	}
	
	@Bean
	public VersionPrinter oldVersionPrinter() {
		VersionPrinter versionPrinter = new VersionPrinter();
		versionPrinter.setMajorVersion(4);
		versionPrinter.setMinorVersion(3);
		return versionPrinter;
	}

}
