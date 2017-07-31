package com.lq.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lq.entity.Users;
import com.lq.service.TestUsersService;
import com.lq.utils.CreateFileUtil;

@Controller
public class RegisterController {
	
	@Autowired
	private TestUsersService testUsersService;

	
	@ResponseBody
	@RequestMapping(value = "/register",method = RequestMethod.POST, produces = "application/json; charset=utf-8")
	public Object register(
			@Valid Users users,
			@RequestParam(value="file",required=false) MultipartFile file, 
			Errors errors,
			HttpServletRequest request){
		
		System.out.println("---"+users);
		if (errors.getErrorCount()>0) {
			System.out.println("出错了");
			return null ;
		}else {
			Users users2 = testUsersService.getUsersByName(users.getName());
			if (users2!=null) {
				return "用户已存在";
			}else {				
				 //获得物理路径webapp所在路径  
		        String pathRoot = request.getSession().getServletContext().getRealPath("");  
		        String path="";  
		        if(!file.isEmpty()){  
		            //生成uuid作为文件名称  
		            String uuid = UUID.randomUUID().toString().replaceAll("-","");  
		            //获得文件类型（可以判断如果不是图片，禁止上传）  
		            String contentType=file.getContentType();  
		            //获得文件后缀名称  
		            String imageName=contentType.substring(contentType.indexOf("/")+1);  
		            path="/static/images/"+uuid+"."+imageName;  
		            CreateFileUtil.createDir(pathRoot+path);
		            try {
						file.transferTo(new File(pathRoot+path));
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}  
		        }  
		        System.out.println(path);  
		        request.setAttribute("imagesPath", path);  
				users.setImageUrl(path);
				testUsersService.addUsers(users);
				return "success";
			}
		}
		
	}
}
