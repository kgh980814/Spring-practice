package com.google.domain;

import java.util.Date;

import lombok.Data;

@Data
public class NewsVO {

	private Long bno;
	private String title;
	private String content;
	private String writer;
	private Date regdate;
	private Date updateDate;
	private int hit;//조회수
	private int replyCnt;// 댓글수
}
