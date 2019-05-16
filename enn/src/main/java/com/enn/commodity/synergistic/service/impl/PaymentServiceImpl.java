package com.enn.commodity.synergistic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enn.commodity.synergistic.dao.PaymentDao;
import com.enn.commodity.synergistic.entity.Payment;
import com.enn.commodity.synergistic.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService{

	@Autowired
	private PaymentDao paymentDao;
	
	@Override
	public List<Payment> SelectPaymentByObject(Payment payment) {
		
		return paymentDao.SelectPaymentByObject(payment);
	}
	
	

}
