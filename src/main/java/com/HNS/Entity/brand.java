package com.HNS.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class brand {
	@Id
	   @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer brandId;
	private String brandName;
	private String brandCategory;
	public brand()
	{
		brandCategory="";
		brandName="";
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public brand(String Brand,String br)
	{
		brandCategory=Brand;
		brandName = br;
	}
	public Integer getBrandId() {
		return brandId;
	}
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	public String getBrandCategory() {
		return brandCategory;
	}
	public void setBrandCategory(String brandCategory) {
		this.brandCategory = brandCategory;
	}
	

}
