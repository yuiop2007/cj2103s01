package com.spring.cj2103s10.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cj2103s10.dao.BoardDAO;
import com.spring.cj2103s10.vo.BoardReplyVO;
import com.spring.cj2103s10.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDAO boardDAO;

	@Override
	public List<BoardVO> getBoardList(int startIndexNo, int pageSize) {
		return boardDAO.getBoardList(startIndexNo, pageSize);
	}

	@Override
	public void imgCheck(String content, String uploadPath, int position) {
		//             0         1         2         3         4 4   4   5
		//             0123456789012345678901234567890123456789012345678901234567890
		// <img alt="" src="/cj2103s10/resources/ckeditor/images/210622142615_1.png"
		// <img alt="" src="/cj2103s10/resources/ckeditor/images/src/210622142615_1.png"
		
		if(content.indexOf("src=\"/") == -1) return;
		
		// 이미지를 src(또는 images)폴더로 복사한다.
		String nextImg = content.substring(content.indexOf("src=\"/")+position);
		
		boolean sw = true;
		
		while(sw) {
			String imgFile = nextImg.substring(0, nextImg.indexOf("\""));  // 순수한 그림파일명만 발췌
			String copyFilePath = "";
			
			String oriFilePath = uploadPath + imgFile;   				// 원본  그림이 들어있는 '경로명+파일명'
			if(content.substring(content.indexOf("src=\"/")+position-4,content.indexOf("src=\"/")+position).equals("src/")) {  // 이미 복사가 되어 src폴더에 파일이 존재하는 경우(수정시에 들어왔을경우.)
				// 수정시에 처리할 부분
			  copyFilePath = uploadPath.substring(0,uploadPath.length()-4) + imgFile;// 복사될 그림이 들어있는 '경로명+파일명'
			}
			else {
				// 처음에 입력시에 처리부분(원본그림은 images폴더에 있기에 'src폴더로 복사처리해야함..
				copyFilePath = uploadPath + "src/" + imgFile;// 복사될 그림이 들어있는 '경로명+파일명'
			}
			
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

	@Override
	public void bInputOk(BoardVO vo) {
		boardDAO.bInputOk(vo);
	}

	@Override
	public List<BoardVO> getBoardSearchList(int startIndexNo, int pageSize, String search, String searchString) {
		return boardDAO.getBoardSearchList(startIndexNo, pageSize, search, searchString);
	}

	@Override
	public BoardVO getBoardContent(int idx) {
		return boardDAO.getBoardContent(idx);
	}

	@Override
	public void addReadNum(int idx) {
		boardDAO.addReadNum(idx);
	}

	@Override
	public void addGood(int idx) {
		boardDAO.addGood(idx);
	}

	@Override
	public BoardVO passwordCheck(int idx, String pwd) {
		return boardDAO.passwordCheck(idx, pwd);
	}

	@Override
	public void imgDelete(String oriContent, String uploadPath, int position) {
		if(oriContent.indexOf("src=\"/") == -1) return;
		
		String nextImg = oriContent.substring(oriContent.indexOf("src=\"/")+position);
		
		boolean sw = true;
		
		while(sw) {
			String imgFile = nextImg.substring(0, nextImg.indexOf("\""));  // 순수한 그림파일명만 발췌
			String delFilePath = uploadPath + imgFile;   				// 원본  그림이 들어있는 '경로명+파일명'
			fileDeleteCheck(delFilePath);  // 기존파일을 삭제한다.
			
			if(nextImg.indexOf("src=\"/") == -1) {
				sw = false;
			}
			else {
				nextImg = nextImg.substring(nextImg.indexOf("src=\"/")+position);
			}
		}
	}

	// 파일 삭제 메소드
	private void fileDeleteCheck(String delFilePath) {
		File delFile = new File(delFilePath);
		if(delFile.exists()) delFile.delete();
	}

	@Override
	public void boardUpdateOk(BoardVO vo) {
		boardDAO.boardUpdateOk(vo);
	}

	@Override
	public String maxLevelOrder(int boardIdx) {
		return boardDAO.maxLevelOrder(boardIdx);
	}

	@Override
	public void setReplyInsert(BoardReplyVO cVo) {
		boardDAO.setReplyInsert(cVo);
	}

	@Override
	public List<BoardReplyVO> getBoardReply(int idx) {
		return boardDAO.getBoardReply(idx);
	}

	@Override
	public void setReplyDelete(int replyIdx) {
		boardDAO.setReplyDelete(replyIdx);
	}

	@Override
	public void levelOrderPlusUpdate(BoardReplyVO cVo) {
		boardDAO.levelOrderPlusUpdate(cVo);
	}

	@Override
	public void bReplyInsert2(BoardReplyVO cVo) {
		boardDAO.bReplyInsert2(cVo);
	}
	
}
