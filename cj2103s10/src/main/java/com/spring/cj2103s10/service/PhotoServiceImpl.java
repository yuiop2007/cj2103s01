package com.spring.cj2103s10.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cj2103s10.dao.PhotoDAO;
import com.spring.cj2103s10.vo.PhotoVO;

@Service
public class PhotoServiceImpl implements PhotoService {
	@Autowired
	PhotoDAO photoDao;

	@Override
	public List<PhotoVO> getPhoto() {
		return photoDao.getPhoto();
	}

	@Override
	public void imgCheck(PhotoVO vo, String uploadPath) {
		if(vo.getContent().indexOf("src=\"/") == -1) return; // content안에 그림파일이 없으면 작업을 수행하지 않는다.
		//            0         1         2         3         4         5         6
		//            01234567890123456789012345678901234567890123456789012345678901234567890
		//<img alt="" src="/cj2103s10/resources/ckeditor/images/photo/src/210201125255+0900_m13.jpg" style="height:400px; width:600px" />
		
		int position = 48;
		boolean sw = true, firstSw = true;
		String firstFile = ""; // 첫번째 파일명을 저장하기위한 변수
		
		String nextImg = vo.getContent().substring(vo.getContent().indexOf("src=\"/")+position);
		
		while(sw) {
			String imgFile = nextImg.substring(0, nextImg.indexOf("\""));  // 순수한 그림파일만 가져온다.
			
			// 첫번째 그림을 thumbnail로 저장하기위해 파일명을 변수에 저장시켜두었다.
			if(firstSw) {
				firstFile = imgFile;
				firstSw = false;
			}
			
			// 아래로 그림파일을 src폴더로 복사하는 작업이다. 
			String oriFilePath = uploadPath + imgFile;  // 원본파일이 있는 경로명과 파일명
			String copyFilePath = uploadPath + "src/" + imgFile;  // 복사될파일이 있는 경로명과 파일명
			
			fileCopyCheck(oriFilePath, copyFilePath);  // images/photo/폴더에서 images/photo/src/폴더로 파일 복사작업처리
			
			// nextImg변수안에 또다른 'src="/'문구가 있는지를 검색하여, 있다면 다시 앞의 작업을 반복수행한다.
			if(nextImg.indexOf("src=\"/") == -1) {
				sw = false;
			}
			else {
				nextImg = nextImg.substring(nextImg.indexOf("src=\"/")+position);
			}
		}
		// content필드에 'style=~~~~~' 을 찾아서 모두 삭제처리한다.
		String tempContent = "";
		sw = true;
		//int cnt = 0;
		//String nextContent = vo.getContent().substring(vo.getContent().indexOf("style=\""));
		String nextContent = vo.getContent();
		
		while(nextContent.indexOf("style=\"") != -1) {
			sw = false;
			tempContent += nextContent.substring(0,nextContent.indexOf("style=\""));
			tempContent += nextContent.substring(nextContent.indexOf("px\"")+4);
			if(tempContent.indexOf("style=\"")==-1) {
				break;
			}
			else {
				nextContent = tempContent;
				tempContent = "";
			}
		}
		// content필드안에 있는 'style=~~~~'문구들을 모두 삭제시켰다. 그후 다시 content에 set시켜준다.
		if(!sw)	vo.setContent(tempContent);
		
  	// 파일이 정상적으로 src폴더에 복사되었으면 DB에 저장되는 실제경로를 src폴더로 변경시켜준다.
		//vo.setContent(vo.getContent().replace("/images/photo/", "/images/photo/src/")); // 실제로 서버에 저장되는 경로
		vo.setContent(vo.getContent().replace("/resources/ckeditor/images/photo/", "/resources/ckeditor/images/photo/src/"));
		
		// 아래로, 일반파일의 모든 복사작업이 끝나면 /src/폴더의 첫번째 그림파일을 썸네일파일로 복사작업처리한다.
		try {
			String fileExt = firstFile.substring(firstFile.lastIndexOf(".")+1); // 확장자 구하기
			
			// 썸네일 이미지 만들어 저장하기(이미지를 필요한 크기로 잘라서 저장한다.)
			String imsiUploadPath = uploadPath + "/src/" + firstFile;
			BufferedImage srcImg = ImageIO.read(new File(imsiUploadPath));
			
			int ow = srcImg.getWidth();  // 서버에 저장된 원본 그림파일의 폭(너비)
			int oh = srcImg.getHeight(); // 서버에 저장된 원본 그림파일의 높이
			
			int tw = 200, th = 150;  // 썸네일의 너비와 높이를 사용자가 원하는 크기로 지정한다.(4:3 비율) -> 200px / 4 * 3 = 150px
			
			// 원본그림을 썸네일의 폭(높이)의 재지정된 크기(200*150)로 변경처리한다.
			int rw = ow;
			int rh = (ow * th) / tw;
			
			// 재 계산된 높이가 원본보다 높다면 잘랐을때 공백으로 채워지게된다. 따라서 이때는 높이를 기준으로 재계산처리한다.
			if(rh > oh) {
				rh = oh;
				rw =(oh * tw) / th;
			}
			// 앞의 계산은 이미지 잘랐을때 공백이 들어가게 될까봐서 다시 계산 처리한 것이다.
			// 처리가 완료되면 실제로는 아래에서 원본그림을 지정한 그림의 크기(200*150)로 변형시키게 된다.
			
			// 계산된 크기만큼 원본 이미지를 자른다.
			//BufferedImage cropImg = Scalr.crop(srcImg, (ow-rw)/2, (oh-rh)/2, rw, rh);
			Scalr.crop(srcImg, (ow-rw)/2, (oh-rh)/2, rw, rh);
			
			// 잘라낸 그림파일의 정보를 다시 읽어온다.
			BufferedImage resizeImg = Scalr.resize(srcImg, tw, th);
			
			// 새롭게 재 계산처리되어 잘라진 썸네일을 저장한다.
			String pathFileName = uploadPath + "src/" + "s_" + firstFile;
			File tFileName = new File(pathFileName);
			ImageIO.write(resizeImg, fileExt, tFileName);  // 썸네일 이미지 저장처리
			
			// 이미지 잘라서 저장작업을 마친후 DB에 저장하기 위해서 VO에 작업된 내용을 담는다.
			vo.setThumbnail("s_" + firstFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
  	// 잘 정비된 vo를 DB에 저장한다.
		photoDao.photoInputOk(vo);
	}

	private void fileCopyCheck(String oriFilePath, String copyFilePath) {
		File oriFile = new File(oriFilePath);
		File copyFile = new File(copyFilePath);
		
		try {
			FileInputStream fis = new FileInputStream(oriFile);
			FileOutputStream fos = new FileOutputStream(copyFile);
			
			byte[] buffer = new byte[1024];
			int readCnt = 0;
			while((readCnt=fis.read(buffer)) !=  -1) {
				fos.write(buffer, 0, readCnt);
			}
			fos.flush();
			fis.close();
			fos.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 포토갤러리 개별 항목 내용보기
	@Override
	public PhotoVO getPhotoContent(int idx) {
		return photoDao.getPhotoContent(idx);
	}

	// 카운트 증가
	@Override
	public void setReadNumCount(int idx) {
		photoDao.setReadNumCount(idx);
	}

	// 삭제
	@Override
	public void photoDelete(int idx, String deletePath) {
		PhotoVO vo = photoDao.getPhotoContent(idx);
		
		String oriContent = vo.getContent();
		
		// vo를 이용해 src폴더의 파일들을 삭제처리한다.
		if(oriContent.indexOf("src=\"/") == -1) return; // content안에 그림파일이 없으면 작업을 수행하지 않는다.
		//            0         1         2         3         4         5         6
		//            01234567890123456789012345678901234567890123456789012345678901234567890
		//<img alt="" src="/cj2103s10/resources/ckeditor/images/photo/src/210201125255+0900_m13.jpg" />
		
		int position = 52;
		boolean sw = true;
		
		String nextImg = oriContent.substring(oriContent.indexOf("src=\"/")+position);
		
		while(sw) {
			String imgFile = nextImg.substring(0, nextImg.indexOf("\""));  // 순수한 그림파일만 가져온다.
			
			String delFilePath = deletePath + imgFile;  // 원본파일이 있는 경로명과 파일명
			
			File delFile = new File(delFilePath);
			if(delFile.exists()) delFile.delete();  // 실제로 파일을 지운다.
			
			// nextImg변수안에 또다른 'src="/'문구가 있는지를 검색하여, 있다면 다시 앞의 작업을 반복수행한다.
			if(nextImg.indexOf("src=\"/") == -1) {
				sw = false;
			}
			else {
				nextImg = nextImg.substring(nextImg.indexOf("src=\"/")+position);
			}
		}
		// content안에 기술되어 있는 모든 그림파일 삭제를 마쳤으면, 다음은 썸네일 그림도 삭제처리한다.
		File delFile = new File(deletePath + vo.getThumbnail());
		if(delFile.exists()) delFile.delete();
		
  	// 파일 삭제가 다 되었으면 DB에서도 자료(레코드)를 삭제시켜준다.
		photoDao.photoDelete(idx);
	}

	@Override
	public List<PhotoVO> photoPartList(int startNo, int pageSize, String part) {
		return photoDao.photoPartList(startNo, pageSize, part);
	}

	@Override
	public List<PhotoVO> photoSearchList(int startNo, int pageSize, String search, String searchString) {
		return photoDao.photoSearchList(startNo, pageSize, search, searchString);
	}
	
}
