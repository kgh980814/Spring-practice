package com.lodgment.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lodgment.domain.AccommodationRoom;
import com.lodgment.domain.PaymentVO;
import com.lodgment.domain.ReservationVO;
import com.lodgment.domain.User;
import com.lodgment.dto.PaymentRequestDTO;
import com.lodgment.mapper.PaymentMapper;
import com.lodgment.mapper.ReservationMapper;





@Service
public class ReservationService {

	@Autowired
	private ReservationMapper reservationMapper;

	@Autowired
	private PaymentMapper paymentMapper;
	/**
	 * 모든 예약정보리스트를 예약번호로 가져오기
	 * @param reservationId 예약번호
	 */
	public List<ReservationVO>  getAllReserveInfo(int userNo){
		return reservationMapper.getAllReserveInfo(userNo);
	}
	
	public List<PaymentVO> getAllPaymentInfo(int userNo){
		return paymentMapper.getAllPaymentInfo(userNo);
	}
	public PaymentVO getPaymentInfo(String reservationNo){
		return paymentMapper.getPaymentInfo(reservationNo);
	}
	
	/**
	 * 예약정보를 예약번호로 가져오기
	 * @param uid_imp 예약번호
	 */
	//모든리스트
	public ReservationVO  getReserveInfoByReserveId(String reservationNo){
		return reservationMapper.getReserveInfoByReserveId(reservationNo);
	}
	// 예약완료
	public List<PaymentVO>  getReadytoReserveInfoByReserveId(int userNo){
		return paymentMapper.getReadytoReserveInfoByReserveId(userNo);
	}
	// 예약취소
	public List<PaymentVO>  getRefundReserveInfoByReserveId(int userNo){
		return paymentMapper.getRefundReserveInfoByReserveId(userNo);
	}

	/**
	 * 결재상태 변경하기 (결제대기 -> 결제완료)
	 * @param payment 결제정보
	 */
	public void updatePaymentStatus(String reservationNo) {
		paymentMapper.updatePaymentStatuts(reservationNo);
	}
	
	/*
	 * 예약정보 저장
	 */
	public void insertReservate(PaymentRequestDTO paymentRequest, User user) {
		ReservationVO reservation = new ReservationVO();
		reservation.setUser(user);
		reservation.setReserName(paymentRequest.getReserName());
		reservation.setReserTel(paymentRequest.getReserTel());
		reservation.setCheckIn(paymentRequest.getCheckIn());
		reservation.setCheckOut(paymentRequest.getCheckOut());
		reservation.setReservationNo(paymentRequest.getMerchantUid());
		reservation.setAccoId(paymentRequest.getAccoId());
		reservation.setRoomNo(paymentRequest.getRoomNo());
		
		PaymentVO payment = new PaymentVO();
		payment.setPaymentId(paymentRequest.getImpUid());
		payment.setPaymentTotalPrice(paymentRequest.getAmount());
		payment.setReservationNo(paymentRequest.getMerchantUid());
		
		System.out.println(reservation);
		reservationMapper.insertReservation(reservation);
		paymentMapper.insertPayment(payment);
		
		
	}
	
	/**
	 * roomDetail 정보 차후 acco서비스로 이동
	 */
	public AccommodationRoom getRoomDetailByroomNo(int roomNo){
		return reservationMapper.getRoomDetailByroomNo(roomNo);
	}
}
