package com.spring.cj2103s01.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s01.dao.ProductDAO;
import com.spring.cj2103s01.vo.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;

	@Override
	public List<ProductVO> getpList(int startIndexNo, int pageSize) {
		return productDAO.getpList(startIndexNo, pageSize);
	}

	public int totRecCnt() {
		return productDAO.totRecCnt();
	}

	@Override
	public int setProductInput(MultipartFile file, ProductVO vo) {
		int res = 0;
		try {
			String oFileName = file.getOriginalFilename();
			if (oFileName != "") {
				// 파일 업로드처리 과정(파일명 중복을 방지시킨후 파일을 업로드한다.)

				// UUID를 이용한 파일명 중복방지처리
				UUID uid = UUID.randomUUID();
				String saveFileName = uid + "_" + oFileName;

				// 파일업로드처리
				writeFile(file, saveFileName); // 메소드를 통해서 파일을 서버에 저장시킨다.
				vo.setpImage(saveFileName);
			} else {
				res = 0;
			}
			// 정상 파일 업로드된 자료의 DB처리
			productDAO.setProductInput(vo);
			res = 1;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return res;
	}

	// 파일을 실제 서버에 저장하는 메소드
	private void writeFile(MultipartFile mFile, String saveFileName) throws IOException {
		byte[] data = mFile.getBytes();

		// request객체의 사용으로 인한 작업처리.......
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();

		// ServletContext application = ((HttpSession) request).getServletContext();
		// String uploadPath = application.getRealPath(saveFileName);

		String uploadPath = request.getSession().getServletContext().getRealPath("/resources/pMainImages/");

		FileOutputStream fos = new FileOutputStream(uploadPath + saveFileName);
		fos.write(data); // 실제 파일이 서버에 저장된다.

		fos.close();
	}
	
	@Override
	public void imgCheck(String content, String uploadPath, int position) {
		//             0         1         2         3         4         5
		//             0123456789012345678901234567890123456789012345678901234567890
		// <img alt="" src="/cj2103s10/resources/ckeditor/images/210622142615_1.png"
		
		if(content.indexOf("src=\"/") == -1) return;
		
		// 이미지를 src폴더로 복사한다.
		String nextImg = content.substring(content.indexOf("src=\"/")+position);
		
		boolean sw = true;
		
		while(sw) {
			String imgFile = nextImg.substring(0, nextImg.indexOf("\""));  // 순수한 그림파일명만 발췌
			
			String oriFilePath = uploadPath + imgFile;   				// 원본  그림이 들어있는 '경로명+파일명'
			String copyFilePath = uploadPath + "src/" + imgFile;// 복사될 그림이 들어있는 '경로명+파일명'
			
			fileCopyCheck(oriFilePath, copyFilePath);  // 원본그림을 복사될 위치로 복사작업하는 메소드
			
			// 복사작업을 마친후 또다른 'src=\"' 문구가 있는지를 검색하여 있으면, 앞의 작업을 반복한다.
			if(nextImg.indexOf("src=\"/") == -1) {
				sw = false;
			}
			else {
				nextImg = nextImg.substring(nextImg.indexOf("src=\"/")+position);
			}
		}
	}
	
	// 원본그림을 복사될 위치로 복사작업하는 메소드
	private void fileCopyCheck(String oriFilePath, String copyFilePath) {
		File oriFile  = new File(oriFilePath);
		File copyFile = new File(copyFilePath);
		
		try {
			FileInputStream  fis = new FileInputStream(oriFile);
			FileOutputStream fos = new FileOutputStream(copyFile);
			
			byte[] buffer = new byte[2048];
			int count = 0;
			while((count=fis.read(buffer)) != -1) {
				fos.write(buffer, 0, count);
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
}
