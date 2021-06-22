package com.spring.cj2103s01;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s01.pagenation.Pagenation;
import com.spring.cj2103s01.pagenation.PagenationVO;
import com.spring.cj2103s01.service.ImageService;
import com.spring.cj2103s01.service.ProductService;
import com.spring.cj2103s01.vo.ProductVO;

@Controller
@RequestMapping("/product")
public class ProductController {
	String msgFlag = "";
	
	@Autowired
	Pagenation pagenation;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	ImageService imageService;
	
	@RequestMapping(value = "/pInput", method = RequestMethod.GET)
	public String pInputGet() {
		return "product/pInput";
	}
	
	@RequestMapping(value = "/pInput", method = RequestMethod.POST)
	public String pInputPost(MultipartFile file, ProductVO vo, HttpServletRequest request) {
	  	
		String root = "pInput";
	  	String uploadPath = request.getRealPath("/resources/ckeditor/images/");  // ckeditor를 통해서 저장된 모든 파일이 있는곳
	 
	  	// 이미지파일 시작위치(42) : src="/cj2103s01/resources/ckeditor/images/210622142615_1.png"
	  	
	  	imageService.imgCheck(vo.getpContent(), uploadPath, 42);  // 이미지파일을 발췌해서 'src'폴더에 복사시킨다.
	  	
	  	vo.setpContent(vo.getpContent().replace("/resources/ckeditor/images/", "/resources/ckeditor/images/src/")); // DB에 저장되는 실제 파일 정보의 위치
	  	
	  	int res = productService.setProductInput(file, vo, root);
	  	
		if(res == 1) msgFlag = "pInputOk";
		else msgFlag = "pInputNo";
		
		return "redirect:/msg/" + msgFlag;
	}
	
	@RequestMapping(value = "/pList", method = RequestMethod.GET)
	public String pListGet(@RequestParam(name="pag", defaultValue="1", required=false) int pag,
			@RequestParam(name="pageSize", defaultValue="10", required=false) int pageSize,
			Model model) {
		if(pag<1) {
			pag=1;
		}
		
		PagenationVO pageVo = pagenation.pagenation(pag, pageSize, "pList", "");
		
		List<ProductVO> vos = productService.getpList(pageVo.getStartIndexNo(), pageSize);
		
		model.addAttribute("vos", vos);
		model.addAttribute("pageVo", pageVo);
		
		return "product/pList";
	}
	
	@RequestMapping(value = "/pContent", method = RequestMethod.GET)
	public String pContentGet(int pId) {
		
		return "product/pContent";
	}
}
