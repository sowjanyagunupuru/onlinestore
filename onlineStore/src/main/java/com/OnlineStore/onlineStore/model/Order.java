package com.OnlineStore.onlineStore.model;

import java.sql.Timestamp;
import java.util.Date;

public class Order {

	private int id;
	private int cust_id;
	private String orderId;
	private double total;
	private String payreference;
	private Timestamp orderdate;
	private boolean isEmpty;

	public Order() {
		super();
	}

	public Order(int id, int cust_id, String orderId, double total, String payreference, Timestamp orderdate,
			boolean isEmpty) {
		super();
		this.id = id;
		this.cust_id = cust_id;
		this.orderId = orderId;
		this.total = total;
		this.payreference = payreference;
		this.orderdate = orderdate;
		this.isEmpty = isEmpty;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty(boolean isEmpty) {
		this.isEmpty = isEmpty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCust_id() {
		return cust_id;
	}

	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getPayreference() {
		return payreference;
	}

	public void setPayreference(String payreference) {
		this.payreference = payreference;
	}

	public Timestamp getOrderdate() {
		return orderdate;
	}

	public void setOrderdate(Timestamp orderdate) {
		this.orderdate = orderdate;
	}

}
