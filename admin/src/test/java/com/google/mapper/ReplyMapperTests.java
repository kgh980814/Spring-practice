package com.google.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.google.domain.Criteria;
import com.google.domain.ReplyVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ReplyMapperTests {
	
	@Setter(onMethod_ = {@Autowired})
	private ReplyMapper mapper;
	
	//@Test
	public void testGet() {
		log.info("ReplyMapper....");
	}
	
	@Test
	public void testInsert() {
		ReplyVO vo = new ReplyVO();
		vo.setBno(3L);
		vo.setReply("댓글내용");
		vo.setReplyer("댓글작성자");
		mapper.insert(vo);
	}
	
	//@Test
	public void testRead() {
		mapper.read(2L);
	}
	//@Test
	public void testRemove() {
		mapper.delete(1L);
	}
	
	//@Test
	public void testModify() {
		ReplyVO vo = new ReplyVO();
		vo.setRno(3L);
		vo.setReply("댓글내용을 수정합니다.");
		mapper.update(vo);
	}
	
	@Test
	public void testList() {
		Criteria cri = new Criteria();
		List<ReplyVO> replies = mapper.getListWithPaging(cri, 3L);
		
		replies.forEach(reply -> log.info(reply));
	}
}





