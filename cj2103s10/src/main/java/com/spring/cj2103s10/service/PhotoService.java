package com.spring.cj2103s10.service;

import java.util.List;

import com.spring.cj2103s10.vo.PhotoVO;

public interface PhotoService {

	public List<PhotoVO> getPhoto();

	public void imgCheck(PhotoVO vo, String uploadPath);

	public PhotoVO getPhotoContent(int idx);

	public void setReadNumCount(int idx);

	public void photoDelete(int idx, String deletePath);

	public List<PhotoVO> photoPartList(int startNo, int pageSize, String part);

	public List<PhotoVO> photoSearchList(int startNo, int pageSize, String search, String searchString);

}
