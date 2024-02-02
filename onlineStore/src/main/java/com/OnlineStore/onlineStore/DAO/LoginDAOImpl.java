package com.OnlineStore.onlineStore.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import com.OnlineStore.onlineStore.model.Customer;
import com.OnlineStore.onlineStore.model.Login;

@Component
public class LoginDAOImpl implements LoginDAO {

    @Autowired
    private NamedParameterJdbcTemplate template;
    
    static int cust_id = 20;

    @Override
    public Customer login(Customer customer) {
        String selectSql = "SELECT * FROM hoscustomers WHERE username=:username AND password=:password";
        
        Map<String, String> loginMap = new HashMap<>();
        loginMap.put("username", customer.getUsername());
        loginMap.put("password", customer.getPassword());
        Customer cust = new Customer();

        try {
            BeanPropertyRowMapper<Customer> rowMapper = BeanPropertyRowMapper.newInstance(Customer.class);
            cust = this.template.queryForObject(selectSql, loginMap, rowMapper);
            cust.setLoginStatus(true);
            return cust;
        } catch (Exception e) {
            //e.printStackTrace();
        	cust.setLoginStatus(false);
            return cust;
        }
    }
    
    @Override
    public Customer signUp(Customer customer) {
    	
    	Map<String, String> customerMap = new HashMap<>();
        customerMap.put("username", customer.getUsername());
        customerMap.put("password", customer.getPassword());
        customerMap.put("mobile",customer.getMobile());
        customerMap.put("email",customer.getEmail());
        customerMap.put("address",customer.getAddress());
        customerMap.put("status", customer.getStatus());
        
        BeanPropertyRowMapper<Customer> rowMapper = BeanPropertyRowMapper.newInstance(Customer.class);
        
        String selectSql = "select cust_id from hoscustomers order by cust_id desc limit 1";
    	try {
    		Customer custObj = this.template.queryForObject(selectSql, customerMap,rowMapper);
    		if(custObj.getCust_Id()>0) {
    		customer.setCust_Id(custObj.getCust_Id());}
    		else {
    			customer.setCust_Id(1);
    		}
    	}
    	catch(EmptyResultDataAccessException e) {
    		customer.setCust_Id(1);
    	}
    	
        String insertSql = "INSERT INTO hoscustomers(username,mobile,email,address,status,password)"
        		+ "	VALUES(:username, :mobile, :email, :address, :status, :password)";    
        try {
            
            int count= this.template.update(insertSql, customerMap);
            if(count>0)
            {
            	customer.setLoginStatus(true);
            	return customer;
            }
            else {
            	customer.setLoginStatus(false);
            	return new Customer();
            }
        } catch (DuplicateKeyException e) {
            customer.setLoginStatus(false);
            return new Customer();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        	return new Customer();
        }
    }
    
    @Override
    public boolean getUser(String username) {
    	String selectSql = "SELECT * FROM hoscustomers WHERE username=:username";
        
        Map<String, String> userMap = new HashMap<>();
        userMap.put("username",username);

        try {
            BeanPropertyRowMapper<Customer> rowMapper = BeanPropertyRowMapper.newInstance(Customer.class);
            Customer result= this.template.queryForObject(selectSql, userMap, rowMapper);
            if(result!=null)
            {
            	return false;
            }
            else {
            	return false;
            }
        } 
        catch (EmptyResultDataAccessException e) {
            return true;
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
		return false;
    }
}
