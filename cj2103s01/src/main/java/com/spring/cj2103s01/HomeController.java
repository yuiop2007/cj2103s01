package com.spring.cj2103s01;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class HomeController {
	
	@RequestMapping(value = {"/","main"}, method = RequestMethod.GET)
	public String main() {
		return "main/main";
	}
	
	@ResponseBody
	@RequestMapping("/imageUpload")
	public void imageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) throws Exception {
		// ckeditor에서 업로드할 파일들 처리....
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String originalFilename = upload.getOriginalFilename();
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		originalFilename = sdf.format(date) + "_" + originalFilename;
		
		
		byte[] bytes = upload.getBytes();
		// ckeditor에서 올린 파일을, 서버 파일시스템에 저장시킨다.
		//String uploadPath = request.getRealPath("/resources/ckeditor/images/");
		String uploadPath = request.getSession().getServletContext().getRealPath("/resources/ckeditor/images/");
		OutputStream outStr = new FileOutputStream(new File(uploadPath + originalFilename));
		outStr.write(bytes);
		
		// 아래는 ckeditor 화면에 출력처리
		PrintWriter out = response.getWriter();
		String fileUrl = request.getContextPath() + "/resources/ckeditor/images/" + originalFilename;
		//out.println("{\"변수명\":\"값\"}");
		out.println("{\"originalFilename\":\""+originalFilename+"\",\"uploaded\":1,\"url\":\""+fileUrl+"\"}");
		
		out.flush();
		outStr.close();
	}
	
	
}
