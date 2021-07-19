package com.spring.cj2103s10.pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cj2103s10.dao.BoardDAO;
import com.spring.cj2103s10.dao.GuestDAO;
import com.spring.cj2103s10.dao.MemberDAO;
import com.spring.cj2103s10.dao.PdsmDAO;
import com.spring.cj2103s10.dao.ReservationDAO;
import com.spring.cj2103s10.dao.VoteDAO;

@Service
public class Pagination {
  @Autowired
  GuestDAO guestDAO;
  
  @Autowired
  MemberDAO memberDAO;
  
  @Autowired
  BoardDAO boardDAO;
  
  @Autowired
  PdsmDAO pdsmDAO;
  
  @Autowired
  ReservationDAO reservationDAO;
  
  @Autowired
  VoteDAO voteDAO;

  // 인자 : 1.page번호, 2.page크기, 3.소속(예:게시판-board, 멀티자료실:pdms), 4.part(분류)-예:'전체'/'여행', 5.검색어
	public PaginationVO pagination(int pag, int pageSize, String partName, String partValue, String searchString) {
		int blockSize = 3;
		int totRecCnt = 0;
		
		PaginationVO pageVo = new PaginationVO();
		
		if(partName.equals("guest")) {
		  totRecCnt = guestDAO.totRecCnt();
		}
		else if(partName.equals("member")) {
			if(!partValue.equals("")) {
				totRecCnt = memberDAO.totRecCntMid(partValue);
			}
			else if(searchString.equals("")) {
				totRecCnt = memberDAO.totRecCnt();
			}
			else {
				totRecCnt = memberDAO.totRecCntLevel(searchString);
			}
		}
		else if(partName.equals("board")) {
			if(searchString.equals("")) {
			  totRecCnt = boardDAO.totRecCnt();
			}
			else {
				String search = partValue;
				totRecCnt = boardDAO.totSearchRecCnt(search, searchString);
			}
		}
		else if(partName.equals("pdsm")) {
			totRecCnt = pdsmDAO.totRecCnt(partValue);
		}
		else if(partName.equals("reservation")) {
			String room = partValue;
			totRecCnt = reservationDAO.totRecCnt(room);
		}
		else if(partName.equals("vote")) {
			totRecCnt = voteDAO.totRecCnt();
		}
		
		int totPage = (totRecCnt % pageSize)==0 ? totRecCnt / pageSize : (int)(totRecCnt / pageSize) +1;
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
