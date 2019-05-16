package com.enn.commodity.synergistic.dao;

import java.util.List;

import com.enn.commodity.synergistic.entity.Payment;

public interface PaymentDao {
	
	List<Payment>SelectPaymentByObject(Payment payment);

}
