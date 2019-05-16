package com.enn.commodity.synergistic.dao;

import java.util.List;
import java.util.Map;

import com.enn.commodity.synergistic.entity.MaterialDetail;
import com.enn.commodity.synergistic.entity.PO;
import com.enn.commodity.synergistic.entity.Payment;
import com.enn.commodity.synergistic.entity.PaymentDetail;

public interface PurchaseOrderDao {
	
	List<Payment>SelectPaymentByObject(Payment payment);
	
	List<PO>SelectPOByObject(PO po);
	
	int CreatePO(PO po);
	
	int CreateMaterialDetail(MaterialDetail materialDtail);
	
	int CreatePayment(Payment payment);
	
	int CreatePaymentDetail(PaymentDetail paymentDetail);
	
	List<PO>SelectPOByObjectIn(Map<String,String>map);
	
	int UpdatePO(PO po);
	
	int UpdateMaterialDetail(MaterialDetail materialDtail);
	
	int DeletePO(PO po);
	
	int DeleteMaterialDetail(PO po);
	
	int DeletePayment(PO po);
	
	int DeletePaymentDetail(Payment payment);

}
