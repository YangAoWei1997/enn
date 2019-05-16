package com.enn.commodity.synergistic.entity;

import java.util.List;

public class Payment {
	
	private String id;
	private String name;
	private String version;
	private String comments;
	private String organizationId;
	private String organizationName;
	private String userId;
	private String userName;
	private String privateId;
	private String dataType;
	private String goodsType;
	
	private List<SecureSet> secureSet;
	
	private String payerId;
	private String payerName;
	private String payerBankOfDeposit;
	private String payerAccountId;
	private String payeeId;
	private String payeeName;
	private String payeeBankOfDeposit;
	private String payeeAccountId;
	private String payType;
	private String reason;
	private String customerPaymentId;
	
	private String poId; 
	
	private PaymentDetail paymentdetail;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(String organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPrivateId() {
		return privateId;
	}

	public void setPrivateId(String privateId) {
		this.privateId = privateId;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getGoodsType() {
		return goodsType;
	}

	public void setGoodsType(String goodsType) {
		this.goodsType = goodsType;
	}

	public List<SecureSet> getSecureSet() {
		return secureSet;
	}

	public void setSecureSet(List<SecureSet> secureSet) {
		this.secureSet = secureSet;
	}

	public String getPayerId() {
		return payerId;
	}

	public void setPayerId(String payerId) {
		this.payerId = payerId;
	}

	public String getPayerName() {
		return payerName;
	}

	public void setPayerName(String payerName) {
		this.payerName = payerName;
	}

	public String getPayerBankOfDeposit() {
		return payerBankOfDeposit;
	}

	public void setPayerBankOfDeposit(String payerBankOfDeposit) {
		this.payerBankOfDeposit = payerBankOfDeposit;
	}

	public String getPayerAccountId() {
		return payerAccountId;
	}

	public void setPayerAccountId(String payerAccountId) {
		this.payerAccountId = payerAccountId;
	}

	public String getPayeeId() {
		return payeeId;
	}

	public void setPayeeId(String payeeId) {
		this.payeeId = payeeId;
	}

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getPayeeBankOfDeposit() {
		return payeeBankOfDeposit;
	}

	public void setPayeeBankOfDeposit(String payeeBankOfDeposit) {
		this.payeeBankOfDeposit = payeeBankOfDeposit;
	}

	public String getPayeeAccountId() {
		return payeeAccountId;
	}

	public void setPayeeAccountId(String payeeAccountId) {
		this.payeeAccountId = payeeAccountId;
	}

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		this.payType = payType;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getCustomerPaymentId() {
		return customerPaymentId;
	}

	public void setCustomerPaymentId(String customerPaymentId) {
		this.customerPaymentId = customerPaymentId;
	}

	public PaymentDetail getPaymentdetail() {
		return paymentdetail;
	}

	public void setPaymentdetail(PaymentDetail paymentdetail) {
		this.paymentdetail = paymentdetail;
	}
	
	

	public String getPoId() {
		return poId;
	}

	public void setPoId(String poId) {
		this.poId = poId;
	}

	

	public Payment(String id, String name, String version, String comments, String organizationId,
			String organizationName, String userId, String userName, String privateId, String dataType,
			String goodsType, List<SecureSet> secureSet, String payerId, String payerName, String payerBankOfDeposit,
			String payerAccountId, String payeeId, String payeeName, String payeeBankOfDeposit, String payeeAccountId,
			String payType, String reason, String customerPaymentId, String poId, PaymentDetail paymentdetail) {
		super();
		this.id = id;
		this.name = name;
		this.version = version;
		this.comments = comments;
		this.organizationId = organizationId;
		this.organizationName = organizationName;
		this.userId = userId;
		this.userName = userName;
		this.privateId = privateId;
		this.dataType = dataType;
		this.goodsType = goodsType;
		this.secureSet = secureSet;
		this.payerId = payerId;
		this.payerName = payerName;
		this.payerBankOfDeposit = payerBankOfDeposit;
		this.payerAccountId = payerAccountId;
		this.payeeId = payeeId;
		this.payeeName = payeeName;
		this.payeeBankOfDeposit = payeeBankOfDeposit;
		this.payeeAccountId = payeeAccountId;
		this.payType = payType;
		this.reason = reason;
		this.customerPaymentId = customerPaymentId;
		this.poId = poId;
		this.paymentdetail = paymentdetail;
	}

	public Payment() {
		
	}
	
	
	

}
