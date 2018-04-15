package com.HNS.Entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class products {
	@Id
	   @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer productId;
	private String prdouctName;
	private String productCat;
	private double productPrice;
	private int productCount;
	private int productBrand;
	private int numberOfBuyers;
	
	public int getNumberOfBuyers() {
		return numberOfBuyers;
	}
	public void setNumberOfBuyers(int numberOfBuyers) {
		this.numberOfBuyers = numberOfBuyers;
	}
	public products(String productName, double productPrice, int productCount) {
		super();
		this.prdouctName = productName;
		this.productPrice = productPrice;
		this.productCount = productCount;
		numberOfBuyers=0;
	}
	public products(String productName, double productPrice, int productCount,int productBrand) {
		super();
		this.prdouctName = productName;
		this.productPrice = productPrice;
		this.productCount = productCount;
		this.productBrand = productBrand;
		numberOfBuyers=0;
	}
	
	public products()
	{
		this.prdouctName = "";
		this.productPrice = 0;
		this.productCount = 0;
		numberOfBuyers=0;
	}
	public products(Integer productId, String prdouctName, String productCat, double productPrice, int productCount,
			int productBrand) {
		super();
		this.productId = productId;
		this.prdouctName = prdouctName;
		this.productCat = productCat;
		this.productPrice = productPrice;
		this.productCount = productCount;
		this.productBrand = productBrand;
		numberOfBuyers=0;
	}

	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public String getPrdouctName() {
		return prdouctName;
	}
	public void setPrdouctName(String prdouctName) {
		this.prdouctName = prdouctName;
	}
	public String getProductCat() {
		return productCat;
	}
	public void setProductCat(String productCat) {
		this.productCat = productCat;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public int getProductBrand() {
		return productBrand;
	}
	public void setProductBrand(int productBrand) {
		this.productBrand = productBrand;
	}
	
	
	

}
