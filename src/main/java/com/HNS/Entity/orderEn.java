package com.HNS.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity



public class orderEn {
	
	@Id
	   @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer orderId;
	private String shippingAddress;
	private String productName;
	private int  amounts;
	private Integer userId;
	private Integer productId;
	private Integer storeId;
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public orderEn(String shippingAddress, int amounts, Integer userId, Integer productId,String productName, Integer storeId) {
		super();
		this.shippingAddress = shippingAddress;
		this.amounts = amounts;
		this.userId = userId;
		this.productId = productId;
		this.productName = productName;
		this.storeId = storeId;
	}
	public orderEn() {
		super();
		this.shippingAddress = "";
		this.amounts = 0;
		this.userId = 0;
		this.productId = 0;
		this.productName="";
		this.storeId = 0;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public String getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public int getAmounts() {
		return amounts;
	}
	public void setAmounts(int amounts) {
		this.amounts = amounts;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}

}
