package com.lodgment.dto;

import org.apache.ibatis.type.Alias;

import com.lodgment.domain.Category;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 카테고리 유형을 표현하는 객체
 * @author 82102
 *
 */
@NoArgsConstructor
@Getter
@Setter
@ToString
@Alias("QaCategory")
public class QaCategoryDTO {

	private String qaCatId;
	private Category category;
	
	public QaCategoryDTO(String qaCatId, String qaCatName) {
		this.qaCatId = qaCatId;
		this.category = new Category(qaCatName);
	}
}
