package com.google.domain;

import lombok.Data;

@Data
public class AttachFileDTO {//첨부된파일을 저장하는

	
	private String fileName;//파일명
	private String uploadPath;//업로드 경로 년/월/일
	private String uuid;//UUID값
	private boolean image;//그림인지 아닌지 여부
}
