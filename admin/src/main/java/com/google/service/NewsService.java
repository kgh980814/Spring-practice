package com.google.service;

import java.util.List;

import com.google.domain.BoardVO;
import com.google.domain.Criteria;
import com.google.domain.NewsVO;

public interface NewsService {
	
	public List<NewsVO> getList(Criteria cri);
	
	public int getListTotal(Criteria cri);
	
	public void register(NewsVO vo);//insert
	
	//public long insertLastId(BoardVO vo);
	
	public NewsVO get(long bno);//read
	
	public void remove(long bno);//delete

	public void modify(NewsVO vo);//update
	
}
