package com.google.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 페이징
 * 
 * @author BigData11
 *
 */
@Setter
@Getter
@ToString
public class Criteria {

	private int pageNum;// 현재 페이지
	private int amount;// 보여줄 페이지
	
	public Criteria() {
		super();
		this.pageNum = 1;
		this.amount = 10;
	}

	public Criteria(int pageNum, int amount) {
		super();
		this.pageNum = pageNum;
		this.amount = amount;
		
	}

	public int getSkip(){
		return (this.pageNum - 1) * this.amount;
	}
}
