package com.google.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.google.domain.BoardVO;
import com.google.domain.Criteria;

public interface BoardMapper {
	// @Select("SELECT * FROM tbl_board ORDER BY bno DESC")
	public List<BoardVO> getList();

	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public int getListTotal();//페이지 총수

	public void insert(BoardVO vo);

	public long insertLastId(BoardVO vo);

	public BoardVO read(long bno);

	public void delete(long bno);

	public void update(BoardVO vo);

	public int updateHit(long bno);
}
