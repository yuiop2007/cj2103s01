package com.spring.cj2103s01;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s01.pagenation.Pagenation;
import com.spring.cj2103s01.pagenation.PagenationVO;
import com.spring.cj2103s01.service.ImageService;
import com.spring.cj2103s01.service.NoticeService;
import com.spring.cj2103s01.vo.NoticeVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	String msgFlag = "";
	
	@Autowired
	NoticeService noticeService;
	
	@Autowired
	Pagenation pagenation;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	ImageService imageService;
	
	@RequestMapping(value = "/notice", method = RequestMethod.GET)
	public String noticeGet(@RequestParam(name="pag", defaultValue="1", required=false) int pag,
			@RequestParam(name="pageSize", defaultValue="10", required=false) int pageSize,
			Model model) {
		if(pag<1) {
			pag=1;
		}
		
		PagenationVO pageVo = pagenation.pagenation(pag, pageSize, "notice", "");
		
		List<NoticeVO> vos = noticeService.getNoticeList(pageVo.getStartIndexNo(), pageSize);
		
		model.addAttribute("vos", vos);
		model.addAttribute("pageVo", pageVo);
		
		return "board/notice";
	}
	
	@RequestMapping(value = "/nInput", method = RequestMethod.GET)
	public String nInputGet() {
		
		return "board/noticeInput";
	}
	
	@RequestMapping(value = "/nInput", method = RequestMethod.POST)
	public String nInputPost(MultipartFile file, NoticeVO vo, HttpServletRequest request) {
		vo.setnPwd(bCryptPasswordEncoder.encode(vo.getnPwd()));
		
		String root = "nInput";
		String uploadPath = request.getRealPath("/resources/ckeditor/images/");  // ckeditor를 통해서 저장된 모든 파일이 있는곳
		 
	  	// 이미지파일 시작위치(42) : src="/cj2103s01/resources/ckeditor/images/210622142615_1.png"
	  	
	  	imageService.imgCheck(vo.getnContent(), uploadPath, 42);  // 이미지파일을 발췌해서 'src'폴더에 복사시킨다.
	  	
	  	vo.setnContent(vo.getnContent().replace("/resources/ckeditor/images/", "/resources/ckeditor/images/src/")); // DB에 저장되는 실제 파일 정보의 위치
	  	
	  	int res = noticeService.setNoticeInput(file, vo, root);
	  	
		if(res == 1) msgFlag = "nInputOk";
		else msgFlag = "nInputNo";
		
		return "redirect:/msg/" + msgFlag;
	}
	
	
	@RequestMapping(value = "/qna", method = RequestMethod.GET)
	public String qnaGet() {
		
		return "board/qna";
	}
	
	@RequestMapping(value = "/event", method = RequestMethod.GET)
	public String eventGet() {
		
		return "board/event";
	}
}
