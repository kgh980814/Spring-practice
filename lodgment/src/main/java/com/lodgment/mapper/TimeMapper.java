package com.lodgment.mapper;

import org.apache.ibatis.annotations.Select;

public interface TimeMapper {
	@Select("SELECT now()")
	public String getTime();
}
