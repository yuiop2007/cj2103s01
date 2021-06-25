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
import com.spring.cj2103s01.service.EventService;
import com.spring.cj2103s01.service.ImageService;
import com.spring.cj2103s01.service.NoticeService;
import com.spring.cj2103s01.vo.EventVO;
import com.spring.cj2103s01.vo.NoticeVO;

@Controller
@RequestMapping("/board")
public class BoardController {
	String msgFlag = "";

	@Autowired
	NoticeService noticeService;

	@Autowired
	EventService eventService;

	@Autowired
	Pagenation pagenation;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	ImageService imageService;

	@RequestMapping(value = "/notice", method = RequestMethod.GET)
	public String noticeGet(@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize, Model model) {
		if (pag < 1) {
			pag = 1;
		}

		PagenationVO pageVO = pagenation.pagenation(pag, pageSize, "notice", "", "");

		List<NoticeVO> vos = noticeService.getNoticeList(pageVO.getStartIndexNo(), pageSize);

		model.addAttribute("vos", vos);
		model.addAttribute("pageVO", pageVO);

		return "board/notice";
	}

	@RequestMapping(value = "/nInput", method = RequestMethod.GET)
	public String nInputGet() {

		return "board/noticeInput";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/nInput", method = RequestMethod.POST)
	public String nInputPost(MultipartFile file, NoticeVO vo, HttpServletRequest request) {
		vo.setnPwd(bCryptPasswordEncoder.encode(vo.getnPwd()));

		String root = "nInput";
		String uploadPath = request.getRealPath("/resources/ckeditor/images/"); // ckeditor를 통해서 저장된 모든 파일이 있는곳

		// 이미지파일 시작위치(42) :
		// src="/cj2103s01/resources/ckeditor/images/210622142615_1.png"

		imageService.imgCheck(vo.getnContent(), uploadPath, 42); // 이미지파일을 발췌해서 'src'폴더에 복사시킨다.

		vo.setnContent(vo.getnContent().replace("/resources/ckeditor/images/", "/resources/ckeditor/images/src/"));

		int res = noticeService.setNoticeInput(file, vo, root);

		if (res == 1)
			msgFlag = "nInputOk";
		else
			msgFlag = "nInputNo";

		return "redirect:/msg/" + msgFlag;
	}

	// 조건 검색 Search List처리
	@RequestMapping(value = "/nSearch", method = RequestMethod.GET)
	public String nSearchGet(String search, String searchString,
			@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize, Model model) {
		if (pag < 1) {
			pag = 1;
		}

		PagenationVO pageVO = pagenation.pagenation(pag, pageSize, "notice", search, searchString);

		List<NoticeVO> vos = noticeService.getNoticeSearchList(pageVO.getStartIndexNo(), pageSize, search,
				searchString);

		String searchTitle = "";
		if (search.equals("nTitle")) {
			searchTitle = "제목";
		} else {
			searchTitle = "글쓴이";
		}

		model.addAttribute("vos", vos);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("search", search);
		model.addAttribute("searchTitle", searchTitle);
		model.addAttribute("searchString", searchString);
		model.addAttribute("searchCount", pageVO.getTotRecCnt());

		return "board/nSearch";
	}

	@RequestMapping(value = "/nContent", method = RequestMethod.GET)
	public String nContentGet(Model model, int idx, int pag, int pageSize) {
		// 조회수 증가
		noticeService.addReadNum(idx);

		// 원본글 가져오기
		NoticeVO vo = noticeService.getNoticeContent(idx);
		model.addAttribute("vo", vo);
		model.addAttribute("pag", pag);
		model.addAttribute("pageSize", pageSize);

		return "board/nContent";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/nContent", method = RequestMethod.POST)
	public String nContentPost(int idx, String nPwd, HttpServletRequest request,
			@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize, Model model) {
		NoticeVO vo = noticeService.getIdCheck(idx);

		if (!bCryptPasswordEncoder.matches(nPwd, vo.getnPwd())) { // 비밀번호 오류일때 처리
			msgFlag = "noticePasswordNo$idx=" + idx + "&pag=" + pag + "&pageSize=" + pageSize;
			return "redirect:/msg/" + msgFlag;
		} else {
			// 비밀번호 맞을때 처리, 먼저 기존의 src폴더의 그림파일을 images폴더로 백업시켜둔다.
			String uploadPath = request.getRealPath("/resources/ckeditor/images/src/"); // ckeditor를 통해서 저장된 모든 파일이 있는곳
			imageService.imgCheck(vo.getnContent(), uploadPath, 46);

			msgFlag = "noticePasswordOk$idx=" + idx + "&pag=" + pag + "&pageSize=" + pageSize;
			return "redirect:/msg/" + msgFlag;
		}
	}

	@RequestMapping(value = "/nUpdate", method = RequestMethod.GET)
	public String nUpdateGet(int idx, @RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize, Model model) {

		PagenationVO pageVO = pagenation.pagenation(pag, pageSize, "notice", "", "");
		NoticeVO vo = noticeService.getNoticeContent(idx);

		model.addAttribute("pageVO", pageVO);
		model.addAttribute("vo", vo);

		return "board/nUpdate";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/nUpdate", method = RequestMethod.POST)
	public String nUpdatePost(MultipartFile file, NoticeVO vo, Model model, HttpServletRequest request) {
		vo.setnPwd(bCryptPasswordEncoder.encode(vo.getnPwd()));

		// 이곳에 오기전에 content안의 그림을 만약에 대비하여 image폴더에 백업받아 두었다.
		// 수정작업이 되었고(텍스트 or 그림포함), 이때 content안의 'src='태그속성이 있다면, image에 있는 그림파일을 src폴더로
		// 복사작업한다.
		if (!vo.getOriContent().equals(vo.getnContent()) && vo.getnContent().indexOf("src=\"/") != -1) {
			vo.setnContent(vo.getnContent().replace("/resources/ckeditor/images/src/", "/resources/ckeditor/images/"));

			// 기존에 src폴더에 존재하는 그림들을 모두 삭제처리한다.
			String uploadPath = request.getRealPath("/resources/ckeditor/images/src/");
			imageService.imgDelete(vo.getOriContent(), uploadPath, 46);

			// src폴더의 원본 그림을 모두 삭제시킨후, 새롭게 업로드시킨 그림을 다시 src폴더에 복사한다.
			uploadPath = request.getRealPath("/resources/ckeditor/images/"); // ckeditor를 통해서 저장된 모든 파일이 있는곳

			// 이미지파일 시작위치(42) :
			// src="/cj2103s10/resources/ckeditor/images/210622142615_1.png"
			imageService.imgCheck(vo.getnContent(), uploadPath, 42); // 이미지파일을 발췌해서 'src'폴더에 복사시킨다.
			// 위의 작업을 마치고 오면 이미지가 /src폴더로 복사완료되어 있다.

			vo.setnContent(vo.getnContent().replace("/resources/ckeditor/images/", "/resources/ckeditor/images/src/"));
		}

		noticeService.noticeUpdateOk(vo); // 잘 정비된 VO를 DB에 저장한다.

		int pag = request.getParameter("pag") == null ? 1 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize") == null ? 1
				: Integer.parseInt(request.getParameter("pageSize"));
		msgFlag = "noticeUpdateOk$idx=" + vo.getnId() + "&pag=" + pag + "&pageSize=" + pageSize;

		return "redirect:/msg/" + msgFlag;
	}

	@RequestMapping(value = "/nDelete", method = RequestMethod.POST)
	public String nDeleteGet(int idx, String nPwd, HttpServletRequest request,
			@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize, Model model) {
		NoticeVO vo = noticeService.getIdCheck(idx);

		if (!bCryptPasswordEncoder.matches(nPwd, vo.getnPwd())) { // 비밀번호 오류일때 처리
			msgFlag = "noticePasswordNo$idx=" + idx + "&pag=" + pag + "&pageSize=" + pageSize;

			return "redirect:/msg/" + msgFlag;
		} else {
			noticeService.nDelete(idx);

			msgFlag = "noticeDeleteOk$pag=" + pag + "&pageSize=" + pageSize;
			return "redirect:/msg/" + msgFlag;
		}
	}

	@RequestMapping(value = "/qna", method = RequestMethod.GET)
	public String qnaGet() {

		return "board/qna";
	}

	@RequestMapping(value = "/event", method = RequestMethod.GET)
	public String eventGet(@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize, Model model) {
		if (pag < 1) {
			pag = 1;
		}

		PagenationVO pageVO = pagenation.pagenation(pag, pageSize, "event", "", "");

		List<EventVO> vos = eventService.getEventList(pageVO.getStartIndexNo(), pageSize);

		model.addAttribute("vos", vos);
		model.addAttribute("pageVO", pageVO);

		return "board/event";
	}

	// 조건 검색 Search List처리
	@RequestMapping(value = "/eSearch", method = RequestMethod.GET)
	public String eSearchGet(String search, String searchString,
			@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize, Model model) {
		if (pag < 1) {
			pag = 1;
		}

		PagenationVO pageVO = pagenation.pagenation(pag, pageSize, "event", search, searchString);

		List<EventVO> vos = eventService.getEventSearchList(pageVO.getStartIndexNo(), pageSize, search, searchString);

		String searchTitle = "";
		if (search.equals("eTitle")) {
			searchTitle = "제목";
		} else {
			searchTitle = "글쓴이";
		}

		model.addAttribute("vos", vos);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("search", search);
		model.addAttribute("searchTitle", searchTitle);
		model.addAttribute("searchString", searchString);
		model.addAttribute("searchCount", pageVO.getTotRecCnt());

		return "board/eSearch";
	}

	@RequestMapping(value = "/eInput", method = RequestMethod.GET)
	public String eInputGet() {

		return "board/eventInput";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/eInput", method = RequestMethod.POST)
	public String eInputPost(MultipartFile file, EventVO vo, HttpServletRequest request) {
		vo.setePwd(bCryptPasswordEncoder.encode(vo.getePwd()));

		String root = "eInput";
		String uploadPath = request.getRealPath("/resources/ckeditor/images/"); // ckeditor를 통해서 저장된 모든 파일이 있는곳

		// 이미지파일 시작위치(42) :
		// src="/cj2103s01/resources/ckeditor/images/210622142615_1.png"

		imageService.imgCheck(vo.geteContent(), uploadPath, 42); // 이미지파일을 발췌해서 'src'폴더에 복사시킨다.

		// DB에 저장되는 실제 파일 정보의 위치
		vo.seteContent(vo.geteContent().replace("/resources/ckeditor/images/", "/resources/ckeditor/images/src/"));

		int res = eventService.setEventInput(file, vo, root);

		if (res == 1)
			msgFlag = "eInputOk";
		else
			msgFlag = "eInputNo";

		return "redirect:/msg/" + msgFlag;
	}

	@RequestMapping(value = "/eContent", method = RequestMethod.GET)
	public String eContentGet(Model model, int idx, int pag, int pageSize) {
		// 조회수 증가
		eventService.addReadNum(idx);

		// 원본글 가져오기
		EventVO vo = eventService.getEventContent(idx);
		model.addAttribute("vo", vo);
		model.addAttribute("pag", pag);
		model.addAttribute("pageSize", pageSize);

		return "board/eContent";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/eContent", method = RequestMethod.POST)
	public String eContentPost(int idx, String ePwd, HttpServletRequest request,
			@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize, Model model) {
		EventVO vo = eventService.getIdCheck(idx);

		if (!bCryptPasswordEncoder.matches(ePwd, vo.getePwd())) { // 비밀번호 오류일때 처리
			msgFlag = "eventPasswordNo$idx=" + idx + "&pag=" + pag + "&pageSize=" + pageSize;
			return "redirect:/msg/" + msgFlag;
		} else {
			// 비밀번호 맞을때 처리, 먼저 기존의 src폴더의 그림파일을 images폴더로 백업시켜둔다.
			String uploadPath = request.getRealPath("/resources/ckeditor/images/src/"); // ckeditor를 통해서 저장된 모든 파일이 있는곳
			imageService.imgCheck(vo.geteContent(), uploadPath, 46);

			msgFlag = "eventPasswordOk$idx=" + idx + "&pag=" + pag + "&pageSize=" + pageSize;
			return "redirect:/msg/" + msgFlag;
		}
	}

	@RequestMapping(value = "/eUpdate", method = RequestMethod.GET)
	public String eUpdateGet(int idx, @RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize, Model model) {

		PagenationVO pageVO = pagenation.pagenation(pag, pageSize, "event", "", "");
		EventVO vo = eventService.getEventContent(idx);

		model.addAttribute("pageVO", pageVO);
		model.addAttribute("vo", vo);

		return "board/eUpdate";
	}

	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/eUpdate", method = RequestMethod.POST)
	public String eUpdatePost(MultipartFile file, EventVO vo, Model model, HttpServletRequest request) {
		vo.setePwd(bCryptPasswordEncoder.encode(vo.getePwd()));

		// 이곳에 오기전에 content안의 그림을 만약에 대비하여 image폴더에 백업받아 두었다.
		// 수정작업이 되었고(텍스트 or 그림포함), 이때 content안의 'src='태그속성이 있다면, image에 있는 그림파일을 src폴더로
		// 복사작업한다.
		if (!vo.getOriContent().equals(vo.geteContent()) && vo.geteContent().indexOf("src=\"/") != -1) {
			vo.seteContent(vo.geteContent().replace("/resources/ckeditor/images/src/", "/resources/ckeditor/images/"));

			// 기존에 src폴더에 존재하는 그림들을 모두 삭제처리한다.
			String uploadPath = request.getRealPath("/resources/ckeditor/images/src/");
			imageService.imgDelete(vo.getOriContent(), uploadPath, 46);

			// src폴더의 원본 그림을 모두 삭제시킨후, 새롭게 업로드시킨 그림을 다시 src폴더에 복사한다.
			uploadPath = request.getRealPath("/resources/ckeditor/images/"); // ckeditor를 통해서 저장된 모든 파일이 있는곳

			// 이미지파일 시작위치(42) :
			// src="/cj2103s10/resources/ckeditor/images/210622142615_1.png"
			imageService.imgCheck(vo.geteContent(), uploadPath, 42); // 이미지파일을 발췌해서 'src'폴더에 복사시킨다.
			// 위의 작업을 마치고 오면 이미지가 /src폴더로 복사완료되어 있다.

			vo.seteContent(vo.geteContent().replace("/resources/ckeditor/images/", "/resources/ckeditor/images/src/"));
		}

		eventService.eventUpdateOk(vo); // 잘 정비된 VO를 DB에 저장한다.

		int pag = request.getParameter("pag") == null ? 1 : Integer.parseInt(request.getParameter("pag"));
		int pageSize = request.getParameter("pageSize") == null ? 1
				: Integer.parseInt(request.getParameter("pageSize"));
		msgFlag = "eventUpdateOk$idx=" + vo.geteId() + "&pag=" + pag + "&pageSize=" + pageSize;

		return "redirect:/msg/" + msgFlag;
	}

	@RequestMapping(value = "/eDelete", method = RequestMethod.POST)
	public String eDeleteGet(int idx, String ePwd, HttpServletRequest request,
			@RequestParam(name = "pag", defaultValue = "1", required = false) int pag,
			@RequestParam(name = "pageSize", defaultValue = "10", required = false) int pageSize, Model model) {
		EventVO vo = eventService.getIdCheck(idx);

		if (!bCryptPasswordEncoder.matches(ePwd, vo.getePwd())) { // 비밀번호 오류일때 처리
			msgFlag = "eventPasswordNo$idx=" + idx + "&pag=" + pag + "&pageSize=" + pageSize;
			return "redirect:/msg/" + msgFlag;
		} else {
			eventService.eDelete(idx);

			msgFlag = "eventDeleteOk$pag=" + pag + "&pageSize=" + pageSize;
			return "redirect:/msg/" + msgFlag;
		}
	}
}
