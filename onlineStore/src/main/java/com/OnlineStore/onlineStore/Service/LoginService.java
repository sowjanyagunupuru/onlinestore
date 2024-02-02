package com.OnlineStore.onlineStore.Service;

import com.OnlineStore.onlineStore.model.Customer;
import com.OnlineStore.onlineStore.model.Login;

public interface LoginService {

	public Customer login(Customer customer);

	public Customer signUp(Customer customer);
}
