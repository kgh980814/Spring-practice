package com.lodgment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.lodgment.domain.BoardVO;

public interface BoardMapper {
	//@Select("select * from lod_board where bno > 0")
	public List<BoardVO> getList();

	public void insert(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int update(BoardVO board);
	
	public int delete(Long bno);
	
}
