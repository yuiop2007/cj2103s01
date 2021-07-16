package com.spring.cj2103s01.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s01.dao.SlideDAO;
import com.spring.cj2103s01.vo.SlideVO;

@Service
public class SlideServiceImpl implements SlideService{
	
	@Autowired
	SlideDAO slideDAO;
	
	@Autowired
	FileService fileService;
	
	

	@Override
	public List<SlideVO> getSlideList() {
		return slideDAO.getSlideList();
	}

	@Override
	public int getSlideTotCnt() {
		return slideDAO.getSlideTotCnt();
	}

	@Override
	public int setSlideInput(MultipartFile file, SlideVO vo, String root) {
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
				vo.setsImage(saveFileName);
			} else {
				res = 0;
			}
			// 정상 파일 업로드된 자료의 DB처리
			slideDAO.setSlideInput(vo);
			res = 1;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public String getImageSid(int sId) {
		return slideDAO.getImageSid(sId);
	}

	@Override
	public void sDelete(int sId) {
		slideDAO.sDelete(sId);
	}

	@Override
	public void sNumUpdate(int sId, int sNum) {
		slideDAO.sNumUpdate(sId, sNum);
	}

	@Override
	public List<SlideVO> getSlideMainList() {
		return slideDAO.getSlideMainList();
	}
}
