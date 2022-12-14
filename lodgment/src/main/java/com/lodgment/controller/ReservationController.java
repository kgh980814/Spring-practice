package com.lodgment.controller;




import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lodgment.annotation.LoginUser;
import com.lodgment.criteria.RoomCriteria;
import com.lodgment.domain.User;
import com.lodgment.dto.PaymentRequestDTO;
import com.lodgment.service.AccommodationService;
import com.lodgment.service.LoginedUserService;
import com.lodgment.service.ReservationService;
import com.siot.IamportRestClient.exception.IamportResponseException;



@Controller
public class ReservationController {

	@Autowired
	LoginedUserService loginedUserService;
	@Autowired
	ReservationService reservationService;
	@Autowired
	AccommodationService accommodationService;
	/*
	 * 예약페이지 요청처리 / 상세예약페이지에서 요청처리 옴과 동시의 데이터베이스에서 예약상태를 '결제대기'상태로 변경할것
	 * 요청URI : /reservation
	 * 뷰 페이지 : /WEB-INF/views/home.jsp
	 */
	@GetMapping(path = "/reservation")
	public String reservation(@LoginUser User user,RoomCriteria criteria, @RequestParam(name ="id")Integer accoId,@RequestParam(name ="duration")Integer duration, @RequestParam(name="roomno")Integer roomNo, @RequestParam(name ="checkin" ,required=false) String checkIn, @RequestParam(name ="checkout") String checkOut ,
	Model model, PaymentRequestDTO paymentRequest) {
		model.addAttribute("user", user);
		model.addAttribute("id", accoId);
		model.addAttribute("roomno", roomNo);
		model.addAttribute("checkin", checkIn);
		model.addAttribute("checkout", checkOut);
		model.addAttribute("duration", duration);
		
		//상세페이지에서 온 해당 id를 가지고 숙소정보 가져오기.
		model.addAttribute("acco", accommodationService.getAccommodationDetailById(accoId));
		model.addAttribute("room", reservationService.getRoomDetailByroomNo(roomNo));
		// 체크인 체크아웃 데이터 가져오기...?
		
		return "reservation/reservation";
	}
	
	@PostMapping("/reservation/complete")
	public String paymentinfo(PaymentRequestDTO paymentRequest, @LoginUser User user) throws IamportResponseException, IOException{
		reservationService.insertReservate(paymentRequest, user);
		
		return "redirect:/user/information?cat=CAT_002";
	}
	
	@GetMapping("/reservation/refund")
	public String reservationRefund(@LoginUser User user, @RequestParam(name="reservationNo") String reservationNo) {
		reservationService.updatePaymentStatus(reservationNo);
		
		return "redirect:/user/information?cat=CAT_002";
	}
	
	/*
	 * 예약취소기능이 있는 나의 상세페이지 요청
	 * 요청URI : /myreservation
	 * 뷰 페이지 : /WEB-INF/views/myreservation.jsp
	 * 마이페이지의 나의 상세예약정보페이지 요청 추후 마이페이지에 보낼예정
	 */
	@GetMapping(path = "/myreservation")
	public String myReservation(@LoginUser User user ,@RequestParam(name="reservationNo")String reservationNo ,Model model) {
		
		model.addAttribute("payment", reservationService.getPaymentInfo(reservationNo));
		return "reservation/myreservation";
	}

	
}
