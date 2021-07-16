package com.spring.cj2103s01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s01.vo.SlideVO;

public interface SlideDAO {

	public List<SlideVO> getSlideList();

	public int getSlideTotCnt();

	public void setSlideInput(@Param("vo") SlideVO vo);

	public String getImageSid(@Param("sId") int sId);

	public void sDelete(@Param("sId") int sId);

	public void sNumUpdate(@Param("sId") int sId,@Param("sNum") int sNum);

	public List<SlideVO> getSlideMainList();

}
