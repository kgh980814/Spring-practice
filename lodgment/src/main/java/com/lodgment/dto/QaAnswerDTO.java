package com.lodgment.dto;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 문의게시판 답변 저장을 위한 vo
 * @author USER
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@Alias("QaAnswer")
public class QaAnswerDTO {

	private int qaNo;
	private int answerNo;
	private String content;
	
	public QaAnswerDTO(int qaNo, String content) {
		this.qaNo = qaNo;
		this.content = content;
	}
}
