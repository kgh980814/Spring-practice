package com.google.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
	/**
	 * 년/월/일 폴더로 생성
	 * @return
	 */
	public static String getFolder() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(new Date());
		return str.replace("-",File.separator);
	}
	
	/**
	 *  그림파일이면 true, 아니면 false
	 * @param file
	 * @return
	 */
	public static boolean checkImageType(File file) {//파일이 이미지인지 아닌지 체크
		try {
			String contentType = Files.probeContentType(file.toPath());
			return contentType.startsWith("image");// image/
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	


}
