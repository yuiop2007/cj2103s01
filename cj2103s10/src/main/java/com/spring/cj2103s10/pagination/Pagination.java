package com.spring.cj2103s10.pagination;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cj2103s10.dao.GuestDAO;

@Service
public class Pagination {
  @Autowired
  GuestDAO guestDAO;
  
//  @Autowired
//  BoardDAO boardDAO;

	public PaginationVO pagination(int pag, int pageSize, String partName, String partValue) {
		int blockSize = 3;
		int totRecCnt = 0;
		
		PaginationVO pageVo = new PaginationVO();
		
		if(partName.equals("guest")) {
		  totRecCnt = guestDAO.totRecCnt();
		}
//		else if(partName.equals("board")) {
//			totRecCnt = boardDAO.totRecCnt();
//		}
		
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
