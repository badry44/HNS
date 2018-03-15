package com.HNS.Entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity


public class stores {
	@Id
	   @GeneratedValue(strategy=GenerationType.AUTO)
private Integer storeId;
private String storeName;
private String storeType;
private String storeLocation;
private int storeOwner;
private int storeState;
public stores(String storeName, String storeType, String storeLocation, int storeOwner, int storeState) {
	super();
	this.storeName = storeName;
	this.storeType = storeType;
	this.storeLocation = storeLocation;
	this.storeOwner = storeOwner;
	this.storeState = storeState;
}
public stores() {
	super();
	storeName = "";
	storeType = "";
	storeLocation = "";
	storeOwner = 0;
	storeState = 0;
	
}
public Integer getStoreId() {
	return storeId;
}
public void setStoreId(Integer storeId) {
	this.storeId = storeId;
}
public String getStoreName() {
	return storeName;
}
public void setStoreName(String storeName) {
	this.storeName = storeName;
}
public String getStoreType() {
	return storeType;
}
public void setStoreType(String storeType) {
	this.storeType = storeType;
}
public String getStoreLocation() {
	return storeLocation;
}
public void setStoreLocation(String storeLocation) {
	this.storeLocation = storeLocation;
}
public int getStoreOwner() {
	return storeOwner;
}
public void setStoreOwner(int storeOwner) {
	this.storeOwner = storeOwner;
}
public int getStoreState() {
	return storeState;
}
public void setStoreState(int storeState) {
	this.storeState = storeState;
}
}