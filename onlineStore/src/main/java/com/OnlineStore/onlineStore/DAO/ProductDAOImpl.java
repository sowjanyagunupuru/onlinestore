package com.OnlineStore.onlineStore.DAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Component;

import com.OnlineStore.onlineStore.model.Cart;
import com.OnlineStore.onlineStore.model.Customer;
import com.OnlineStore.onlineStore.model.Order;
import com.OnlineStore.onlineStore.model.Product;

@Component
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private NamedParameterJdbcTemplate template;
	List<Product> cartProducts = new ArrayList<>();
	List<Product> orderProducts = new ArrayList<>();

	@Override
	public List<Product> getAllProducts(int n) {
		String selectSql = "SELECT * FROM hosproducts";

		try {
			BeanPropertyRowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
			return this.template.query(selectSql, rowMapper);
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public int addToCart(Cart cart) {
		String insertSql = "insert into hoscart(cust_id,prodid) values(:cust_id,:prodid)";

		try {
			Map<String, Integer> cartMap = new HashMap<>();
			cartMap.put("cust_id", cart.getCust_Id());
			cartMap.put("prodid", cart.getProdId());
			return this.template.update(insertSql, cartMap);
		} catch (Exception e) {
			return 0;
		}
	}

	@Override
	public List<Product> getFromCart(Customer customer) {
		String selectSql = "select p.prodid,prodtitle,prodBrand,imageurl,proddesc,price from hosproducts p,hoscart c1"
				+ "		where c1.cust_id=:cust_id and c1.prodid=p.prodid";

		try {
			BeanPropertyRowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
			Map<String, Integer> cartMap = new HashMap<>();
			cartMap.put("cust_id", customer.getCust_Id());
			cartProducts = this.template.query(selectSql, cartMap, rowMapper);
			return cartProducts;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public List<Product> removeFromCart(Cart cart) {
		String deleteSql = "delete from hoscart " + "where cust_id=:cust_Id and prodid=:prodId";

		try {
			BeanPropertyRowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
			Map<String, Integer> cartMap = new HashMap<>();
			cartMap.put("cust_Id", cart.getCust_Id());
			cartMap.put("prodId", cart.getProdId());
			int res = this.template.update(deleteSql, cartMap);
			if (res > 0) {
				String selectSql = "select p.prodid,prodtitle,prodBrand,imageurl,proddesc,price from hosproducts p,hoscart c1"
						+ "		where c1.cust_id=:cust_Id and c1.prodid=p.prodId";

				try {
					return this.template.query(selectSql, cartMap, rowMapper);
				} catch (Exception e) {
					return new ArrayList<>();
				}
			} else {
				return new ArrayList<>();
			}
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	@Override
	public Product getProdById(Product product) {
		String selectSql = "select * from hosproducts where prodid=:prodId";

		try {
			BeanPropertyRowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
			Map<String, Integer> cartMap = new HashMap<>();
			cartMap.put("prodId", product.getProdId());
			return this.template.queryForObject(selectSql, cartMap, rowMapper);
		} catch (Exception e) {
			return new Product();
		}
	}

	@Override
	public void addOrder(Order order) {
		String insertSql = "insert into hosorders(cust_id, orderid, orderdate, total, payreference) "
				+ "values(:cust_id,:orderId,:orderdate,:total,:payreference)";

		try {
			Date currentDate = new Date();
			Timestamp currentTimestamp = new Timestamp(currentDate.getTime());
			order.setOrderdate(currentTimestamp);
			SqlParameterSource parameters = new BeanPropertySqlParameterSource(order);
			this.template.update(insertSql, parameters);

			String selectSql = "select id from hosorders order by id desc limit 1";
			Integer orderId = this.template.queryForObject(selectSql, new MapSqlParameterSource(), Integer.class);

			String insertOPSql = "insert into hosOrderProducts(id,prodid,cust_id) values(:id,:prodId,:cust_id)";
			for (Product p1 : cartProducts) {
				Map<String, Integer> orderMap = new HashMap<>();
				orderMap.put("id", orderId);
				orderMap.put("prodId", p1.getProdId());
				orderMap.put("cust_id", order.getCust_id());
				this.template.update(insertOPSql, orderMap);
			}

			String deleteSQL = "delete from hoscart where cust_id=:cust_id";
			Map<String, Integer> cartMap = new HashMap<>();
			cartMap.put("cust_id", order.getCust_id());
			int res = this.template.update(deleteSQL, cartMap);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<Product> getOrders(Customer customer) {
		String selectSql = "select p.prodid,p.prodtitle,imageurl,price from hosproducts p,hosorderproducts op "
				+ "where op.cust_id=:cust_id and p.prodid=op.prodid";

		try {
			BeanPropertyRowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
			Map<String, Integer> orderMap = new HashMap<>();
			orderMap.put("cust_id", customer.getCust_Id());
			orderProducts = this.template.query(selectSql, orderMap, rowMapper);
			return orderProducts;
		} catch (Exception e) {
			return new ArrayList<>();
		}
	}

	public List<Product> getAllProduct(int limit, int offset) {

		try {
			String selectSql = "SELECT * FROM crm.hosproducts ORDER BY prodId ASC LIMIT :limit OFFSET :offset";

			MapSqlParameterSource paramMap = new MapSqlParameterSource();
			paramMap.addValue("limit", limit);
			paramMap.addValue("offset", offset);
			BeanPropertyRowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
			List<Product> productList = this.template.query(selectSql, paramMap, rowMapper);
			return productList;
		} catch (Exception e) {
			// Handle exceptions appropriately
			return null;
		}
	}

	@Override
	public int getTotalRecords() {
		String countSql = "SELECT COUNT(*) FROM crm.hosproducts";
		int count = template.getJdbcOperations().queryForObject(countSql, Integer.class);
		return count;
	}
}
