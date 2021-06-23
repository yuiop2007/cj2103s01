package com.spring.cj2103s01.pagenation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cj2103s01.dao.EventDAO;
import com.spring.cj2103s01.dao.NoticeDAO;
import com.spring.cj2103s01.dao.ProductDAO;

@Service
public class Pagenation {
	@Autowired
	NoticeDAO noticeDAO;

	@Autowired
	ProductDAO productDAO;

	@Autowired
	EventDAO eventDAO;

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
//				totRecCnt = productDAO.totSearchRecCnt(search, searchString);
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
