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

			// request객체의 사용으로 인한 작업처리
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
					.getRequest();

			//저장되는 폴더
			if(root.equals("pInput")) {
				root = "pMainImages";
			}
			else if(root.equals("nInput")){
				root = "boardfile";
			}
			else if(root.equals("eInput")) {
				root = "boardfile";
			}
			else if(root.equals("sInput")) {
				root = "slideshow";
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

		// 파일 삭제 메소드
		public void fileDeleteCheck(String delFilePath) {
			File delFile = new File(delFilePath);
			if(delFile.exists()) delFile.delete();
		}

		public int imgDelete(String uploadPath) {
			File path = new File(uploadPath);
			// 파일객체를 통해서 uploadPath경로안의 모든 파일의 정보를 담아와서 배열로 저장한다.
			File[] fileList = path.listFiles();
			
			int fileCnt = fileList.length - 1;
			
			for(int i=0; i<fileCnt; i++) {
				fileList[i].delete();
			}
			
			return fileCnt;
		}
}
