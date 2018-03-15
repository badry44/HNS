package com.HNS.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class stat {
	@Id
	   @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer statId;
	private Integer storeId;
	private Integer numberOfViews;
	private Integer numberOfUserBuy;
	public stat(Integer storeId, Integer numberOfViews, Integer numberOfUserBuy) {
		super();
		this.storeId = storeId;
		this.numberOfViews = numberOfViews;
		this.numberOfUserBuy = numberOfUserBuy;
	}
	public stat() {
		this.storeId = 0;
		this.numberOfViews = 0;
		this.numberOfUserBuy = 0;
	}
	public Integer getStatId() {
		return statId;
	}
	public void setStatId(Integer statId) {
		this.statId = statId;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public Integer getNumberOfViews() {
		return numberOfViews;
	}
	public void setNumberOfViews(Integer numberOfViews) {
		this.numberOfViews = numberOfViews;
	}
	public Integer getNumberOfUserBuy() {
		return numberOfUserBuy;
	}
	public void setNumberOfUserBuy(Integer numberOfUserBuy) {
		this.numberOfUserBuy = numberOfUserBuy;
	}
	
	
	

}
