package com.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.board.domain.BoardVO;
import com.board.domain.Criteria;
import com.board.persistence.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardMapper mapper;

	@Override
	public List<BoardVO> getList() {
		
		return mapper.getList();
		
		//0824 수정
		//return mapper.getListWithPaging();
	}
	
	@Override
	public List<BoardVO> getListWithPaging(Criteria cri){
		return mapper.getListWithPaging(cri); 
	}

	@Override
	public void register(BoardVO board) {
		//사용가능하지만 selectkey 사용해서 넣어보기
		//mapper.insert(board);
		
		//얘도 사용 가능 함
		mapper.insertSelectKey(board);
		
	}

	@Override
	public BoardVO get(Long bno) {
		// TODO Auto-generated method stub
		return mapper.read(bno);
	}

	@Override
	public boolean modify(BoardVO board) {
		//비교연산. 
		//업데이트 결과가 1이면 true 아니면 false 리턴
		return mapper.updateBoard(board)==1;
	}

	@Override
	public boolean delete(Long bno) {
		return mapper.deleteBoard(bno)==1;
	}

	@Override
	public int getTotal(Criteria cri) {
		//글의 수 가져오기
		return mapper.getTotal(cri);
	}

}
