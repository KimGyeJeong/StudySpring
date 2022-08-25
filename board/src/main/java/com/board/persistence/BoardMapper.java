package com.board.persistence;

import java.util.List;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;

public interface BoardMapper {
	
	//전체 레코드 검색 
	public List<BoardVO> getList();	//페이징 처리x
	
	//페이징 처리용 0824 추가
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	//글 저장 (Create)
	public void insert(BoardVO board);
	
	public int insertSelectKey(BoardVO board);
	
	//글 읽기 (Read)
	public BoardVO read(Long bno);
	
	//글 삭제 (Delete)
	public int deleteBoard(Long bno);
	
	//글 수정 (Update)
	public int updateBoard(BoardVO board);
	
	//글의 수 가져오기
	public int getTotal(Criteria cri);
	
}
