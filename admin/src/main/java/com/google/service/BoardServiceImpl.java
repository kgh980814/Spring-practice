package com.google.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.domain.BoardVO;
import com.google.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor // = @Setter(onMethod_ = { @Autowired })
@Service
public class BoardServiceImpl implements BoardService {

	private BoardMapper mapper;

	@Override
	public List<BoardVO> getList() {
		return mapper.getList();
	}

	@Override
	public void register(BoardVO vo) {
		mapper.insert(vo);

	}

	@Override
	public BoardVO get(long bno) {
		// TODO Auto-generated method stub
		return mapper.read(bno);
	}

	@Override
	public void remove(long bno) {
		// TODO Auto-generated method stub
		mapper.delete(bno);
	}

	@Override
	public void modify(BoardVO vo) {
		// TODO Auto-generated method stub
		mapper.update(vo);
	}

}
