package com.lodgment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lodgment.criteria.AccoCriteria;
import com.lodgment.criteria.LikeCriteria;
import com.lodgment.criteria.Pagination;
import com.lodgment.criteria.RoomCriteria;
import com.lodgment.domain.Accommodation;
import com.lodgment.domain.AccommodationRoom;
import com.lodgment.domain.AccommodationType;
import com.lodgment.domain.City;
import com.lodgment.domain.CommonFacility;
import com.lodgment.domain.RoomFacility;
import com.lodgment.domain.User;
import com.lodgment.mapper.AccommodationMapper;


@Service
public class AccommodationService {

	@Autowired
	private AccommodationMapper accommodationMapper;
	
	// 모든 숙소유형 정보를 반환
	public List<AccommodationType>getAllTypes () {
		return accommodationMapper.getAllTypes();
	}
	
	// 모든 지역카테고리를 반환
	public List<City> getAllCities() {
		return accommodationMapper.getAllCities();
	}

	// 해당 숙소유형 아이디를 가진 유형명을 반환
	public String getTypeNameById(String typeId) {
		return accommodationMapper.getTypeNameById(typeId);
	}

	// 해당 숙소유형에 해당하는 공용시설 옵션을 모두 반환
	public List<CommonFacility> getCommonFacilityOptions(String typeId) {
		return accommodationMapper.getCommonFacilitiesByAccoType(typeId);
	}

	// 모든 객실시설 옵션을 반환
	public List<RoomFacility> getRoomFacilityOptions() {
		return accommodationMapper.getAllRoomFacilities();
	}
	
	// 해당 숙소유형에 해당하는, 저장되어있는 모든 숙소 태그를 반환
	public List<String> getAllAccoTagOptionsByType(String typeId) {
		return accommodationMapper.getAllAccoTagsByAccoType(typeId);
	}

	// 저장되어있는 모든 숙소 태그를 반환
	public List<String> getAllAccoTagOptions() {
		return accommodationMapper.getAllAccoTags();
	}
	
	// 검색조건에 맞는 모든 숙소정보를 반환
	public List<Accommodation> searchAccommodation(AccoCriteria criteria) {
		// TODO : currentLat, currentLong, startDate, endDate 서버에서도 null 처리?
		List<Accommodation> accommodations = accommodationMapper.getAccommodationsByCriteria(criteria);
		return accommodations;
	}
	
	// 해당 고유번호(아이디)를 가진 숙소의 상세정보를 반환 (숙소가 가지는 공용시설, 태그 포함)
	public Accommodation getAccommodationDetailById(int accoId) {
		Accommodation accommodation = accommodationMapper.getAccommodationdDetailById(accoId);
		return accommodation;
	}
	
	// 해당 고유번호를 가진 객실의 상세정보를 반환
	public AccommodationRoom getRoomByNo(int roomNo) {
		return accommodationMapper.getRoomByNo(roomNo);
	}
	
	// 해당 객실번호를 가진 숙소, 객실의 이름을 객실 객체에 담아 반환
	public AccommodationRoom getNamesByRoomNo(int roomNo) {
		return accommodationMapper.getNamesByRoomNo(roomNo);
	}
	
	/**
	 * 숙소 번호에 따른 모든 객실정보 조회 (예약 가능 여부 포함)
	 * @param criteria
	 * @return
	 */
	public List<AccommodationRoom> getAllRoomDetailsByAccoId(RoomCriteria criteria) {
		// 요청파라미터로 획득하는 값: 숙소아이디, 검색기간(시작날짜, 종료날짜)
		int accoId = criteria.getAccoId();
		// getAllRoomsByAccoId로 리스트 획득
		List<AccommodationRoom> rooms = accommodationMapper.getAllRoomsByAccoId(accoId);
		// 각 객실 별로 예약 가능 재고 조회해서 객실 객체에 저장
		for (AccommodationRoom room : rooms) {
			// 객실 별 조회를 위해 기준 객체에 객실 번호를 저장해서 전달 (roomNo가 0이어도 조회결과가 없음으로 나와서 0 반환되므로 문제 x)
			// jsp에서는 stock이 양의 정수인 객실만 예약버튼을 활성화시킨다.
			criteria.setRoomNo(room.getNo());
			int stock = accommodationMapper.getAvailableRoomStock(criteria);
			room.setStock(stock);
		}
		return rooms;
	}

	/**
	 * 숙소 번호에 따른 객실정보를 페이징 번호에 맞게 조회 (예약 가능 여부 포함)
	 * @param criteria
	 * @return
	 */
	public List<AccommodationRoom> getRoomDetailsByAccoIdwithPagination(RoomCriteria criteria) {
		// 요청파라미터로 획득하는 값: 숙소아이디, 검색기간(시작날짜, 종료날짜), 현재페이지번호
		// pagination 객체 획득
		Pagination pagination = generatePagination(criteria);
		// pagination의 beginIndex, endIndex 값 사용해서 해당 페이지에 맞는 정보만 조회
		criteria.setBeginIndex(pagination.getBeginIndex());
		criteria.setEndIndex(pagination.getEndIndex());
		List<AccommodationRoom> rooms = accommodationMapper.getRoomsByAccoIdwithPagination(criteria);
		// 각 객실 별로 예약 가능 재고 조회해서 객실 객체에 저장
		for (AccommodationRoom room : rooms) {
			// 객실 별 조회를 위해 기준 객체에 객실 번호를 저장해서 전달 (roomNo가 0이어도 조회결과가 없음으로 나와서 0 반환되므로 문제 x)
			// jsp에서는 stock이 양의 정수인 객실만 예약버튼을 활성화시킨다.
			criteria.setRoomNo(room.getNo());
			int stock = accommodationMapper.getAvailableRoomStock(criteria);
			room.setStock(stock);
		}
		return rooms;
	}
	
	/**
	 * 해당 번호를 가진 숙소의 모든 객실정원 옵션을 반환한다.
	 * @param accoId
	 * @return
	 */
	public List<Integer> getAllCapacityOptionsByAccoId(int accoId) {
		return accommodationMapper.getAllRoomCapacitiesByAccoId(accoId);
	}
	
	/**
	 * 해당 번호를 가진 사용자의, 해당 아이디를 가진 숙소에 대한 찜하기 상태를 변경한다.
	 * USER_ACCOMMODATION_LIKES 테이블에 해당사용자의 해당숙소 찜하기 정보가 있으면 삭제하고, 없으면 추가한다.
	 * 변경에 따라 해당 숙소의 찜하기 개수 정보를 업데이트 한다.
	 * 트랜잭션 처리를 통해 중간에 오류가 발생하면 모든 DB 변경사항을 롤백시킨다.
	 * @param userNo
	 * @param accoId
	 */
	@Transactional
	public void changeMyAccoLikeStatus(User loginUser, int accoId) {
		int userNo = loginUser.getNo();
		LikeCriteria criteria = new LikeCriteria(userNo, accoId);
		
		// 숙소 찜 개수 정보를 업데이트 하기 위해 숙소 객체 획득
		// **** update할 때 다른 컬럼에 null값 들어가지 않도록 할 것!! **** 
		Accommodation acco = accommodationMapper.getAccommodationById(accoId);
		int likeCount = acco.getLikeCount();
		
		// isExistUserLikeByAccoId(param)는 존재하면 1을, 존재하지 않으면 0을 반환한다.
		if (isLikedAcco(criteria)) {
			 accommodationMapper.deleteUserLikeByAccoId(criteria);
			 likeCount--;
		} else {
			accommodationMapper.insertUserLikeByAccoId(criteria);
			likeCount++;
		}
		
		if (likeCount < 0) {
			//값을 변경한 likeCount가 음수이면 잘못된 정보가 들어가게 되므로 예외를 발생시킨다.
			throw new RuntimeException("숙소의 찜 개수는 음수가 될 수 없습니다.");
		}
		
		// 찜하기 상태 변경을 반영한 숙소 정보 업데이트
		acco.setLikeCount(likeCount);
		accommodationMapper.updateAccommodation(acco);
	}
	
	/**
	 * 해당 사용자가 해당 숙소를 찜하기 눌렀는지 여부를 조회해 boolean타입의 값으로 반환한다.
	 * @param criteria {userNo=사용자번호, id=숙소아이디}가 담긴 객체
	 * @return
	 */
	public boolean isLikedAcco(LikeCriteria criteria) {
		return accommodationMapper.isExistUserLikeByAccoId(criteria) == 1 ? true : false;
	}
	
	/**
	 * 리뷰 평점이 높은 숙소 정보를 상위 n건 조회해서 반환한다.
	 * @param count
	 * @return
	 */
	public List<Accommodation> getBestAccommodations(int count) {
		return accommodationMapper.getBestAccommodations(count);
	}

	/**
	 * 숙소의 객실 정보 행 수를 조회해서, 현재 페이지에 대한 pagination 객체를 반환한다.
	 * @param accoId
	 * @param currentPage
	 * @return
	 */
	public Pagination generatePagination(RoomCriteria criteria) {
		int totalRows = accommodationMapper.getTotalRowsOfRoomsByAccoId(criteria);
		Pagination pagination = new Pagination(totalRows, criteria.getCurrentPage());
		// 페이징에 필요한 값들 초기화
		pagination.init();
		return pagination;
	}
	
	/**
	 * 해당 사용자가 찜하기를 누른 모든 숙소를 반환한다.
	 * @param userNo
	 * @return
	 */
	public List<Accommodation> getAllLikedItemsByUser(int userNo) {
		return accommodationMapper.getAllLikedAccoByUserNo(userNo);
	}
	
}
