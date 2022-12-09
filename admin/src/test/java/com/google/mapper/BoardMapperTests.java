package com.google.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.domain.BoardVO;
import com.google.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	
	@Setter(onMethod_ = {@Autowired})
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
		mapper.getList().forEach(board->log.info(board));
	}
	
	//@Test
	public void testGetListWithPaging() {
		Criteria cri = new Criteria(1, 20);
		cri.setType("TCW");
		cri.setKeyword("새글");
		
		mapper.getListWithPaging(cri).forEach(board->log.info(board));
	}
	
	//@Test
	public void testGetListTotal() {
		Criteria cri = new Criteria(1, 20);
		cri.setType("T");
		cri.setKeyword("새글");
		
		int total = mapper.getListTotal(cri);
	}
	
	//@Test
	public void testInsert() {
		BoardVO vo = new BoardVO();
		vo.setTitle("새글제목");
		vo.setContent("새글내용");
		vo.setWriter("user00");
		mapper.insert(vo);
	}
	
	//@Test
	public void testInsertLastId() {
		BoardVO vo = new BoardVO();
		vo.setTitle("새글제목");
		vo.setContent("새글내용");
		vo.setWriter("user00");
		mapper.insertLastId(vo);
		log.info(vo);
	}
	
	//@Test
	public void testRead() {
		mapper.read(38);
	}
	
	//@Test
	public void testDelete() {
		mapper.delete(39);
	}
	
	//@Test
	public void testUpdate() {
		BoardVO vo = new BoardVO();
		vo.setBno(38L);
		vo.setTitle("변경 제목");
		vo.setContent("변경 내용");
		
		mapper.update(vo);
	}
}





