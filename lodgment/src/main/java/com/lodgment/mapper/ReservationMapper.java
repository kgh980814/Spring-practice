package com.lodgment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lodgment.domain.AccommodationRoom;
import com.lodgment.domain.ReservationVO;


@Mapper
public interface ReservationMapper {

	// 숙소 예약
	void insertReservation(ReservationVO reservation);
	
	
	// 로그인번호 모든 예약정보 가져오기.
	List<ReservationVO> getAllReserveInfo(int userNo);
	

	// 예약번호로 예약정보 가져오기
	ReservationVO getReserveInfoByReserveId(String reservationNo);

	// 객실번호로 객실정보 가져오기.
	AccommodationRoom getRoomDetailByroomNo(int roomNo);

}
