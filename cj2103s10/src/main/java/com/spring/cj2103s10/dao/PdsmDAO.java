package com.spring.cj2103s10.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s10.vo.PdsmVO;

public interface PdsmDAO {

	public int totRecCnt(@Param("partValue") String partValue);

	public List<PdsmVO> getPmList(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize, @Param("part") String part);

	public void pmInput(@Param("vo") PdsmVO vo);

	public void setDownCheck(@Param("idx") int idx);

	public PdsmVO getPmContent(@Param("idx") int idx);

}
