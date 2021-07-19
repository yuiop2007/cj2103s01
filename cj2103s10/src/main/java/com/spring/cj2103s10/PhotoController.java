package com.spring.cj2103s10;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring.cj2103s10.pagination.Pagination;
import com.spring.cj2103s10.pagination.PaginationVO;
import com.spring.cj2103s10.service.PhotoService;
import com.spring.cj2103s10.vo.PhotoVO;

@Controller
@RequestMapping("/photo")
public class PhotoController {
	String msgFlag = "";
	
  @Autowired
  PhotoService photoService;
  
  @Autowired
  Pagination pagination;
  
  @RequestMapping(value="/photo", method=RequestMethod.GET)
  public String photoGet(Model model) {
  	List<PhotoVO> vos = photoService.getPhoto();
  	int[] idxs = new int[20];
  	String[] titles = new String[20];
  	String[] thumbnails = new String[20];
  	int cnt = 0;
  	for(PhotoVO vo : vos) {
  		idxs[cnt] = vo.getIdx();
  		titles[cnt] = vo.getTitle();
  		thumbnails[cnt] = vo.getThumbnail();
  		cnt++;
  	}
  	model.addAttribute("vos", vos);
  	model.addAttribute("idxs", idxs);
  	model.addAttribute("titles", titles);
  	model.addAttribute("thumbnails", thumbnails);
  	
  	return "photo/photo";
  }
  
  @RequestMapping(value="/photoInput", method=RequestMethod.GET)
  public String photoInputGet() {
  	return "photo/photoInput";
  }
  
  @RequestMapping(value="/photoInput", method=RequestMethod.POST)
  public String photoInputPost(PhotoVO vo, HttpServletRequest request) {
		String uploadPath = request.getSession().getServletContext().getRealPath("/resources/ckeditor/images/photo/");
		photoService.imgCheck(vo, uploadPath); // 이미지를 발췌해서 'src'폴더로 복사처리하려고 한다.
  	msgFlag = "photoInputOk";
  	
  	return "redirect:/msg/" + msgFlag;
  }

  @RequestMapping(value="/photoContent", method=RequestMethod.GET)
  public String photoContentGet(Model model, int idx) {
  	photoService.setReadNumCount(idx);
  	PhotoVO vo = photoService.getPhotoContent(idx);
  	model.addAttribute("vo", vo);
  	
  	return "photo/photoContent";
  }
  
  @RequestMapping(value="/photoDelete", method=RequestMethod.GET)
  public String photoDeleteGet(int idx, HttpServletRequest request) {
  	String deletePath = request.getSession().getServletContext().getRealPath("/resources/ckeditor/images/photo/src/");
  	photoService.photoDelete(idx, deletePath);
  	msgFlag = "photoDeleteOk";
  	
  	return "redirect:/msg/" + msgFlag;
  }
  
  @RequestMapping(value="/photoPart", method=RequestMethod.GET)
  public String photoPartGet(String part,
			@RequestParam(name="pag", defaultValue="1", required=false) int pag,
			@RequestParam(name="pageSize", defaultValue="5", required=false) int pageSize,
			Model model) {
    PaginationVO pageVo = pagination.pagination(pag, pageSize, "photo", part, "");
		
		List<PhotoVO> vos = photoService.photoPartList(pageVo.getStartIndexNo(), pageSize, part);
		model.addAttribute("pageVo", pageVo);
		model.addAttribute("part", part);
		model.addAttribute("vos", vos);
		
		return "photo/photoPart";
  }
  
  @RequestMapping(value="/photoSearch", method=RequestMethod.GET)
  public String photoSearchGet(String search, String searchString,
  		@RequestParam(name="pag", defaultValue="1", required=false) int pag,
			@RequestParam(name="pageSize", defaultValue="5", required=false) int pageSize,
			Model model) {
  	
  	PaginationVO pageVo = pagination.pagination(pag, pageSize, "photo", search, searchString);
  	
  	List<PhotoVO> vos = photoService.photoSearchList(pageVo.getStartIndexNo(), pageSize, search, searchString);
  	
  	String searchTitle = "";
  	if(search.equals("title")) searchTitle = "글제목"; 
  	else if(search.equals("name")) searchTitle = "글쓴이"; 
  	else searchTitle = "글내용"; 
  	
  	model.addAttribute("vos", vos);
  	model.addAttribute("pageVo", pageVo);
  	model.addAttribute("search", search);
  	model.addAttribute("searchTitle", searchTitle);
  	model.addAttribute("searchString", searchString);
  	model.addAttribute("searchCount", pageVo.getTotRecCnt());
  	
  	return "photo/photoSearch";
  }
}
