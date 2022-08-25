package com.board.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PageDTO {
	//페이지 번호등 페이징 처리시 필요한 정보 담는 클래스
	
	private int startPage;
	private int endPage;
	
	//페이지 번호 앞으로가기, 뒤로가기 활성화 여부
	private boolean prev, next;

	//전체 글의 개수
	private int total;
	
	//페이지 번호 pageNum, 페이지당 보여줄 글의 개수 listQty
	private Criteria cri;
	
	public PageDTO(Criteria cri, int total) {
		this.cri = cri;
		this.total = total;
		
		// 한 페이지에 페이지 번호 10개 띄우는 기준으로 계산
		//ceil -> 올림?
		this.endPage = (int)(Math.ceil(cri.getPageNum()/10.0)*10);
		this.startPage = this.endPage - 9;
		
		//만약 글의 갯수가 123개면 13페이지가 나와야 함. 계산 식.
		int realEnd = (int)(Math.ceil((total*1.0)/cri.getListQty()));
		
		if(realEnd < this.endPage) {
			//전체 페이지수보다 endPage가 크면
			//마지막 페이지번호를 전체페이지로 변경
			this.endPage = realEnd;
		}
		
		//startPage 에 들어가는 수는 1, 11, 21 ...
		// prev를 보여주기 위해서 1보다 큰수들만 true 를 줌
		//1페이지에서는 굳이 prev 를 활성화 할 이유가 없기 때문 (?)
		this.prev = this.startPage > 1;
		
		//endpage가 realend보다 작을경우 다음페이지로 갈수 있는 next가 활성화 대야하므로
		// true를 돌려줌
		this.next = this.endPage<realEnd;
	}
}
