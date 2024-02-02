package com.OnlineStore.onlineStore.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.OnlineStore.onlineStore.DAO.LoginDAO;
import com.OnlineStore.onlineStore.model.Customer;
import com.OnlineStore.onlineStore.model.Login;

@Component
public class LoginServiceImpl implements LoginService{

	@Autowired
	LoginDAO loginDAO;
	
	@Override
	public Customer login(Customer customer) {
		return loginDAO.login(customer);
	}
	
	@Override
	public Customer signUp(Customer customer)
	{
		return loginDAO.signUp(customer);
	}
}
