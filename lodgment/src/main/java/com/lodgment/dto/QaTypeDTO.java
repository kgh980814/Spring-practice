package com.lodgment.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 문의유형을 표현하는 객체
 * @author 82102
 *
 */
@Getter
@Setter
@ToString
public class QaTypeDTO {

	private String qaId;
	private String qaType;
}
