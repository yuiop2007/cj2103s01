package com.spring.cj2103s10.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s10.dao.StudyDAO;
import com.spring.cj2103s10.vo.AreaVO;
import com.spring.cj2103s10.vo.CartTestVO;
import com.spring.cj2103s10.vo.ThumbnailVO;

@Service
public class StudyImpl implements Study {

	@Autowired
	StudyDAO studyDAO;

	@Override
	public String[] getAddress1() {
		return studyDAO.getAddress1();
	}

	@Override
	public List<AreaVO> getAddress2(String address1) {
		return studyDAO.getAddress2(address1);
	}

	@Override
	public AreaVO getAddressSearch(String address1, String address2) {
		return studyDAO.getAddressSearch(address1, address2);
	}

	@Override
	public void getCalendar() {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		// 오늘 날짜 저장
		Calendar calToday = Calendar.getInstance();
		int toYear = calToday.get(Calendar.YEAR);
		int toMonth = calToday.get(Calendar.MONTH);
		int toDay = calToday.get(Calendar.DATE);
				
		// 화면에 보여줄 해당년월을 셋팅하는 부분
		Calendar calView = Calendar.getInstance();
		int yy = request.getParameter("yy")==null ? calView.get(Calendar.YEAR) : Integer.parseInt(request.getParameter("yy"));
	  int mm = request.getParameter("mm")==null ? calView.get(Calendar.MONTH) : Integer.parseInt(request.getParameter("mm"));
	  
	  if(mm < 0) { // 1월에서 전월 버튼을 클릭시에 실행
	  	yy--;
	  	mm = 11;
	  }
	  if(mm > 11) { // 12월에서 다음월 버튼을 클릭시에 실행
	  	yy++;
	  	mm = 0;
	  }
	  calView.set(yy, mm, 1);
	  
	  int startWeek = calView.get(Calendar.DAY_OF_WEEK);  // 해당 '년/월'의 1일에 해당하는 요일값을 가져온다.
	  int lastDay = calView.getActualMaximum(Calendar.DAY_OF_MONTH);  // 해당월의 마지막일자를 구한다.
	  
	  // 화면에 보여줄 년월기준 전년도/다음년도를 구하기 위한 부분
	  int prevYear = yy;  			// 전년도
	  int prevMonth = (mm) - 1; // 이전월
	  int nextYear = yy;  			// 다음년도
	  int nextMonth = (mm) + 1; // 다음월
	  
	  if(prevMonth == -1) {  // 1월에서 전월 버튼을 클릭시에 실행..
	  	prevYear--;
	  	prevMonth = 11;
	  }
	  
	  if(nextMonth == 12) {  // 12월에서 다음월 버튼을 클릭시에 실행..
	  	nextYear++;
	  	nextMonth = 0;
	  }
	  
	  // 이전달력과 다음달력처리부분
	  Calendar calPre = Calendar.getInstance();  // 이전달력
	  calPre.set(prevYear, prevMonth, 1);  // 이전 달력 셋팅
	  int preLastDay = calPre.getActualMaximum(Calendar.DAY_OF_MONTH);  // 해당월의 마지막일자를 구한다.
	  
	  Calendar calNext = Calendar.getInstance();  // 다음달력
	  calNext.set(nextYear, nextMonth, 1);  // 다음 달력 셋팅
	  int nextStartWeek = calNext.get(Calendar.DAY_OF_WEEK);  // 다음달의 1일에 해당하는 요일값을 가져온다.
	  
	  // 아래는 sql 쿼리작업에서 dataformat의 비교형식을 맞춰주기위해 날짜형식을 변환하였다.
	  // 해당월의 일자를 처리시에 값이 2021-1  -> 2021-01   , 2021-10 은 변환 불필요.
	  /*
	  String ym = "";
	  int tmpMM = (mm + 1);
	  if(tmpMM >= 1 && tmpMM <= 9) {
	  	ym = yy + "-0" + (mm + 1);
	  }
	  else {
	  	ym = yy + "-" + (mm + 1);
	  }
	  */
	  
	  /* ---------  아래는  앞에서 처리된 값들을 모두 request객체에 담는다.  -----------------  */
	  
	  // 오늘기준 달력...
	  request.setAttribute("toYear", toYear);
	  request.setAttribute("toMonth", toMonth);
	  request.setAttribute("toDay", toDay);
	  
	  // 화면에 보여줄 해당 달력...
	  request.setAttribute("yy", yy);
	  request.setAttribute("mm", mm);
	  request.setAttribute("startWeek", startWeek);
	  request.setAttribute("lastDay", lastDay);
	  
	  // 화면에 보여줄 해당 달력 기준의 전년도, 전월, 다음년도, 다음월 ...
	  request.setAttribute("preYear", prevYear);
		request.setAttribute("preMonth", prevMonth);
		request.setAttribute("preLastDay", preLastDay);
		request.setAttribute("nextYear", nextYear);
		request.setAttribute("nextMonth", nextMonth);
		request.setAttribute("nextStartWeek", nextStartWeek);
	}

	@Override
	public ThumbnailVO thumbnailCreate(MultipartFile file) {
		ThumbnailVO vo = new ThumbnailVO();
		try {
			String oFileName = file.getOriginalFilename();
			String fileExt = oFileName.substring(oFileName.lastIndexOf(".")+1);
			
			// 업로드된 파일의 이름 중복방지를 위한 처리
			UUID uid = UUID.randomUUID();
			String saveFileName = uid + "_" + oFileName;
			oImageFileWrite(file, saveFileName);  // 원본 이미지 저장하기 메소드 생성호출
			
			// 아래로, 썸네임 이미지 만들어서 저장하기
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
			String imsiUploadPath = request.getSession().getServletContext().getRealPath("/resources/study/thumbnail/");
			String uploadPath = imsiUploadPath + saveFileName;
			
			BufferedImage srcImg = ImageIO.read(new File(uploadPath));
			
			int ow = srcImg.getWidth();   // 서버에 저장된 원본이미지의 폭을 가져온다.
			int oh = srcImg.getHeight();  // 서버에 저장된 원본이미지의 높이를 가져온다.
			
			// 썸네일 이미지로 저장할 그림파일의 폭과 높이를 지정한다.(4:3 비율 -> 200px(폭) / 4 * 3 = 150px(너비))
			int tw = 200, th = 150;
			
			// 원본그림을 재지정된(w:200, h:150) 그림으로 변경처리
			int rw = ow;                              // rw =2000, 
			int rh = (ow * th) / tw;
			
			// 재계산된 높이가 원본보다 높다면 잘랐을때 위쪽이 공백으로 채워진다. 따라서 폭과 높이를 서로 바꿔서 다시 계산해 준다.
			if(rh > oh) {
				rh = oh;
				rw = (oh * tw) / th;
			}
			
			// 재계산이 완료되면 계산된 크기만큼 원본 이미지를 잘라서 저장한다.
			Scalr.crop(srcImg, (ow-rw)/2, (oh-rh)/2, rw, rh);
			
		  // 변경된 사이즈로 정보를 다시 읽어온다.
			BufferedImage resizeImg = Scalr.resize(srcImg, tw, th);  
			
			// 새롭게 재 계산되어 처리(잘라진)된 썸네일을 화일명을 붙여서 저장한다.
			String imsiFileName = imsiUploadPath + "s_" + saveFileName;
			String thumbnailFileName = "s_" + saveFileName + "." + fileExt;
			
			File tFileName = new File(imsiFileName);
			ImageIO.write(resizeImg, fileExt, tFileName);  // 썸네일 이미지 저장처리

			// 이미지가 정상처리되어 저장된후 DB작업을 위해 VO에 담는다.
			vo.setRes(1);
			vo.setoFileName(saveFileName);
			vo.settFileName(thumbnailFileName);
		} catch (IOException e) {
			vo.setRes(0);
			e.printStackTrace();
		}
		// DB작업처리 할 것 있으면 이곳에서 처리한다.
		
		return vo;
	}

	// 원본이미지 저장메소드
	private void oImageFileWrite(MultipartFile mFile, String saveFileName) throws IOException {
		byte[] date = mFile.getBytes();
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		//request.getRealPath("/resources/study/thumbnail/");
		String uploadPath = request.getSession().getServletContext().getRealPath("/resources/study/thumbnail/");
		
		FileOutputStream fos = new FileOutputStream(uploadPath + saveFileName);
		fos.write(date);
		
		fos.close();
	}

	@Override
	public List<CartTestVO> getOrderList(String idxsStr) {
		return studyDAO.getOrderList(idxsStr);
	}

	@Override
	public List<CartTestVO> getCartTest() {
		return studyDAO.getCartTest();
	}
}
