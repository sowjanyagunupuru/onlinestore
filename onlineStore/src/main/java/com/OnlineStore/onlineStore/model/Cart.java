package com.OnlineStore.onlineStore.model;

public class Cart {

	private int cust_Id;
	private int prodId;
	private boolean isEmpty;

	public Cart(int cust_Id, int prodId, boolean isEmpty) {
		super();
		this.cust_Id = cust_Id;
		this.prodId = prodId;
		this.isEmpty = isEmpty;
	}

	public Cart() {
		super();
	}

	public int getCust_Id() {
		return cust_Id;
	}

	public void setCust_Id(int cust_Id) {
		this.cust_Id = cust_Id;
	}

	public int getProdId() {
		return prodId;
	}

	public void setProdId(int prodId) {
		this.prodId = prodId;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

}
