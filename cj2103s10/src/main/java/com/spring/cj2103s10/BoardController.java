package com.spring.cj2103s10;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.cj2103s10.pagination.Pagination;
import com.spring.cj2103s10.pagination.PaginationVO;
import com.spring.cj2103s10.service.BoardService;
import com.spring.cj2103s10.vo.BoardReplyVO;
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
  	
  	PaginationVO pageVO = pagination.pagination(pag, pageSize, "board", "", "");
  	
  	List<BoardVO> vos = boardService.getBoardList(pageVO.getStartIndexNo(), pageSize);
  	
  	model.addAttribute("vos", vos);
  	model.addAttribute("pageVO", pageVO);
  	
  	return "board/bList";
  }
  
  // 조건 검색 Search List처리
  @RequestMapping(value="/bSearch", method=RequestMethod.GET)
  public String bSearchGet(String search, String searchString,
  		@RequestParam(name="pag", defaultValue="1", required=false) int pag,
			@RequestParam(name="pageSize", defaultValue="5", required=false) int pageSize,
			Model model) {
  	
  	PaginationVO pageVO = pagination.pagination(pag, pageSize, "board", search, searchString);
  	
  	List<BoardVO> vos = boardService.getBoardSearchList(pageVO.getStartIndexNo(), pageSize, search, searchString);
  	
  	String searchTitle = "";
  	if(search.equals("title")) searchTitle = "글제목"; 
  	else if(search.equals("name")) searchTitle = "글쓴이"; 
  	else searchTitle = "글내용"; 
  	
  	model.addAttribute("vos", vos);
  	model.addAttribute("pageVO", pageVO);
  	model.addAttribute("search", search);
  	model.addAttribute("searchTitle", searchTitle);
  	model.addAttribute("searchString", searchString);
  	model.addAttribute("searchCount", pageVO.getCurScrStartNo());
  	
  	return "board/bSearch";
  }
  
  @RequestMapping(value="/bInput", method=RequestMethod.GET)
  public String bInputGet() {
  	return "board/bInput";
  }
  
  @SuppressWarnings("deprecation")
	@RequestMapping(value="/bInput", method=RequestMethod.POST)
  public String bInputPost(BoardVO vo, HttpServletRequest request) {
  	
  	String uploadPath = request.getRealPath("/resources/ckeditor/images/");  // ckeditor를 통해서 저장된 모든 파일이 있는곳
 
  	// 이미지파일 시작위치(42) : src="/cj2103s10/resources/ckeditor/images/210622142615_1.png"
  	boardService.imgCheck(vo.getContent(), uploadPath, 42);  // 이미지파일을 발췌해서 'src'폴더에 복사시킨다.
  	// 위의 작업을 마치고 오면 이미지가 /src폴더로 복사완료되어 있다.
  	
  	vo.setContent(vo.getContent().replace("/resources/ckeditor/images/", "/resources/ckeditor/images/src/")); // DB에 저장되는 실제 파일 정보의 위치
  	boardService.bInputOk(vo);  // 잘 정비된 VO를 DB에 저장한다.
  	
  	return "redirect:/msg/bInputOk";
  }
  
  @RequestMapping(value="/bContent", method=RequestMethod.GET)
  public String bContentGet(Model model, int idx, int pag, int pageSize) {
  	// 조회수 증가
  	boardService.addReadNum(idx);
  	
  	// 원본글 가져오기
  	BoardVO vo = boardService.getBoardContent(idx);
  	
  	// 댓글 가져오기
  	List<BoardReplyVO> cVos = boardService.getBoardReply(idx);
  	
  	model.addAttribute("vo", vo);
  	model.addAttribute("cVos", cVos);
  	model.addAttribute("pag", pag);
  	model.addAttribute("pageSize", pageSize);
  	
  	return "board/bContent";
  }
  
  @ResponseBody
  @RequestMapping(value="/bGood", method=RequestMethod.GET)
  public String bGoodGet(int idx) {
  	boardService.addGood(idx);
  	return "";
  }
  
  @RequestMapping(value="/bContent", method=RequestMethod.POST)
  public String bContentPost(int idx, String pwd, HttpServletRequest request,
  		@RequestParam(name="pag", defaultValue="1", required=false) int pag,
			@RequestParam(name="pageSize", defaultValue="5", required=false) int pageSize,
			Model model) {
  	
  	BoardVO vo = boardService.passwordCheck(idx, pwd);
  	
  	if(vo == null) {  // 비밀번호 오류일때 처리
  		msgFlag = "boardPasswordNo$idx="+idx+"&pag="+pag+"&pageSize="+pageSize;
  		return "redirect:/msg/" + msgFlag;
  	}
  	else {
  		// 비밀번호 맞을때 처리, 먼저 기존의 src폴더의 그림파일을 images폴더로 백업시켜둔다.
	  	String uploadPath = request.getRealPath("/resources/ckeditor/images/src/");  // ckeditor를 통해서 저장된 모든 파일이 있는곳
	  	boardService.imgCheck(vo.getContent(), uploadPath, 46);
	  	
	  	msgFlag = "boardPasswordOk$idx="+idx+"&pag="+pag+"&pageSize="+pageSize;
  		return "redirect:/msg/" + msgFlag;
  	}
  }
  
  @RequestMapping(value="/bUpdate", method=RequestMethod.GET)
  public String bUpdateGet(int idx,
  		@RequestParam(name="pag", defaultValue="1", required=false) int pag,
			@RequestParam(name="pageSize", defaultValue="5", required=false) int pageSize,
			Model model) {
  	
    PaginationVO pageVO = pagination.pagination(pag, pageSize, "board", "", "");
    BoardVO vo = boardService.getBoardContent(idx);
    
  	model.addAttribute("pageVO", pageVO);
  	model.addAttribute("vo", vo);
  	
  	return "board/bUpdate";
  }
  
  @RequestMapping(value="/bUpdate", method=RequestMethod.POST)
  public String bUpdatePost(BoardVO vo, Model model, HttpServletRequest request) {
  	// 이곳에 오기전에 content안의 그림을 만약에 대비하여 image폴더에 백업받아 두었다.
  	// 수정작업이 되었고(텍스트 or 그림포함), 이때 content안의 'src='태그속성이 있다면, image에 있는 그림파일을 src폴더로 복사작업한다.
  	if(!vo.getOriContent().equals(vo.getContent()) && vo.getContent().indexOf("src=\"/") != -1) {
  		vo.setContent(vo.getContent().replace("/resources/ckeditor/images/src/", "/resources/ckeditor/images/"));
  		
  		// 기존에 src폴더에 존재하는 그림들을 모두 삭제처리한다. 
  		String uploadPath = request.getRealPath("/resources/ckeditor/images/src/");
  		boardService.imgDelete(vo.getOriContent(), uploadPath, 46);
  		
  		// src폴더의 원본 그림을 모두 삭제시킨후, 새롭게 업로드시킨 그림을 다시 src폴더에 복사한다.
  		uploadPath = request.getRealPath("/resources/ckeditor/images/");  // ckeditor를 통해서 저장된 모든 파일이 있는곳
  		 
    	// 이미지파일 시작위치(42) : src="/cj2103s10/resources/ckeditor/images/210622142615_1.png"
    	boardService.imgCheck(vo.getContent(), uploadPath, 42);  // 이미지파일을 발췌해서 'src'폴더에 복사시킨다.
    	// 위의 작업을 마치고 오면 이미지가 /src폴더로 복사완료되어 있다.
    	
    	vo.setContent(vo.getContent().replace("/resources/ckeditor/images/", "/resources/ckeditor/images/src/")); // DB에 저장되는 실제 파일 정보의 위치
  	}
  	boardService.boardUpdateOk(vo);  // 잘 정비된 VO를 DB에 저장한다.
  	
  	int pag = request.getParameter("pag")==null ? 1 : Integer.parseInt(request.getParameter("pag"));
  	int pageSize = request.getParameter("pageSize")==null ? 1 : Integer.parseInt(request.getParameter("pageSize"));
  	msgFlag = "boardUpdateOk$idx="+vo.getIdx()+"&pag="+pag+"&pageSize="+pageSize;
  	
  	return "redirect:/msg/" + msgFlag;
  }
  
  // 댓글처리(댓글저장, 처음댓글의 level과 levelOrder은 0)
  @ResponseBody
  @RequestMapping(value="/bReplyInsert", method=RequestMethod.GET)
  public String bReplyInsertGet(BoardReplyVO cVo) {
  	// 현재 본문글에 해당하는 댓글의 levelOrder값을 구한다.
  	int levelOrder = 0;
  	String strLevelOrder = boardService.maxLevelOrder(cVo.getBoardIdx());
  	if(strLevelOrder != null) levelOrder = Integer.parseInt(strLevelOrder) + 1;
  	cVo.setLevelOrder(levelOrder);
  	
  	boardService.setReplyInsert(cVo);
  	//System.out.println("cVo : " + cVo);
  	
  	return "1";
  }
  
  @ResponseBody
  @RequestMapping(value="/bReplyDelete", method=RequestMethod.GET)
  public String bReplyDeleteGet(int replyIdx) {
  	boardService.setReplyDelete(replyIdx);
  	return "";
  }
  
  // 부모댓글에 대한 답변글(대댓글) 입력처리
  @ResponseBody
  @RequestMapping(value="/bReplyInsert2", method=RequestMethod.GET)
  public String bReplyInsert2Get(BoardReplyVO cVo) {
  	boardService.levelOrderPlusUpdate(cVo); 	// 부모댓글의 levelOrder값보다 큰 모든 댓글의 levelOrder값을 +1 시켜준다.(update)
  	cVo.setLevel(cVo.getLevel()+1);						// 자신의 level은 부모 level보다 +1 한다.
  	cVo.setLevelOrder(cVo.getLevelOrder()+1);	// 자신의 levelOrder은 부모 levelOrder보다 +1 한다.
  	
  	boardService.bReplyInsert2(cVo);		// 모든 설정을 마친 cVo에 담긴 내용을 댓글테이블에 등록한다.
  	
  	return "";
  }
}
