package com.google.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReplyPageDTO {
	
	private int replyCnt;//댓글총수
	private List<ReplyVO> list;

}
