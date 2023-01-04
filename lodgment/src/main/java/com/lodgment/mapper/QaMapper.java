package com.lodgment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lodgment.domain.QaVO;
import com.lodgment.dto.QaCategoryDTO;
import com.lodgment.dto.QaDTO;
import com.lodgment.dto.QaTypeDTO;



@Mapper
public interface QaMapper {

	void insertQa(QaDTO qaDto);							// 작성 문의사항 등록하기
	List<QaVO> getAllQas();								// 작성한 모든 문의사항 가져오기
	List<QaCategoryDTO> getAllQaCategories(); 				// 모든 문의사항 카테고리
	List<QaTypeDTO> getAllQaTypes();						// 모든 문의사항 유형
	List<QaDTO> getUserQa(int userNo);					// 사용자의 문의사항 검색
	QaDTO getQaByNo(int no);							// 상세페이지 출력을 위해 글번호로 글 조회
	QaDTO getUserQaByQaNo(int no);						// 상세페이지 출력을 위해 글번호로 글 조회
}
