package com.lodgment.domain;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PaymentVO {

	private String paymentId;
	private String paymentStatus;
	private int paymentTotalPrice;
	private String reservationNo;
	
	private ReservationVO reservation;
	private User user;
}
