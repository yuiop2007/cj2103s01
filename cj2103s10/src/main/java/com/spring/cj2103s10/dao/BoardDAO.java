package com.spring.cj2103s10.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s10.vo.BoardReplyVO;
import com.spring.cj2103s10.vo.BoardVO;

public interface BoardDAO {

	public int totRecCnt();

	public List<BoardVO> getBoardList(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize);

	public void bInputOk(@Param("vo") BoardVO vo);

	public int totSearchRecCnt(@Param("search") String search, @Param("searchString") String searchString);

	public List<BoardVO> getBoardSearchList(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize, @Param("search") String search, @Param("searchString") String searchString);

	public BoardVO getBoardContent(@Param("idx") int idx);

	public void addReadNum(@Param("idx") int idx);

	public void addGood(@Param("idx") int idx);

	public BoardVO passwordCheck(@Param("idx") int idx, @Param("pwd") String pwd);

	public void boardUpdateOk(@Param("vo") BoardVO vo);

	public String maxLevelOrder(@Param("boardIdx") int boardIdx);

	public void setReplyInsert(@Param("cVo") BoardReplyVO cVo);

	public List<BoardReplyVO> getBoardReply(@Param("idx") int idx);

	public void setReplyDelete(@Param("replyIdx") int replyIdx);

	public void levelOrderPlusUpdate(@Param("cVo") BoardReplyVO cVo);

	public void bReplyInsert2(@Param("cVo") BoardReplyVO cVo);

}
