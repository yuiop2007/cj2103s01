package com.spring.cj2103s10.service;

import java.util.List;

import com.spring.cj2103s10.vo.BoardReplyVO;
import com.spring.cj2103s10.vo.BoardVO;

public interface BoardService {

	public List<BoardVO> getBoardList(int startIndexNo, int pageSize);

	public void imgCheck(String content, String uploadPath, int position);

	public void bInputOk(BoardVO vo);

	public List<BoardVO> getBoardSearchList(int startIndexNo, int pageSize, String search, String searchString);

	public BoardVO getBoardContent(int idx);

	public void addReadNum(int idx);

	public void addGood(int idx);

	public BoardVO passwordCheck(int idx, String pwd);

	public void imgDelete(String oriContent, String uploadPath, int position);

	public void boardUpdateOk(BoardVO vo);

	public String maxLevelOrder(int boardIdx);

	public void setReplyInsert(BoardReplyVO cVo);

	public List<BoardReplyVO> getBoardReply(int idx);

	public void setReplyDelete(int replyIdx);

	public void levelOrderPlusUpdate(BoardReplyVO cVo);

	public void bReplyInsert2(BoardReplyVO cVo);

}
