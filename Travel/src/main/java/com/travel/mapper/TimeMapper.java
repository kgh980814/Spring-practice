package com.travel.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	@Select("SELECT curdate() ")//sql가 제대로 작동하는지 확인할 예제 sql문
	public String getTime();//현재나오는 날짜를 문자로 출력
	
	public String getTime2(); 
	
	public String getMember();//big_member테이블 가져오기.
}
