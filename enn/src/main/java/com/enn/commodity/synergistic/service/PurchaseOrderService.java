package com.enn.commodity.synergistic.service;

import java.util.List;
import java.util.Map;

import com.enn.commodity.synergistic.entity.MaterialDetail;
import com.enn.commodity.synergistic.entity.PO;
import com.enn.commodity.synergistic.entity.Payment;
import com.enn.commodity.synergistic.entity.PaymentDetail;

import net.sf.json.JSONObject;


public interface PurchaseOrderService {
	
//	public JSONObject CreateSalesOrder(String SalesOrder);
//	
//	public JSONObject QuerySalesOrder(String SalesOrderId,String UserOrgId);
//	
//	public JSONObject QueryPurchaseOrder(String PurchaseOrderId,String UserOrgId);
//	
//	public JSONObject CreatePurchaseOrder(String PurchaseOrder);
//	
//	public JSONObject SelectSalesOrder(String BuyerId,String UserOrgId);
//	
//	public JSONObject SelectPurchaseOrderOfSaler(String BuyerId,String UserOrgId);
//	
//	public JSONObject SelectPurchaseOrderOfBuyer(String SellerId,String UserOrgId);
//	
//	public JSONObject QueryStatusOfPO(String PurchaseOrderId,String SalesOrderId,String UserOrgId);
//	
//	public JSONObject ComparePO(String PurchaseOrderId,String SalesOrderId,String ContractNumber,String UserOrgId);

    public String CreateSalesOrder(JSONObject requestJson);
	
	public String QuerySalesOrder(JSONObject requestJson);
	
	public String QueryPurchaseOrder(JSONObject requestJson);
	
	public String CreatePurchaseOrder(JSONObject requestJson);
	
	public String SelectSalesOrder(JSONObject requestJson);
	
	public String SelectPurchaseOrderOfSaler(JSONObject requestJson);
	
	public String SelectPurchaseOrderOfBuyer(JSONObject requestJson);
	
	public String QueryStatusOfPO(JSONObject requestJson);
	
	public String ComparePO(JSONObject requestJson);
	
	List<Payment>SelectPaymentByObject(Payment payment);
	
	List<PO>SelectPOByObject(PO po);
	
    JSONObject CreatePO(PO po);
	
    List<PO>SelectPOByObjectIn(Map<String,String>map);
    
    JSONObject UpdatePO(PO po);
    
    JSONObject DeletePO(PO po);
    
    
	
	
}
