package com.spring.cj2103s10.service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s10.dao.MemberDAO;
import com.spring.cj2103s10.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	MemberDAO memberDAO;

	@Override
	public MemberVO getIdCheck(String mid) {
		return memberDAO.getIdCheck(mid);
	}

	@Override
	public MemberVO getNicknameCheck(String nickName) {
		return memberDAO.getNicknameCheck(nickName);
	}

	@Override
	public int setMemberInput(MultipartFile file, MemberVO vo) {
		int res = 0;
		try {
			String oFileName = file.getOriginalFilename();
			if(oFileName != "") {
				// 파일 업로드처리 과정(파일명 중복을 방지시킨후 파일을 업로드한다.)
				
				// UUID를 이용한 파일명 중복방지처리
				UUID uid = UUID.randomUUID();
			  String saveFileName = uid + "_" + oFileName;
			
			  // 파일업로드처리
			  writeFile(file, saveFileName);  // 메소드를 통해서 파일을 서버에 저장시킨다.
			  vo.setPhoto(saveFileName);
			}
			else {
				if(vo.getGender().equals("남자")) vo.setPhoto("male.jpg");
				else vo.setPhoto("female.jpg");
			}
			// 정상 파일 업로드된 자료의 DB처리
			memberDAO.setMemberInput(vo);
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
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		
		// ServletContext application = ((HttpSession) request).getServletContext();
		// String uploadPath = application.getRealPath(saveFileName);
		
		String uploadPath = request.getSession().getServletContext().getRealPath("/resources/member/");
		
		FileOutputStream fos = new FileOutputStream(uploadPath + saveFileName);
		fos.write(data);  // 실제 파일이 서버에 저장된다.
		
		fos.close();
	}
	
}