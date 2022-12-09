package com.lodgment.service;

import java.util.List;

import com.lodgment.domain.BoardVO;

public interface BoardService {
	public boolean modify(BoardVO board);

	public boolean remove(Long bno);

	public List<BoardVO> getList();
}
