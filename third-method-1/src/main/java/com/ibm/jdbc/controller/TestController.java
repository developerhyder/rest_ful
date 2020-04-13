package com.ibm.jdbc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.jdbc.dao.Account;
import com.ibm.jdbc.dao.AccountDao;
import com.ibm.jdbc.services.TestService;

@RestController
@RequestMapping("/jdbc")
public class TestController {

	@Autowired
	TestService testService;
	@RequestMapping("/count")
	Integer gettingCount() {
		return testService.getCount();
	}
	
	@RequestMapping("/count/{id}")
	String getDetails(@PathVariable Integer id) {
		return testService.getAccountById((double)id);
		
	}
	@RequestMapping(method= RequestMethod.POST, value= "/count/adduser")
	String addUser(@RequestBody Account account) {
		
		return testService.addUser(account);
	}
	@RequestMapping(method= RequestMethod.PUT, value= "/count/updateuser/{id}")
	String update(@RequestBody Account account, @PathVariable Integer id) {
		return testService.updateUser(account, (double)id);
	}
	
	@RequestMapping("/allusers")
	List allUsers() {
		return testService.showAllUsers();
	}
	
	@RequestMapping(method= RequestMethod.DELETE, value= "/count/{id}")
	String deleteUser(@PathVariable Integer id) {
		return testService.deleteUser((double)id);
	}
}
