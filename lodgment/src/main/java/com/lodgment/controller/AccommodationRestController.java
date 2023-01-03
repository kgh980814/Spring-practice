package com.lodgment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lodgment.criteria.AccoCriteria;
import com.lodgment.criteria.Pagination;
import com.lodgment.criteria.RoomCriteria;
import com.lodgment.domain.Accommodation;
import com.lodgment.domain.AccommodationRoom;
import com.lodgment.service.AccommodationService;


/**
 * 숙소 관련 요청에 대하여 데이터를 json 형식의 응답으로 반환하는 컨트롤러이다.
 * @author doyoung
 *
 */
@RestController
public class AccommodationRestController {
	
	@Autowired
	private AccommodationService accommodationService;
	
	
	// 검색 조건에 맞는 숙소 리스트 반환
	@GetMapping(path = "/accommodations")
	public List<Accommodation> accommodations(AccoCriteria criteria) {
		return accommodationService.searchAccommodation(criteria);
	}
	
	// 검색 조건(날짜)에 맞는 객실 리스트 반환
	@GetMapping(path = "/rooms")
	public List<AccommodationRoom> rooms(RoomCriteria roomCriteria) {
		// TODO pagination 정보 같이 제공하기
		return accommodationService.getRoomDetailsByAccoIdwithPagination(roomCriteria);
	}
	
	// 객실 정보에 대한 pagination 객체 반환
	@GetMapping(path = "/rooms/pagination")
	public Pagination pagination(RoomCriteria criteria) {
		return accommodationService.generatePagination(criteria);
	}
	

}
