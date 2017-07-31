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
			System.out.println("������");
			return null ;
		}else {
			Users users2 = testUsersService.getUsersByName(users.getName());
			if (users2!=null) {
				return "�û��Ѵ���";
			}else {				
				 //�������·��webapp����·��  
		        String pathRoot = request.getSession().getServletContext().getRealPath("");  
		        String path="";  
		        if(!file.isEmpty()){  
		            //����uuid��Ϊ�ļ�����  
		            String uuid = UUID.randomUUID().toString().replaceAll("-","");  
		            //����ļ����ͣ������ж��������ͼƬ����ֹ�ϴ���  
		            String contentType=file.getContentType();  
		            //����ļ���׺����  
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
