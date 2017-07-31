package com.lq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lq.dao.TestUsersDao;
import com.lq.entity.User;
import com.lq.entity.Users;

@Service
public class TestUsersService {

	@Autowired
	private TestUsersDao testUsersDao;

	// 将用户存入数据库
	public void addUsers(Users user) {
		testUsersDao.addUsers(user);
	}

	public List<Users> getAllUsers() {

		return testUsersDao.getAllUsers();
	}

	
	public Users getUsersByName(String name){
		return testUsersDao.getUsersByName(name);
	}
	
	

	public Users getUsersById(String id){
		return testUsersDao.getUsersById(id);
	}
}
