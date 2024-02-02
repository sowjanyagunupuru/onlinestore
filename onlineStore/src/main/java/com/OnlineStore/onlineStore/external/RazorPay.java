package com.OnlineStore.onlineStore.external;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;

@Component
public class RazorPay {

	public String createRazorpayOrder(double amt) {
		String orderId = null;
		try {
			RazorpayClient razorpayClient = new RazorpayClient("rzp_test_9HQWiJ62Fr4xUA", "oDUzcYm0WMmzcHXxotogxXkn");
			JSONObject obj = new JSONObject();
			obj.put("amount", amt);
			obj.put("currency", "INR");
			obj.put("receipt", "hello123");
			obj.put("payment_capture", true);
			Order order = razorpayClient.orders.create(obj);
			orderId = order.get("id");
			//System.out.println("orderId generated "+order.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderId;
	}
}
