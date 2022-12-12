package com.lodgment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lodgment.domain.BoardVO;
import com.lodgment.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
@Service
@AllArgsConstructor
@Log4j
public class BoardServiceImpl implements BoardService{
@Setter(onMethod_ = @Autowired)
private BoardMapper mapper;

	@Override
	public boolean modify(BoardVO board) {
		log.info("modify..."+board);
		return mapper.update(board) == 1;
	}

	@Override
	public boolean remove(Long bno) {
		log.info("remove.."+bno);
		return mapper.delete(bno) == 1;
	}

	@Override
	public List<BoardVO> getList() {
		// TODO Auto-generated method stub
		return mapper.getList();
	}

	@Override
	public void register(BoardVO board) {
		// TODO Auto-generated method stub
		log.info("register"+board);
	}

	@Override
	public BoardVO get(Long bno) {
		log.info("get....."+ bno);
		return mapper.read(bno);
	}
	
	

}
