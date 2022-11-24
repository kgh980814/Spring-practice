package com.google.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.domain.BoardAttachVO;
import com.google.domain.BoardVO;
import com.google.domain.Criteria;
import com.google.mapper.BoardAttachMapper;
import com.google.mapper.BoardMapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {
	
	
	private BoardMapper mapper;
	
	private BoardAttachMapper attachmapper;

	@Override
	public List<BoardVO> getList(Criteria cri) {
		return mapper.getListWithPaging(cri);
	}

	@Override
	public int getListTotal(Criteria cri) {
		return mapper.getListTotal(cri);
	}
	
	@Transactional
	@Override
	public void register(BoardVO vo) {
		mapper.insertLastId(vo);
		
		if(vo.getAttachList() == null || vo.getAttachList().size() <=0 ) {
			return; //빈값이거나 0보다작으면 수행을 멈춤.
		}
		vo.getAttachList().forEach(attach -> {
			attach.setBno(vo.getBno());
			attachmapper.insert(attach);
		});
	}
	@Transactional
	@Override
	public BoardVO get(long bno) {
		mapper.updateHit(bno);//조회수 증가
		return mapper.read(bno);
	}

	@Override
	public void remove(long bno) {
		mapper.delete(bno);		
	}

	@Override
	public void modify(BoardVO vo) {
		mapper.update(vo);
	}

	@Override
	public List<BoardAttachVO> getAttachList(long bno) {
		
		return attachmapper.findByBno(bno);//글번호에 따른 첨부파일 목록
	}


}
