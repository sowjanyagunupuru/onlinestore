package com.OnlineStore.onlineStore.Service;

import java.util.List;

import com.OnlineStore.onlineStore.model.Cart;
import com.OnlineStore.onlineStore.model.Customer;
import com.OnlineStore.onlineStore.model.Order;
import com.OnlineStore.onlineStore.model.Product;

public interface ProductService {

	public List<Product> getAllProducts(int n);

	public int addToCart(Cart cart);

	public List<Product> getFromCart(Customer customer);

	public List<Product> removeFromCart(Cart cart);

	public Product getProductById(Product product);

	public void addOrder(Order order);

	public List<Product> getOrders(Customer customer);

	public List<Product> getAllProduct(int limit, int offset);

	public int getTotalRecords();
}
