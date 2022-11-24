package com.google.mapper;

import java.util.List;

import com.google.domain.BoardAttachVO;

public interface BoardAttachMapper {

	public void insert(BoardAttachVO vo);
	
	public void delete(String uuid);
	
	public List<BoardAttachVO>findByBno(Long bno);//해당 글번호로 첨부파일을 찾겠다. 
}
