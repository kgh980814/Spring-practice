package com.google.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class PageDTO {

	private int startPage;//시작페이지
	private int endPage;//끝 페이지
	private boolean prev, next; //이전, 다음 여부
	
	private int total;//전체 글수
	private Criteria cri;//페이징
	
	public PageDTO(Criteria cri) {
		super();
		this.cri = cri;
	}
	
	
	public PageDTO(Criteria cri, int total) {
		super();
		this.total = total;
		this.cri = cri;
		//끝 페이지 번호
		this.endPage = (int)(Math.ceil(cri.getPageNum() / 10.0)) * 10;
		this.startPage = this.endPage - 9;
		int realEnd = (int)(Math.ceil((total * 1.0) / cri.getAmount()));
		
		if(realEnd < this.endPage) {
			this.endPage = realEnd;
		}
		
		this.prev = this.startPage > 1;
		this.next = this.endPage < realEnd;
	}




	
	
}
