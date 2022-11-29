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
	
	private BoardAttachMapper attachMapper;

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
			attachMapper.insert(attach);
		});
	}
	@Transactional
	@Override
	public BoardVO get(long bno) {
		mapper.updateHit(bno);//조회수 증가
		return mapper.read(bno);
	}
	
	@Transactional
	@Override
	public boolean remove(long bno) {
		//attach(첨부파일)mapper를 먼저 지우고 boardmapper를 지움
		
		attachMapper.deleteAll(bno);
		
		return mapper.delete(bno) == 1;		
	}

	@Override
	public void modify(BoardVO vo) {
		mapper.update(vo);
		
		
		//첨부파일 테이블내용 삭제
		if(vo.getAttachList() == null || vo.getAttachList().size() <=0 ) {
			return; //빈값이거나 0보다작으면 수행을 멈춤.
		}
		attachMapper.deleteAll(vo.getBno());
		
		vo.getAttachList().forEach(attach -> {
			attach.setBno(vo.getBno());
			attachMapper.insert(attach);
		});
		
	}

	@Override
	public List<BoardAttachVO> getAttachList(long bno) {
		
		return attachMapper.findByBno(bno);//글번호에 따른 첨부파일 목록
	}


}
