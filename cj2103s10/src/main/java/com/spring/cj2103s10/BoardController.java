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
import com.spring.cj2103s10.service.BoardService;
import com.spring.cj2103s10.vo.BoardVO;

@Controller
@RequestMapping("/board")
public class BoardController {
  String msgFlag = "";
  
  @Autowired
  BoardService boardService;
	
  @Autowired
  Pagination pagination;
  
  @RequestMapping(value="/bList", method=RequestMethod.GET)
  public String bListGet(@RequestParam(name="pag", defaultValue="1", required=false) int pag,
			@RequestParam(name="pageSize", defaultValue="5", required=false) int pageSize,
			Model model) {
  	
  	PaginationVO pageVO = pagination.pagination(pag, pageSize, "board", "");
  	
  	List<BoardVO> vos = boardService.getBoardList(pageVO.getStartIndexNo(), pageSize);
  	
  	model.addAttribute("vos", vos);
  	model.addAttribute("pageVO", pageVO);
  	
  	return "board/bList";
  }
  
  @RequestMapping(value="/bInput", method=RequestMethod.GET)
  public String bInputGet() {
  	return "board/bInput";
  }
  
  @RequestMapping(value="/bInput", method=RequestMethod.POST)
  public String bInputPost(BoardVO vo, HttpServletRequest request) {
  	System.out.println("vo : " + vo);
  	
  	String uploadPath = request.getRealPath("/resources/ckeditor/images/");  // ckeditor를 통해서 저장된 모든 파일이 있는곳
 
  	System.out.println("그림저장하러 갑니다.");
  	// 이미지파일 시작위치(42) : src="/cj2103s10/resources/ckeditor/images/210622142615_1.png"
  	boardService.imgCheck(vo.getContent(), uploadPath, 42);  // 이미지파일을 발췌해서 'src'폴더에 복사시킨다.
  	System.out.println("그림저장하러 다녀왔습니다..");
  	
  	vo.setContent(vo.getContent().replace("/resources/ckeditor/images/", "/resources/ckeditor/images/src/")); // DB에 저장되는 실제 파일 정보의 위치
  	boardService.bInputOk(vo);  // 잘 정비된 VO를 DB에 저장한다.
  	
  	return "redirect:/msg/bInputOk";
  }
}
