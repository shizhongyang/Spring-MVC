package com.lq.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;


import com.lq.entity.Users;


@Repository
public class TestUsersDao {
	//注入已在spring-common.xml中配制好的sessionFactory
		@Resource(name = "sessionFactory")
		private SessionFactory sessionFactory;
		
		
		@SuppressWarnings("unchecked")
		public List<Users> getAllUsers(){
			String hql = "from Users";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			return query.list();
		}
		
		
		public Users getUsersById(String id){
			String hql = "from Users u where u.id = ?";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setString(0, id);
			return (Users) query.uniqueResult();
		}
		

		public Users getUsersByName(String name){
			String hql = "from Users u where u.name = ?";
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			query.setString(0, name);
			return (Users) query.uniqueResult();
		}
		
		
		//将用户存入数据库
		public void addUsers(Users user) {
			sessionFactory.getCurrentSession().save(user);
		}
}
