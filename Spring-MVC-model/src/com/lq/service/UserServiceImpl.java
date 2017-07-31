package com.lq.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.lq.dao.CustomerDao;
import com.lq.dao.UserDao;
import com.lq.entity.Customer;
import com.lq.entity.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private CustomerDao<Customer> customerDao;

	public User getUser(String id) {
		return userDao.getUser(id);
	}

	public List<User> getAllUser() {
		return userDao.getAllUser();
	}

	public void addUser(User user) {
		/* ���ڲ���hibernate����������Ƿ�������
		 * �����ݿ��а�user���age�ֶ�����ΪNot NULL��������nullֵ�ᱨ��
		 * ��ȷ����customer���ݺ�user���ݻ���Ϊnullֵ����
		 * ��ʱ�������������������customer��user�����ݶ����ᱣ��*/
		Customer customer = new Customer();
		customer.setAge("15");
		customer.setName("43");
		customerDao.addCustomer(customer);
		user.setAge(null);
		userDao.addUser(user);
	}

	public boolean delUser(String id) {

		return userDao.delUser(id);
	}

	public boolean updateUser(User user) {
		return userDao.updateUser(user);
	}
}
