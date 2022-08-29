package com.member.domain;

import java.sql.Timestamp;
import java.util.List;

import lombok.Data;

@Data
public class MemberVO {

	private String id;
	private String pw;
	private String name;
	private String email;
	private String gender;
	private Timestamp reg;
	private String enabled;	//활동중 "1", 비활동중 "0"
	//권한 정보 저장. 권한(여러개) 저장 가능한 변수. 하나만 저장할거면 컬럼만 추가해도 괜찮지만 확장성을 위해 미리 분류해서 list 로 만들어 둠.
	private List<AuthVO> authList;
	
	
}
