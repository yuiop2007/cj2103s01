package com.spring.cj2103s01.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
	// 파일을 실제 서버에 저장하는 메소드
		public void writeFile(MultipartFile mFile, String saveFileName, String root) throws IOException {
			byte[] data = mFile.getBytes();

			// request객체의 사용으로 인한 작업처리.......
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
					.getRequest();

			// ServletContext application = ((HttpSession) request).getServletContext();
			// String uploadPath = application.getRealPath(saveFileName);
			if(root.equals("pInput")) {
				root = "pMainImages";
			}
			else if(root.equals("nInput")){
				root = "boardfile";
			}
			else if(root.equals("eInput")) {
				root = "boardfile";
			}
			
			
			String uploadPath = request.getSession().getServletContext().getRealPath("/resources/"+ root +"/");

			FileOutputStream fos = new FileOutputStream(uploadPath + saveFileName);
			fos.write(data); // 실제 파일이 서버에 저장된다.

			fos.close();
		}
		
		// 원본그림을 복사될 위치로 복사작업하는 메소드
		public void fileCopyCheck(String oriFilePath, String copyFilePath) {
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
