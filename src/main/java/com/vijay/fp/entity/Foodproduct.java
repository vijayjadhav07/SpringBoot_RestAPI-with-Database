package com.vijay.fp.entity;

import javax.persistence.Entity;


import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
public class Foodproduct {
	@Id
	private String productId;
	@NotNull(message = "Product name is required")
	private String productName;
	@Min(1)
	private int productQnty;
	@Min(1)
	private double productPrice;
	@NotNull(message = "Product Region is required")
	private String productRegion;

	public Foodproduct() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Foodproduct(String productId, String productName, int productQnty, double productPrice,
			String productRegion) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.productQnty = productQnty;
		this.productPrice = productPrice;
		this.productRegion = productRegion;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductQnty() {
		return productQnty;
	}

	public void setProductQnty(int d) {
		this.productQnty = d;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getProductRegion() {
		return productRegion;
	}

	public void setProductRegion(String productRegion) {
		this.productRegion = productRegion;
	}

	@Override
	public String toString() {
		return "Foodproduct [productId=" + productId + ", productName=" + productName + ", productQnty=" + productQnty
				+ ", productPrice=" + productPrice + ", productRegion=" + productRegion + "]";
	}

}
