package com.board.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Criteria {
	//게시판 사용시 부수적으로 필요한 데이터들 묶음
	
	private int pageNum;	// 페이지 번호
	private int listQty;	//한 페이지에 보여줄 게시물 개수
	
	//0825추가 -- 검색용
	private String sel;	// 검색 조건 T W C TW TC TCW
	private String keyword;	// 검색 키워드
	
	public Criteria() {
		this(1,10);	// 페이지번호 1이고 10개씩 보여주기
	}
	
	public Criteria(int pageNum, int listQty) {
		this.pageNum = pageNum;
		this.listQty = listQty;
	}
	
	//0825추가 -- 검색용. 검색조건을 배열로 만들어 한번에 처리하기 위한 메서드
	public String[] getSelArr() {
		return sel==null ? new String[] {} : sel.split("");	//sel.split("") 모든글자 하나씩 나누어 배열로 저장
	}
	
	//URL 링크에 붙히는 쿼리 스트링 자동 생성해주는 메서드
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.pageNum)
				.queryParam("listQty", this.listQty)
				//0825추가 검색용.
				.queryParam("sel", this.sel)
				.queryParam("keyword", this.keyword)
				;
		
		
		return builder.toUriString();	// ?pageNum=1&listQty=10 라는 패턴으로 문자열을 만들어줌
	}

}
