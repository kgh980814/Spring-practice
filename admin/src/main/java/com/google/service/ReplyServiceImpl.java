package com.google.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.google.domain.Criteria;
import com.google.domain.ReplyPageDTO;
import com.google.domain.ReplyVO;
import com.google.mapper.ReplyMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class ReplyServiceImpl implements ReplyService{
	
	@Setter(onMethod_ = {@Autowired})
	private ReplyMapper mapper;
	
	@Override
	public int insert(ReplyVO vo) {
		
		return mapper.insert(vo);
	}

	@Override
	public ReplyVO read(Long rno) {
		
		return mapper.read(rno);
	}

	@Override
	public int delete(Long rno) {
		
		return mapper.delete(rno);
	}

	@Override
	public int update(ReplyVO vo) {
		
		return mapper.update(vo);
	}

	@Override
	public ReplyPageDTO getListWithPaging(Criteria cri, Long bno) {
	
		return new ReplyPageDTO(
				mapper.getCountByBno(bno)
				, mapper.getListWithPaging(cri, bno));
	}

}
