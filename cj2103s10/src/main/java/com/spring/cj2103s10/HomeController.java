package com.spring.cj2103s10;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s10.service.AdminService;
import com.spring.cj2103s10.vo.NotifyVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	AdminService adminService;
	
	@RequestMapping(value = {"/","/h","/main"}, method = RequestMethod.GET)
	public String home(Model model) {
		List<NotifyVO> vos = adminService.getNotifyPopup();
		model.addAttribute("popupVos", vos);
		
		return "main/main";
	}
	
	@ResponseBody
	@RequestMapping("/imageUpload")
	public void imageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) throws Exception {
		// ckeditor에서 업로드할 파일들 처리....
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
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
		
		// 아래는 ckeditor화면에 출력처리
		PrintWriter out = response.getWriter();
		String fileUrl = request.getContextPath() + "/resources/ckeditor/images/" + originalFilename;
		//out.println("{\"변수명\":\"값\"}");
		out.println("{\"originalFilename\":\""+originalFilename+"\",\"uploaded\":1,\"url\":\""+fileUrl+"\"}");

		out.flush();
		outStr.close();
	}
	
  //예약관리 사진 업로드부분
	@ResponseBody
	@RequestMapping("/reservation/imageUpload")
	public void reservationImageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) throws Exception {
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String fileName=upload.getOriginalFilename();
		
		Date date = new Date();
		SimpleDateFormat imsi = new SimpleDateFormat("yyMMddHHmmss");
		fileName = imsi.format(date)+"_"+fileName;
		byte[] bytes = upload.getBytes();
		
		String uploadPath = request.getSession().getServletContext().getRealPath("/")+"/resources/ckeditor/images/reservation/";
		OutputStream outStr = new FileOutputStream(new File(uploadPath + fileName));
		outStr.write(bytes);
		
		PrintWriter out=response.getWriter();
		String fileUrl=request.getContextPath()+"/resources/ckeditor/images/reservation/"+fileName;
		
		out.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
		
		out.flush();
		outStr.close();
	}
	
  //포토갤러리 사진 업로드부분
	@RequestMapping("/imageUpload/photo")
	@ResponseBody
	public void imagePhoto(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) throws Exception {
	  response.setCharacterEncoding("utf-8");
   response.setContentType("text/html;charset=utf-8");
	  
   // UUID를 이용한, 서버파일시스템에 저장할 중복방지를 위한 파일명 제작
   UUID uid = UUID.randomUUID();
	  String fileName = uid + "_" + upload.getOriginalFilename();
   
   // 이곳은 그림파일을 서버의 파일시스템으로 저장하기위해 경로와 파일명을 이용하여 OutputStream객체를 생성한다.
	  String uploadPath = request.getSession().getServletContext().getRealPath("/resources/ckeditor/images/photo/");
   OutputStream outStr = new FileOutputStream(new File(uploadPath + fileName));
   
   // 업로드된 파일을 Byte단위로 받아서 바이트배열로 저장한다.
   byte[] bytes = upload.getBytes();
   // 바이트배열로 저장된 업로드파일을, 미리 생성해 놓은 지정된 경로와 파일명객체(outStr)로 저장한다. 
   outStr.write(bytes);
   
   // 아래는 ckeditor화면에 그림을 출력시켜주는것...
   //String callback=request.getParameter("CKEditorFuncNum");
   PrintWriter out=response.getWriter();
   String fileUrl=request.getContextPath()+"/resources/ckeditor/images/photo/"+fileName;
   // JSON방식을 이용해서 브라우저 textarea 화면창에 출력한다.
   out.println("{\"filename\" : \""+fileName+"\", \"uploaded\" : 1, \"url\":\""+fileUrl+"\"}");
   
   out.flush();
   outStr.close();
	}
	
}
