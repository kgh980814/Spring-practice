package com.google.domain;

import org.springframework.web.util.UriComponentsBuilder;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 페이징
 * @author BigData00
 *
 */
@Setter
@Getter
@ToString
public class Criteria {

	private int pageNum;//현재페이지
	private int amount; //보여줄 페이지 수
	
	private String type;//검색조건
	private String keyword;//검색어
	
	public Criteria() {
		this(1, 10);
		/*
		 * this.pageNum = 1; this.amount = 10;
		 */
	}

	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
	}
	
	public int getSkip() {
		return (this.pageNum -1 ) * this.amount;
	}
	
	public String[] getTypeArr() {
		return type == null?new String[] {}:type.split("");
	}
	/**
	 * 삭제시 현재페이지 및 검색어 유지
	 * ?pageNum=?&amount=10&type=&keyword=
	 * UriComponentsBuilder
	 */
	public String getListLink() {
		UriComponentsBuilder builder = UriComponentsBuilder.fromPath("")
				.queryParam("pageNum", this.getPageNum())
				.queryParam("amount", this.getAmount())
				.queryParam("type", this.getType())
				.queryParam("keyword", this.getKeyword());
		return builder.toUriString();
		
	}
}
