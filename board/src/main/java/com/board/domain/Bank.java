package com.board.domain;

import lombok.Data;

@Data
public class Bank {
	
	private int accountNo;		//계좌 번호
	private String name;		//계좌주 명
	private String bankName;	//은행 명

}
