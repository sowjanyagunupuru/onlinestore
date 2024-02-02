package com.OnlineStore.onlineStore.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.OnlineStore.onlineStore.DAO.ProductDAO;
import com.OnlineStore.onlineStore.model.Cart;
import com.OnlineStore.onlineStore.model.Customer;
import com.OnlineStore.onlineStore.model.Order;
import com.OnlineStore.onlineStore.model.Product;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;

	@Override
	public List<Product> getAllProducts(int n) {
		return productDAO.getAllProducts(n);
	}

	@Override
	public int addToCart(Cart cart) {
		return productDAO.addToCart(cart);
	}

	@Override
	public List<Product> getFromCart(Customer customer) {
		return productDAO.getFromCart(customer);
	}

	@Override
	public List<Product> removeFromCart(Cart cart) {
		return productDAO.removeFromCart(cart);
	}

	@Override
	public Product getProductById(Product product) {
		return productDAO.getProdById(product);
	}

	@Override
	public void addOrder(Order order) {
		productDAO.addOrder(order);
	}

	@Override
	public List<Product> getOrders(Customer customer) {
		return productDAO.getOrders(customer);
	}

	@Override
	public List<Product> getAllProduct(int limit, int offset) {
		return productDAO.getAllProduct(limit, offset);
	}

	@Override
	public int getTotalRecords() {
		return productDAO.getTotalRecords();
	}

}
