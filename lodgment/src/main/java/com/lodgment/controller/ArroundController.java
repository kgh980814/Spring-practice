package com.lodgment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lodgment.criteria.LikeCriteria;
import com.lodgment.domain.Accommodation;
import com.lodgment.domain.User;
import com.lodgment.service.AccommodationService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/near")
@AllArgsConstructor
public class ArroundController {

	@Autowired
	private AccommodationService accommodationService;

	// 숙소 검색 페이지 뷰 반환
	@GetMapping(path = "")
	public String home(@RequestParam(defaultValue = "") String type, @RequestParam(defaultValue = "") String keyword,
			Model model) {

		// 검색 데이터는 restController에서 제공하기 때문에 이 요청핸들러에서 제공하지 않는다.
		// 그 외의 화면에서 필요한 정보를 이 요청핸들러에서 모두 전달한다.

		// type, keyword를 동시에 전달받은 경우 오류화면으로 이동한다.
//		if (!type.isBlank() && !keyword.isBlank()) {
//			// TODO : 오류화면 구현해서 연결시키기 (현재는 임시로 홈화면으로 연결함)
//			return "home";
//		}

		// TODO URL에 잘못된 type명을 적은 경우?

		// 모든 숙소유형 정보 전달.
		// type은 최초 화면 요청할 때 쓰는 이름이고 types는 정보 전달하고, 조건검색으로 선택할 때 쓰는 이름
		model.addAttribute("types", accommodationService.getAllTypes());
		// 모든 지역 정보 전달
		model.addAttribute("cities", accommodationService.getAllCities());

		// type을 전달받은 경우에는 아래 정보도 전달
		if (!type.isBlank()) {
			// 선택한 숙소 유형명 전달
			model.addAttribute("selectedTypeName", accommodationService.getTypeNameById(type));
			// type에 따른 공용시설 옵션 list 전달
			model.addAttribute("cofacilities", accommodationService.getCommonFacilityOptions(type));
			// 객실시설 옵션 list 전달
			model.addAttribute("rofacilities", accommodationService.getRoomFacilityOptions());
			// 기타 태그 옵션 list 전달
			model.addAttribute("tags", accommodationService.getAllAccoTagOptionsByType(type));
		}

		return "accommodation/home";
	}

	// 숙소 상세 페이지 뷰 반환
	@GetMapping(path = "/detail")
	public String detail(@RequestParam("id") int accoId, Model model) {
		// 해당 id를 가진 숙소 상세정보와 리뷰 정보, 찜하기 여부를 전달한다.
		// 숙소의 객실 정보는 restController에서 제공한다.

		// TODO DTO로 객체 하나에 모든 정보 담아서 모델로 전달하기?
		// 숙소 정보
		model.addAttribute("detail", accommodationService.getAccommodationDetailById(accoId));
		// 객실정원 옵션 정보
		model.addAttribute("capacities", accommodationService.getAllCapacityOptionsByAccoId(accoId));

		return "/accommodation/detail";
	}

	@GetMapping(path = "/best")
	public String best(Model model) {
		// 가장 평점이 높은 숙소 상위 5건 조회
		List<Accommodation> bestAccos = accommodationService.getBestAccommodations(5);
		model.addAttribute("bests", bestAccos);
		// 위에서 조회한 숙소의 최신 리뷰 최대 10건 조회

		return "/accommodation/best";
	}

}
