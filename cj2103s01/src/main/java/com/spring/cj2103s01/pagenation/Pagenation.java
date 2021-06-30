package com.spring.cj2103s01.pagenation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cj2103s01.dao.EventDAO;
import com.spring.cj2103s01.dao.MemberDAO;
import com.spring.cj2103s01.dao.NoticeDAO;
import com.spring.cj2103s01.dao.ProductDAO;
import com.spring.cj2103s01.dao.QnaDAO;
import com.spring.cj2103s01.dao.ReviewDAO;
import com.spring.cj2103s01.dao.WishDAO;

@Service
public class Pagenation {
	@Autowired
	NoticeDAO noticeDAO;

	@Autowired
	ProductDAO productDAO;

	@Autowired
	EventDAO eventDAO;
	
	@Autowired
	ReviewDAO reviewDAO;
	
	@Autowired
	QnaDAO qnaDAO;
	
	@Autowired
	WishDAO wishDAO;
	
	@Autowired
	MemberDAO memberDAO;

	public PagenationVO pagenation(int pag, int pageSize, String partName, String partValue, String searchString) {
		int blockSize = 5;
		int totRecCnt = 0;
		String search = partValue;
		
		PagenationVO pageVo = new PagenationVO();

		if (partName.equals("notice")) {
			if (searchString.equals("")) {
				totRecCnt = noticeDAO.totRecCnt();
			}
			else {
				totRecCnt = noticeDAO.totSearchRecCnt(search, searchString);
			}
		}
		else if (partName.equals("pList")) {
			if (searchString.equals("")) {
				totRecCnt = productDAO.totRecCnt();
			}
			else {
				totRecCnt = productDAO.totSearchRecCnt(search, searchString);
			}
		}
		else if (partName.equals("event")) {
			if (searchString.equals("")) {
				totRecCnt = eventDAO.totRecCnt();
			}
			else {
				totRecCnt = eventDAO.totSearchRecCnt(search, searchString);
			}
		}
		else if (partName.equals("review")) {
			if (searchString.equals("")) {
				totRecCnt = reviewDAO.totRecCnt();
			}
			else {
				totRecCnt = reviewDAO.totSearchRecCnt(search, searchString);
			}
		}
		else if (partName.equals("qna")) {
			if (searchString.equals("")) {
				totRecCnt = qnaDAO.totRecCnt();
			}
			else {
				System.out.println(search);
				totRecCnt = qnaDAO.totSearchRecCnt(search, searchString);
			}
		}
		else if(partName.equals("preview")) {
			int pId = Integer.parseInt(search);
			totRecCnt = reviewDAO.totpIdRecCnt(pId);
		}
		else if(partName.equals("pqna")) {
			int pId = Integer.parseInt(search);
			totRecCnt = qnaDAO.totpIdRecCnt(pId);
		}
		else if(partName.equals("mywish")){
			totRecCnt = wishDAO.totWishRecCnt(search);
		}
		else if (partName.equals("mList")) {
			if (searchString.equals("")) {
				totRecCnt = memberDAO.totMemberCnt();
			}
			else {
				System.out.println(search);
				totRecCnt = memberDAO.totSearchRecCnt(search, searchString);
			}
		}

		int totPage = (totRecCnt % pageSize) == 0 ? totRecCnt / pageSize : (int) (totRecCnt / pageSize) + 1;
		int startIndexNo = (pag - 1) * pageSize;
		int curScrStartNo = totRecCnt - startIndexNo;

		pageVo.setPag(pag);
		pageVo.setPageSize(pageSize);
		pageVo.setBlockSize(blockSize);
		pageVo.setTotRecCnt(totRecCnt);
		pageVo.setTotPage(totPage);
		pageVo.setStartIndexNo(startIndexNo);
		pageVo.setCurScrStartNo(curScrStartNo);

		return pageVo;
	}

}
