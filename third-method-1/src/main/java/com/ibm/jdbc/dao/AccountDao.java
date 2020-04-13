package com.ibm.jdbc.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ibm.jdbc.services.TestService;

@Repository
public class AccountDao {

	
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public Integer getCount(){
		return jdbcTemplate.queryForObject("select count(*) from acc", Integer.class);
	}
	
	public String updateUser(Account account, double id) {
		String sql = "update acc set balance = ? where id = ?";
		
		if(jdbcTemplate.update(sql, account.getBalance(), id)==1)
		{
			return "updated successfully";
		}else{
			return "something terrible happened";
		}
	}
	public String addAccount(Account account) {
		String sql = "INSERT INTO acc values(?,?,?,?)" ;
		
		if(jdbcTemplate.update(sql, account.id, account.name, account.balance, account.pin)==1)
		{
			return "created account successfully";
			
		}else {
			return "something terrible occured";
		}
		
	}
	
	public String deleteAccount(double id) {
		String sql = "delete from acc where id = ?" ;
		
		if(jdbcTemplate.update(sql, id)==1)
		{
			return "deleted successfully";
			
		}else {
			return "something terrible occured";
		}
		
	}
	
	public List<Account> getAllUser() {
		 String sql = "SELECT * FROM acc";

		 BigDecimal bd,id, pin;
	        List<Map<String, Object>> list = jdbcTemplate
	                .queryForList(sql);

	        List<Account> pbList = new ArrayList<Account>();

	        for (Map<String, Object> map : list)
	        {
	            Account account = new Account();
	            bd =(BigDecimal) map.get("balance");
	            System.out.println("chck -12"+bd.doubleValue());
	            account.setBalance(bd.doubleValue());	            
	            account.setName((String)map.get("name"));
	            id=(BigDecimal) map.get("id");
	            account.setId(id.doubleValue());
	            pin = (BigDecimal) map.get("pin");
	            account.setPin(pin.doubleValue());
	            pbList.add(account);
	        }
	        return pbList;
	}
	public List<Map<String, Object>> getAllUserForReal(){
		String sql = "select * from acc";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	public Account getUserById(double id) {
		Account account = new Account();
		
		try {
			String sql = "select * from acc where id = ?";
			return jdbcTemplate.queryForObject(sql, new Object[] {id}, new RowMapper<Account>(){

				@Override
				public Account mapRow(ResultSet rs, int rowNum) throws SQLException {
					System.out.println("chck -1"+ rs.getDouble("balance") +":::"+rs.getString("name"));
					account.setBalance(rs.getDouble("balance"));
					account.setName(rs.getString("name"));
					account.setId(rs.getDouble("id"));
					account.setPin(rs.getDouble("pin"));
					return account;
				}
				
			});
		}catch(Exception e) {
			return null;
		}
	}
	
}
