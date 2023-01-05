package com.lodgment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.lodgment.domain.PaymentVO;



@Mapper
public interface PaymentMapper {

	// 결제 상태변경하기 (결제대기 / 결제완료)
	void updatePaymentStatuts(String reservationNo);
	
	// 결제정보 가져오기
	List<PaymentVO> getAllPaymentInfo(int userNo);
	PaymentVO getPaymentInfo(String reservationNo);

	// 예약완료된 예약정보가져오기
	List<PaymentVO> getReadytoReserveInfoByReserveId(int userNo);
	
	// 예약취소된 예약정보가져오기
	List<PaymentVO> getRefundReserveInfoByReserveId(int userNo);
	
	// 결제 정보 저장하기
	void insertPayment(PaymentVO payment);
	
}
