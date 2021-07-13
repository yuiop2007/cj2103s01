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
import com.spring.cj2103s01.service.QnaService;
import com.spring.cj2103s01.service.ReviewService;
import com.spring.cj2103s01.vo.ProductVO;
import com.spring.cj2103s01.vo.QnaVO;
import com.spring.cj2103s01.vo.ReviewVO;

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
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	QnaService qnaService;

	@RequestMapping(value = "/pInput", method = RequestMethod.GET)
	public String pInputGet() {
		return "product/pInput";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/pInput", method = RequestMethod.POST)
	public String pInputPost(MultipartFile file, ProductVO vo, HttpServletRequest request) {

		String root = "pInput";
		String uploadPath = request.getRealPath("/resources/ckeditor/images/"); // ckeditor를 통해서 저장된 모든 파일이 있는곳

		// 이미지파일 시작위치(42) :
		// src="/cj2103s01/resources/ckeditor/images/210622142615_1.png"

		imageService.imgCheck(vo.getpContent(), uploadPath, 42); // 이미지파일을 발췌해서 'src'폴더에 복사시킨다.

		vo.setpContent(vo.getpContent().replace("/resources/ckeditor/images/", "/resources/ckeditor/images/src/")); 
		if(vo.getpPrice()>60000) {
			vo.setpDelivery("무료");
		}else {
			vo.setpDelivery("일반");
		}
		
		int res = productService.setProductInput(file, vo, root);

		if (res == 1)
			msgFlag = "pInputOk";
		else
			msgFlag = "pInputNo";

		return "redirect:/msg/" + msgFlag;
	}

	@RequestMapping(value = "/pList", method = RequestMethod.GET)
	public String pListGet(@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize, Model model) {
		if (pag < 1) {
			pag = 1;
		}

		PagenationVO pageVO = pagenation.pagenation(pag, pageSize, "pList", "", "");

		List<ProductVO> vos = productService.getProductList(pageVO.getStartIndexNo(), pageSize);

		model.addAttribute("vos", vos);
		model.addAttribute("pageVO", pageVO);

		return "product/pList";
	}

	// 조건 검색 Search List처리
	@RequestMapping(value = "/pSearch", method = RequestMethod.GET)
	public String pSearchGet(String search, String searchString,
			@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize, Model model) {
		if (pag < 1) {
			pag = 1;
		}

		PagenationVO pageVO = pagenation.pagenation(pag, pageSize, "pList", search, searchString);

		List<ProductVO> vos = productService.getProductSearchList(pageVO.getStartIndexNo(), pageSize, search,
				searchString);

		String searchTitle = "";
		if (search.equals("pName")) {
			searchTitle = "상품이름";
		} else if (search.equals("pCate")) {
			searchTitle = "카테고리";
		}

		model.addAttribute("vos", vos);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("search", search);
		model.addAttribute("searchTitle", searchTitle);
		model.addAttribute("searchString", searchString);
		model.addAttribute("searchCount", pageVO.getTotRecCnt());

		return "product/pSearch";
	}

	@RequestMapping(value = "/pContent", method = RequestMethod.GET)
	public String pContentGet(Model model, int pId,
			@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "24", required = false) int pageSize, 
			@RequestParam(name = "rpag", defaultValue = "1", required = false) int rpag,
			@RequestParam(name = "rpageSize", defaultValue = "5", required = false) int rpageSize,
			@RequestParam(name = "qpag", defaultValue = "1", required = false) int qpag,
			@RequestParam(name = "qpageSize", defaultValue = "5", required = false) int qpageSize) {
		if (rpag < 1) {
			rpag = 1;
		}
		// 조회수 증가
		productService.addReadNum(pId);
		
		PagenationVO rpageVO = pagenation.pagenation(rpag, rpageSize, "preview", Integer.toString(pId) , "");
		
		List<ReviewVO> rvos = reviewService.getReviewContentList(pId ,rpageVO.getStartIndexNo(), rpageSize);

		model.addAttribute("rvos", rvos);
		model.addAttribute("rpageVO", rpageVO);
		
		PagenationVO qpageVO = pagenation.pagenation(qpag, qpageSize, "pqna", Integer.toString(pId) , "");
		
		List<QnaVO> qvos = qnaService.getQnaContentList(pId ,qpageVO.getStartIndexNo(), qpageSize);
		
		model.addAttribute("qvos", qvos);
		model.addAttribute("qpageVO", qpageVO);

		// 원본글 가져오기
		ProductVO vo = productService.getProductContent(pId);
		model.addAttribute("vo", vo);
		model.addAttribute("pag", pag);
		model.addAttribute("pageSize", pageSize);

		return "product/pContent";
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/pContent", method = RequestMethod.POST)
	public String nContentPost(int pId, HttpServletRequest request,
			@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize, Model model) {
		ProductVO vo = productService.getIdCheck(pId);

		String uploadPath = request.getRealPath("/resources/ckeditor/images/src/"); // ckeditor를 통해서 저장된 모든 파일이 있는곳
		imageService.imgCheck(vo.getpContent(), uploadPath, 46);

		msgFlag = "productUpdateIn$pId=" + vo.getpId() + "&pag=" + pag + "&pageSize=" + pageSize;
		return "redirect:/msg/" + msgFlag;
	}
	
	
	
	
	
	@RequestMapping(value = "/pUpdate", method = RequestMethod.GET)
	public String pUpdateGet(int pId, @RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize, Model model) {

		PagenationVO pageVO = pagenation.pagenation(pag, pageSize, "pList", "", "");
		ProductVO vo = productService.getProductContent(pId);

		model.addAttribute("pageVO", pageVO);
		model.addAttribute("vo", vo);

		return "product/pUpdate";
	}
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/pUpdate", method = RequestMethod.POST)
	public String pUpdatePost(MultipartFile file, ProductVO vo, Model model, HttpServletRequest request) {
		
		String root = "pInput";
		
		// 이곳에 오기전에 content안의 그림을 만약에 대비하여 image폴더에 백업받아 두었다.
		// 수정작업이 되었고(텍스트 or 그림포함), 이때 content안의 'src='태그속성이 있다면, image에 있는 그림파일을 src폴더로
		// 복사작업한다.
		if (!vo.getOriContent().equals(vo.getpContent()) && vo.getpContent().indexOf("src=\"/") != -1) {
			vo.setpContent(vo.getpContent().replace("/resources/ckeditor/images/src/", "/resources/ckeditor/images/"));

			// 기존에 src폴더에 존재하는 그림들을 모두 삭제처리한다.
			String uploadPath = request.getRealPath("/resources/ckeditor/images/src/");
			imageService.imgDelete(vo.getOriContent(), uploadPath, 46);

			// src폴더의 원본 그림을 모두 삭제시킨후, 새롭게 업로드시킨 그림을 다시 src폴더에 복사한다.
			uploadPath = request.getRealPath("/resources/ckeditor/images/"); // ckeditor를 통해서 저장된 모든 파일이 있는곳

			// 이미지파일 시작위치(42) :
			// src="/cj2103s10/resources/ckeditor/images/210622142615_1.png"
			imageService.imgCheck(vo.getpContent(), uploadPath, 42); // 이미지파일을 발췌해서 'src'폴더에 복사시킨다.
			// 위의 작업을 마치고 오면 이미지가 /src폴더로 복사완료되어 있다.

			vo.setpContent(vo.getpContent().replace("/resources/ckeditor/images/", "/resources/ckeditor/images/src/"));
		}
		
		int res = productService.productUpdateOk(file, vo, root); // 잘 정비된 VO를 DB에 저장한다.

		int pag = request.getParameter("pag") == null ? 1 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize") == null ? 1 : Integer.parseInt(request.getParameter("pageSize"));
		if(res==1) {
			msgFlag = "productUpdateOk$pId=" + vo.getpId() + "&pag=" + pag + "&pageSize=" + pageSize;
		} else {
			msgFlag = "productUpdateNo$pId=" + vo.getpId() + "&pag=" + pag + "&pageSize=" + pageSize;
		}

		return "redirect:/msg/" + msgFlag;
	}

	@RequestMapping(value = "/pDelete", method = RequestMethod.POST)
	public String pDeleteGet(int pId, HttpServletRequest request,
			@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize, Model model) {
		
		
		PagenationVO pageVO = pagenation.pagenation(pag, pageSize, "pList", "", "");

		List<ProductVO> vos = productService.getProductList(pageVO.getStartIndexNo(), pageSize);

		model.addAttribute("vos", vos);
		model.addAttribute("pageVO", pageVO);
		
		
		ProductVO vo = productService.getIdCheck(pId);
		productService.pDelete(vo.getpId());

		msgFlag = "productDeleteOk$pag=" + pag + "&pageSize=" + pageSize;
		return "/product/pList";
	}
	
	@RequestMapping(value = "/proShow", method = RequestMethod.GET)
	public String pproShowGet(@RequestParam String cate,
			@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "24", required = false) int pageSize, Model model) {
		if (pag < 1) {
			pag = 1;
		}
		
		PagenationVO pageVO = pagenation.pagenation(pag, pageSize, "pList", "", "");
		List<ProductVO> vos = productService.getProductList(pageVO.getStartIndexNo(), pageSize);

		model.addAttribute("vos", vos);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("cate", cate);
		
		return "product/proShow";
	}
	
	// 선택한 상품들 삭제처리하기
	@RequestMapping(value = "/pList", method = RequestMethod.POST)
	public String pListPost(String delItems) {
		String[] idxs = delItems.split("/");
		for (String idx : idxs) {
			productService.productDelete(Integer.parseInt(idx));
		}

		return "redirect:/msg/adminProductDeleteOk";
	}
	
}
