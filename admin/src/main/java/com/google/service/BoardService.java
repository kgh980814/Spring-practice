package com.google.service;

import java.util.List;

import com.google.domain.BoardVO;
import com.google.domain.Criteria;

public interface BoardService {
	
	public List<BoardVO> getList(Criteria cri);
	
	public int getListTotal();
	
	public void register(BoardVO vo);//insert 
	
	//public long insertLastId(BoardVO vo);
	
	public BoardVO get(long bno);//read
	
	public void remove(long bno);//delete
	
	public void modify(BoardVO vo);//update


	
}
