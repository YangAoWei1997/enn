package com.enn.commodity.synergistic.service;

import java.util.List;

import com.enn.commodity.synergistic.entity.Payment;

public interface PaymentService {
	
	List<Payment>SelectPaymentByObject(Payment payment);

}
