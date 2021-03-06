package com.spring.cj2103s01;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

import com.spring.cj2103s01.pagenation.Pagenation;
import com.spring.cj2103s01.pagenation.PagenationVO;
import com.spring.cj2103s01.service.ProductService;
import com.spring.cj2103s01.service.SlideService;
import com.spring.cj2103s01.vo.ProductVO;
import com.spring.cj2103s01.vo.SlideVO;

@Controller
public class HomeController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	Pagenation pagenation;
	
	@Autowired
	SlideService slideService;
	
	@RequestMapping(value = {"/","main"}, method = RequestMethod.GET)
	public String main(@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "24", required = false) int pageSize, Model model) {
		if (pag < 1) {
			pag = 1;
		}
		String cate = "ALL";
		
		PagenationVO pageVO = pagenation.pagenation(pag, pageSize, "pList", "", "");
		List<ProductVO> vos = productService.getEventProductList(pageVO.getStartIndexNo(), pageSize);
		List<SlideVO> svos = slideService.getSlideMainList();
		int sCnt = slideService.getSlideTotCnt();
		
		model.addAttribute("cate", cate);
		model.addAttribute("sCnt", sCnt);
		model.addAttribute("vos", vos);
		model.addAttribute("svos", svos);
		model.addAttribute("pageVO", pageVO);
		
		return "main/main";
	}
	
	@ResponseBody
	@RequestMapping("/imageUpload")
	public void imageUpload(HttpServletRequest request, HttpServletResponse response, @RequestParam MultipartFile upload) throws Exception {
		// ckeditor?????? ???????????? ????????? ??????....
		
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String originalFilename = upload.getOriginalFilename();
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMddHHmmss");
		originalFilename = sdf.format(date) + "_" + originalFilename;
		
		
		byte[] bytes = upload.getBytes();
		// ckeditor?????? ?????? ?????????, ?????? ?????????????????? ???????????????.
		//String uploadPath = request.getRealPath("/resources/ckeditor/images/");
		String uploadPath = request.getSession().getServletContext().getRealPath("/resources/ckeditor/images/");
		OutputStream outStr = new FileOutputStream(new File(uploadPath + originalFilename));
		outStr.write(bytes);
		
		// ????????? ckeditor ????????? ????????????
		PrintWriter out = response.getWriter();
		String fileUrl = request.getContextPath() + "/resources/ckeditor/images/" + originalFilename;
		//out.println("{\"?????????\":\"???\"}");
		out.println("{\"originalFilename\":\""+originalFilename+"\",\"uploaded\":1,\"url\":\""+fileUrl+"\"}");
		
		out.flush();
		outStr.close();
	}
	
	
}
