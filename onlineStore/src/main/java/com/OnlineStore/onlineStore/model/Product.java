package com.OnlineStore.onlineStore.model;

public class Product {

	private int prodId;
	private String prodTitle;
	private String prodBrand;
	private String imageUrl;
	private String prodDesc;
	private int price;

	public Product() {
		super();
	}

	public Product(int prodId, String prodTitle, String prodBrand, String imageUrl, String prodDesc, int price) {
		super();
		this.prodId = prodId;
		this.prodTitle = prodTitle;
		this.prodBrand = prodBrand;
		this.imageUrl = imageUrl;
		this.prodDesc = prodDesc;
		this.price = price;
	}

	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getProdTitle() {
		return prodTitle;
	}

	public void setProdTitle(String prodTitle) {
		this.prodTitle = prodTitle;
	}

	public String getProdBrand() {
		return prodBrand;
	}

	public void setProdBrand(String prodBrand) {
		this.prodBrand = prodBrand;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getProdDesc() {
		return prodDesc;
	}

	public void setProdDesc(String prodDesc) {
		this.prodDesc = prodDesc;
	}

}
