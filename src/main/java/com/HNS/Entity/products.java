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
	private float productPrice;
	private int productCount;
	private int productBrand;
	
	public products(String productName, float productPrice, int productCount) {
		super();
		this.prdouctName = productName;
		this.productPrice = productPrice;
		this.productCount = productCount;
	}
	public products(String productName, float productPrice, int productCount,int productBrand) {
		super();
		this.prdouctName = productName;
		this.productPrice = productPrice;
		this.productCount = productCount;
		this.productBrand = productBrand;
	}
	
	public products()
	{
		this.prdouctName = "";
		this.productPrice = 0;
		this.productCount = 0;
	}
	public products(Integer productId, String prdouctName, String productCat, float productPrice, int productCount,
			int productBrand) {
		super();
		this.productId = productId;
		this.prdouctName = prdouctName;
		this.productCat = productCat;
		this.productPrice = productPrice;
		this.productCount = productCount;
		this.productBrand = productBrand;
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
	public float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(float productPrice) {
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
