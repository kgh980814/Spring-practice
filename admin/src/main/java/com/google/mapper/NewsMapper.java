package com.google.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.google.domain.BoardVO;
import com.google.domain.NewsVO;

public interface NewsMapper {
	//@Select("SELECT * FROM tbl_board ORDER BY bno DESC")
	public List<NewsVO> getList();
	
	public void insert(NewsVO vo);
	
	public long insertLastId(NewsVO vo);
	
	public NewsVO read(long bno);
	
	public void delete(long bno);
	
	public void update(NewsVO vo);
}
