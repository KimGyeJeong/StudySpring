package com.board.persistence;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.board.domain.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTest {

	@Autowired
	private BoardMapper mapper;

	// CRUD 기반 테스트
/**
	// 
	@Test
	public void testGetList() {
		List<BoardVO> list = mapper.getList();
		
		for(BoardVO vo : list) {
			log.info(vo);
		}
	}
	
	// 1. Create
	@Test
	public void testInsertBoard() {
		BoardVO board = new BoardVO();
		
		board.setTitle("newTitleTest1");
		board.setContent("newContentTest1");
		board.setWriter("newWriterTest1");
		
		mapper.insert(board);
		
	}

	
	//selectkey 사용하여 insert 해주기
	@Test
	public void testInsertBoard2() {
		BoardVO board = new BoardVO();
		
		board.setTitle("newTitleTest3");
		board.setContent("newContentTest3");
		board.setWriter("newWriterTest3");
		
		//처음에는 void 타입으로 테스트
		//mapper.insertSelectKey(board)
		
		//두번째에는 int받아와서 return 으로 1 받아왔는지 테스트
		//만약 1이 아니라면 실패
		assertThat(mapper.insertSelectKey(board), is(1)); 
		
	}
	
	
	// 2. Read
	@Test
	public void testRead() {
		BoardVO board = mapper.read(6L);
		log.info("************************************************");
		log.info(board.toString());
	}
	
	
	// 3. Delete
	@Test
	public void testdeleteBoard() {
		int result = mapper.deleteBoard(6L);
		
		log.info(result);
		
		assertThat(result, is(1));
	}
	**/
	
	// 4. Update
	@Test
	public void testupdateBoard() {
		BoardVO board = new BoardVO();
		
		board.setBno(8L);
		board.setTitle("titleChangeTest1");
		board.setContent("contentChangeTest1");
		board.setWriter("writerChangeTest1");
		
		assertThat(mapper.updateBoard(board), is(1));
	}
	
}
