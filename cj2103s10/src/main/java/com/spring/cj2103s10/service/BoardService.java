package com.spring.cj2103s10.service;

import java.util.List;

import com.spring.cj2103s10.vo.BoardVO;

public interface BoardService {

	public List<BoardVO> getBoardList(int startIndexNo, int pageSize);

	public void imgCheck(String content, String uploadPath, int position);

	public void bInputOk(BoardVO vo);

}
