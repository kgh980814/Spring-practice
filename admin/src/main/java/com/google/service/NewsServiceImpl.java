package com.google.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.domain.BoardVO;
import com.google.domain.NewsVO;
import com.google.mapper.BoardMapper;
import com.google.mapper.NewsMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;

@AllArgsConstructor // = @Setter(onMethod_ = { @Autowired })
@Service
public class NewsServiceImpl implements NewsService {

	private NewsMapper mapper;

	@Override
	public List<NewsVO>getList() {
		return mapper.getList();
	}

	@Override
	public void register(NewsVO vo) {
		mapper.insert(vo);

	}

	@Override
	public NewsVO get(long bno) {
		// TODO Auto-generated method stub
		return mapper.read(bno);
	}

	@Override
	public void remove(long bno) {
		// TODO Auto-generated method stub
		mapper.delete(bno);
	}

	@Override
	public void modify(NewsVO vo) {
		// TODO Auto-generated method stub
		mapper.update(vo);
	}

}
