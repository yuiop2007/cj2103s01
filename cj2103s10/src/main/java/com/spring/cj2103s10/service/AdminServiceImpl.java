package com.spring.cj2103s10.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cj2103s10.dao.AdminDAO;
import com.spring.cj2103s10.vo.MemberVO;
import com.spring.cj2103s10.vo.NotifyVO;
import com.spring.cj2103s10.vo.ReservationRoomVO;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminDAO adminDAO;

	@Override
	public List<MemberVO> getMemberList(int startIndexNo, int pageSize) {
		return adminDAO.getMemberList(startIndexNo, pageSize);
	}

	@Override
	public List<MemberVO> getMemberListLevel(int startIndexNo, int pageSize, String level) {
		return adminDAO.getMemberListLevel(startIndexNo, pageSize, level);
	}

	@Override
	public List<MemberVO> getMemberListMid(int startIndexNo, int pageSize, String mid) {
		return adminDAO.getMemberListMid(startIndexNo, pageSize, mid);
	}

	@Override
	public void aMLevelCheck(String mid, String level) {
		adminDAO.aMLevelCheck(mid, level);
	}

	@Override
	public MemberVO getAMInfor(String mid) {
		return adminDAO.getAMInfor(mid);
	}

	@Override
	public void memberDelete(int idx) {
		adminDAO.memberDelete(idx);
	}

	@Override
	public List<NotifyVO> getNotifyList() {
		return adminDAO.getNotifyList();
	}

	@Override
	public void nInputOk(NotifyVO vo) {
		adminDAO.nInputOk(vo);
	}

	@Override
	public NotifyVO nUpdate(int idx) {
		return adminDAO.nUpdate(idx);
	}

	@Override
	public void nUpdateOk(NotifyVO vo) {
		adminDAO.nUpdateOk(vo);
	}

	@Override
	public void nDelete(int idx) {
		adminDAO.nDelete(idx);
	}

	@Override
	public void popupCheck(int idx, String popupSw) {
		adminDAO.popupCheck(idx, popupSw);
	}

	@Override
	public List<NotifyVO> getNotifyPopup() {
		return adminDAO.getNotifyPopup();
	}

	@Override
	public ReservationRoomVO getRoomSearch(String room) {
		return adminDAO.getRoomSearch(room);
	}

	@Override
	public List<ReservationRoomVO> roomList() {
		return adminDAO.roomList();
	}

	@Override
	public void imgCheck(String content, String uploadPath, int position) {
		if(content.indexOf("src=\"/") == -1) return; // content안에 그림파일이 없으면 작업을 수행하지 않는다.
		//            0         1         2         3         4         5         6
		//            01234567890123456789012345678901234567890123456789012345678901234567890
		//<img alt="" src="/cj2103s10/resources/ckeditor/images/reservation/src/210201125255+0900_m13.jpg" style="height:400px; width:600px" />
		
		//int position = 54;
		boolean sw = true;
		
		String nextImg = content.substring(content.indexOf("src=\"/")+position);
		
		while(sw) {
			String imgFile = nextImg.substring(0, nextImg.indexOf("\""));  // 순수한 그림파일만 가져온다.
			
			String oriFilePath = uploadPath + imgFile;  // 원본파일이 있는 경로명과 파일명
			String copyFilePath = uploadPath + "src/" + imgFile;  // 복사될파일이 있는 경로명과 파일명
			
			fileCopyCheck(oriFilePath, copyFilePath);  // images폴더에서 images/src폴도로 파일 복사작업처리
			
			// nextImg변수안에 또다른 'src="/'문구가 있는지를 검색하여, 있다면 다시 앞의 작업을 반복수행한다.
			if(nextImg.indexOf("src=\"/") == -1) {
				sw = false;
			}
			else {
				nextImg = nextImg.substring(nextImg.indexOf("src=\"/")+position);
			}
		}
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

	@Override
	public void roomInputOk(ReservationRoomVO vo) {
		adminDAO.roomInputOk(vo);
	}

	@Override
	public void imgDelete(String uploadPath, String content) {
		if(content.indexOf("src=\"/") == -1) return; // content안에 그림파일이 없으면 작업을 수행하지 않는다.
		//            0         1         2         3         4         5         6
		//            01234567890123456789012345678901234567890123456789012345678901234567890
		//<img alt="" src="/cj2103s10/resources/ckeditor/images/reservation/src/210201125255+0900_m13.jpg" style="height:400px; width:600px" />
		
		int position = 58;
		boolean sw = true;
		
		String nextImg = content.substring(content.indexOf("src=\"/")+position);
		
		while(sw) {
			String imgFile = nextImg.substring(0, nextImg.indexOf("\""));  // 순수한 그림파일만 가져온다.
			
			String delFilePath = uploadPath + imgFile;  // 원본파일이 있는 경로명과 파일명
			
			fileDeleteCheck(delFilePath);  // 실제로 파일을 지워주는 메소드호출
			
			// nextImg변수안에 또다른 'src="/'문구가 있는지를 검색하여, 있다면 다시 앞의 작업을 반복수행한다.
			if(nextImg.indexOf("src=\"/") == -1) {
				sw = false;
			}
			else {
				nextImg = nextImg.substring(nextImg.indexOf("src=\"/")+position);
			}
		}
	}

	private void fileDeleteCheck(String delFilePath) {
		File delFile = new File(delFilePath);
		if(delFile.exists()) delFile.delete();
	}

	@Override
	public void roomDeleteOk(String room) {
		adminDAO.roomDeleteOk(room);
	}

	@Override
	public void setRoomModify(String room, String available) {
		adminDAO.setRoomModify(room, available);
	}

	@Override
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
