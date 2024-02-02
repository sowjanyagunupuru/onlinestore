package com.OnlineStore.onlineStore.DAO;

import java.util.HashMap;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.OnlineStore.onlineStore.model.LoanData;

public class LoanDAOImpl implements RateChangeDAO {

	private NamedParameterJdbcTemplate template;
	@Override
	public int changeROI(LoanData loanData) {
		String insertSql = "INSERT INTO loan_data (loan_reference_number, customer_name, current_roi, new_roi) " +
                "VALUES (:loanReferenceNumber, :customerName, :currentROI, :newROI)";

		try {
		 SqlParameterSource parameters = new BeanPropertySqlParameterSource(loanData);
		
		 return this.template.update(insertSql, parameters);
		} catch (Exception e) {
		 return 0;
		}
	}

}
