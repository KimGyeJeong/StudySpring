package com.board.service;

import java.util.List;

import com.board.domain.ReplyVO;

public interface ReplyService {
	
	//댓글 추가
	public int add(ReplyVO vo);
	//댓글 한개 조회
	public ReplyVO getReply(Long rno);
	//댓글 수정
	public int modifyReply(ReplyVO vo);
	//댓글 삭제
	public int deleteReply(Long rno);
	//댓글 목록 조회 -- 페이징처리 x TODO 나중에 페이징처리 된것 만들어보기
	public List<ReplyVO> getList(Long bno);

}
