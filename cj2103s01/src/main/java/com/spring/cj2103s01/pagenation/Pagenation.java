package com.spring.cj2103s01.pagenation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.spring.cj2103s01.dao.NoticeDAO;

@Service
public class Pagenation {
  @Autowired
  NoticeDAO noticeDAO;
  
  
//  @Autowired
//  BoardDAO boardDAO;

	public PagenationVO pagenation(int pag, int pageSize, String partName, String partValue) {
		int blockSize = 5;
		int totRecCnt = 0;
		
		PagenationVO pageVo = new PagenationVO();
		
		if(partName.equals("notice")) {
		  totRecCnt = noticeDAO.totRecCnt();
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
