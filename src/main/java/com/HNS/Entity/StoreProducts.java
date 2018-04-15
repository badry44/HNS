package com.HNS.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity

public class StoreProducts {
	@Id
	   @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer storeProductId;
	private Integer storeId;
	private Integer productId;
	private Integer productCount;
	private String productName;
	public Integer getStoreProductId() {
		return storeProductId;
	}
	public void setStoreProductId(Integer storeProductId) {
		this.storeProductId = storeProductId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getProductCount() {
		return productCount;
	}
	public StoreProducts(Integer storeId, Integer productId, Integer adderId, Integer productCount ,String productName) {
		super();
		this.storeId = storeId;
		this.productId = productId;
		this.productCount = productCount;
		this.productName = productName;
	}
	public StoreProducts() {
		super();
		this.storeId = 0;
		this.productId = 0;
		this.productCount = 0;
		this.productName = "";
	}
	
	public void setProductCount(Integer productCount) {
		this.productCount = productCount;
	}
	
	

}
