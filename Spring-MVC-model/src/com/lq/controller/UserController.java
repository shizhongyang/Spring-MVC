package com.lq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import com.lq.entity.Book;
import com.lq.entity.Reader;
import com.lq.entity.User;

import com.lq.service.CustomerService;
import com.lq.service.ReaderService;
import com.lq.service.UserService;

import antlr.collections.List;

//ע��controller
@Controller
@RequestMapping("/user")
public class UserController {

	//ע��service
	@Autowired
	private UserService userService;

	@Autowired
	private CustomerService customerService;
	
	
	//@Autowired
	//private BookService bookService;
	
	
	@Autowired
	private ReaderService re;
	
	@RequestMapping("/getAllUser")
	public String getAllReader(HttpServletRequest request) {
		request.setAttribute("userList", userService.getAllUser());
		return "/index";
	}
	
	
	@RequestMapping("/getAllReader")
	public String getAllUser(HttpServletRequest request) {
		
		ArrayList<Reader> list = (ArrayList<Reader>)re.getAllReader();
	
		System.out.println("list"+	list.get(0).toString());
		request.setAttribute("userList",list);

		
		
		return "success";
	}

	
	@ResponseBody
	@RequestMapping("/getAllUserJson")
	public ArrayList<User> getAllUserJson(HttpServletRequest request) {
		ArrayList<User> list = (ArrayList<User>) userService.getAllUser();
		return list;
	}
	
	@RequestMapping("/getUser")
	public String getUser(String id, HttpServletRequest request) {

		request.setAttribute("user", userService.getUser(id));

		return "/editUser";
	}

	@RequestMapping("/toAddUser")
	public String toAddUser() {
		return "/addUser";
	}
	
	@RequestMapping(value = "/addReader",method = RequestMethod.POST)
	public String addReader( @Valid Reader reader,Errors errors, HttpServletRequest request) {
		try {
			System.out.println("------"+reader.getMeno());
			if (errors.getErrorCount()>0) {
				System.out.println("������");
				for(FieldError error:errors.getFieldErrors()){
					System.out.println(error.getField() + ":" + error.getDefaultMessage());
				}
				return "AddReader";
			}
			re.addReader(reader);
			return "/success";
		} catch (Exception e) {
			return "/success";
		}		
	}
	
	@RequestMapping(value = "/rea",method = RequestMethod.GET)
	public String input(Map<String, Object> map){
		map.put("reader", new Reader());
		return "AddReader";
	}
	
	
	
//	@RequestMapping("/addBook")
//	public String addBook(Book book, HttpServletRequest request) {
//		try {
//			System.out.println("------"+book.getTitle());
//			bookService.addBook(book);
//			return "/success";
//		} catch (Exception e) {
//			return "/success";
//		}		
//	}
//	
	
	@RequestMapping("/addUser")
	public String addUser(User user, HttpServletRequest request) {
		try {
			
			System.out.println("------"+user.getAge());
			userService.addUser(user);
			return "redirect:/user/getAllUser";
		} catch (Exception e) {
			return "/error";
		}		
	}

	
	
	
	@RequestMapping("/delUser")
	public void delUser(String id, HttpServletResponse response) {

		String result = "{\"result\":\"error\"}";

		if (userService.delUser(id)) {
			result = "{\"result\":\"success\"}";
		}

		response.setContentType("application/json");

		try {
			PrintWriter out = response.getWriter();
			out.write(result);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/updateUser")
	public String updateUser(User user, HttpServletRequest request) {

		if (userService.updateUser(user)) {
			user = userService.getUser(user.getId());
			request.setAttribute("user", user);
			return "redirect:/user/getAllUser";
		} else {
			return "/error";
		}
	}
}
