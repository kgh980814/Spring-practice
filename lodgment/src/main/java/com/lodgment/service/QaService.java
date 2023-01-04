package com.lodgment.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.lodgment.domain.QaVO;
import com.lodgment.dto.QaCategoryDTO;
import com.lodgment.dto.QaDTO;
import com.lodgment.dto.QaTypeDTO;
import com.lodgment.mapper.QaMapper;

@Service
public class QaService {
	
	// 문의사항 이미지 저장 디렉토리
	@Value("${seoul.qa.image.save-directory}")
	String qaImageSaveDirectory;

	@Autowired
	private QaMapper qaMapper;
	
	public void addNewQa(QaDTO qaDto) throws IOException{
		// 음식점 이미지 사진 업로드
		if (!qaDto.getImageFile().isEmpty()) {
			MultipartFile imageFile = qaDto.getImageFile();
			String filename = imageFile.getOriginalFilename();
			qaDto.setImage(filename);
			
			InputStream in = imageFile.getInputStream();
			FileOutputStream out = new FileOutputStream(new File(qaImageSaveDirectory, filename));
			
			FileCopyUtils.copy(in, out);
		}
		qaMapper.insertQa(qaDto);
	}
	
	/**
	 * 사용자가 작성한 모든 문의사항을 반환한다.
	 * @return 문의사항
	 */
	public List<QaVO> getAllQas() {
		return qaMapper.getAllQas();
	}
	
	public List<QaCategoryDTO> getAllQaCategory() {
		return qaMapper.getAllQaCategories();
	}
	
	public List<QaTypeDTO> getAllQaTypes() {
		return qaMapper.getAllQaTypes();
	}

	public List<QaDTO> getUserQa(int userNo) {
		return qaMapper.getUserQa(userNo);
	}

	public QaDTO getQaByNo(int no) {
		System.out.println("서비스 입력"+no);
		System.out.println("서비스 호출"+qaMapper.getUserQa(no));
		return qaMapper.getQaByNo(no);
	}
	
	public QaDTO getUserQaByQaNo(int no) {
		System.out.println("서비스 입력"+no);
		System.out.println("서비스 호출"+qaMapper.getUserQa(no));
		return qaMapper.getUserQaByQaNo(no);
	}
}
