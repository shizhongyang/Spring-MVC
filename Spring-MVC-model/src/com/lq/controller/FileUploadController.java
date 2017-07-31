package com.lq.controller;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lq.entity.Users;
import com.lq.utils.CreateFileUtil;

@Controller
@RequestMapping("/file")
public class FileUploadController {

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	private String fildUpload(Users users, @RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request) throws Exception {

		System.out.println(users.toString());


		String pathRoot = request.getSession().getServletContext().getRealPath("");
		String path = "";
		if (!file.isEmpty()) {

			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			String contentType = file.getContentType();
			String imageName = contentType.substring(contentType.indexOf("/") + 1);
			path = "/static/images/" + uuid + "." + imageName;

			CreateFileUtil.createDir(pathRoot + path);
			file.transferTo(new File(pathRoot + path));
		}
		System.out.println(path);
		request.setAttribute("imagesPath", path);
		return "success";
	}

	// ��Ϊ�ҵ�JSP��WEB-INFĿ¼���棬������޷�ֱ�ӷ���
	@RequestMapping(value = "/forward")
	private String forward() {
		return "index";
	}
}
