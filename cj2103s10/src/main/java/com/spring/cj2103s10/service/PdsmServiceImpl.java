package com.spring.cj2103s10.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.cj2103s10.dao.PdsmDAO;
import com.spring.cj2103s10.vo.PdsmVO;

@Service
public class PdsmServiceImpl implements PdsmService {

	@Autowired
	PdsmDAO pdsmDAO;

	@Override
	public List<PdsmVO> getPmList(int startIndexNo, int pageSize, String part) {
		return pdsmDAO.getPmList(startIndexNo, pageSize, part);
	}

	@Override
	public void pmInput(MultipartHttpServletRequest mfile, PdsmVO vo) {
		try {
			List<MultipartFile> fileList = mfile.getFiles("file");
			String oFileNames = "";     
			String saveFileNames = "";  
			int fileSizes = 0;          
			
			for(MultipartFile file : fileList) {
				String oFileName = file.getOriginalFilename();
				
				String saveFileName = saveFileName(oFileName);
				
				writeFile(file, saveFileName);
				
				oFileNames += oFileName + "/";
				saveFileNames += saveFileName + "/";
				fileSizes += file.getSize();
			}
			
			vo.setfName(oFileNames);
			vo.setRfName(saveFileNames);
			vo.setfSize(fileSizes);
			
			pdsmDAO.pmInput(vo);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	private void writeFile(MultipartFile file, String saveFileName) throws IOException {
		byte[] data = file.getBytes();
		
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
		String uploadPath = request.getSession().getServletContext().getRealPath("/resources/pdsm/");
		
		FileOutputStream fos = new FileOutputStream(uploadPath + saveFileName);
		fos.write(data);  
		
		fos.close();
	}
	
	private String saveFileName(String oFileName) {
		String fileName = "";
		
		Calendar calendar = Calendar.getInstance();
		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += "_" + oFileName;
		
		return fileName;
	}

	@Override
	public void setDownCheck(int idx) {
		pdsmDAO.setDownCheck(idx);
	}

	@Override
	public PdsmVO getPmContent(int idx) {
		return pdsmDAO.getPmContent(idx);
	}
	
}
