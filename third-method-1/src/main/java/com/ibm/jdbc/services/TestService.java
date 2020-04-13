package com.ibm.jdbc.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.ibm.jdbc.dao.Account;
import com.ibm.jdbc.dao.AccountDao;

@Service
public class TestService {

	//TestService should use @repo
	
	@Autowired
	AccountDao dao;
	
	public Integer getCount() {
		return dao.getCount();
	}

	public String getAccountById(double id) {
		Account account = dao.getUserById(id);
		if(account == null)
			return "could not find any details";
		else {
			return account.toString();
		}
	}
	public String updateUser(Account acc, double id) {
		return dao.updateUser(acc, id);
	}
	public String addUser(Account acc) {
		return dao.addAccount(acc);
	}
	public String deleteUser(double id) {
		return dao.deleteAccount(id);
	}
	public List showAllUsers() {
		//return dao.getAllUser();
		return dao.getAllUserForReal();
	}
	
}
