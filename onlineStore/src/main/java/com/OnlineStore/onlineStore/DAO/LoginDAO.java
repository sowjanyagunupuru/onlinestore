package com.OnlineStore.onlineStore.DAO;

import com.OnlineStore.onlineStore.model.Customer;
import com.OnlineStore.onlineStore.model.Login;

public interface LoginDAO {
	
	public Customer login(Customer customer);

	public Customer signUp(Customer customer);
	
	public boolean getUser(String username);
}
