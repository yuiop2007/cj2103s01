package com.spring.cj2103s01.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s01.dao.NoticeDAO;
import com.spring.cj2103s01.vo.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	NoticeDAO noticeDAO;
	
	@Autowired
	FileService fileService;
	
	@Override
	public List<NoticeVO> getNoticeList(int startIndexNo, int pageSize) {
		return noticeDAO.getNoticeList(startIndexNo, pageSize);
	}

	@Override
	public int setNoticeInput(MultipartFile file, NoticeVO vo, String root) {
		int res = 0;
		try {
			String oFileName = file.getOriginalFilename();
			if (oFileName != "") {
				// 파일 업로드처리 과정(파일명 중복을 방지시킨후 파일을 업로드한다.)

				// UUID를 이용한 파일명 중복방지처리
				UUID uid = UUID.randomUUID();
				String saveFileName = uid + "_" + oFileName;
				
				
				// 파일업로드처리
				fileService.writeFile(file, saveFileName, root); // 메소드를 통해서 파일을 서버에 저장시킨다.
				vo.setnFile(saveFileName);
			} else {
				res = 0;
			}
			// 정상 파일 업로드된 자료의 DB처리
			noticeDAO.setNoticeInput(vo);
			res = 1;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public List<NoticeVO> getNoticeSearchList(int startIndexNo, int pageSize, String search, String searchString) {
		return noticeDAO.getNoticeSearchList(startIndexNo, pageSize, search, searchString);
	}

	@Override
	public void addReadNum(int idx) {
		noticeDAO.addReadNum(idx);
	}

	@Override
	public NoticeVO getNoticeContent(int idx) {
		return noticeDAO.getNoticeContent(idx);
	}

	@Override
	public NoticeVO getIdCheck(int idx) {
		return noticeDAO.getIdCheck(idx);
	}

	@Override
	public void noticeUpdateOk(NoticeVO vo) {
		noticeDAO.noticeUpdateOk(vo);
	}

	@Override
	public void nDelete(int idx) {
		noticeDAO.nDelete(idx);
	}

}
