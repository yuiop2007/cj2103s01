package com.spring.cj2103s10.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s10.vo.ScheduleVO;

public interface ScheduleDAO {

	public List<ScheduleVO> getScheduleList(@Param("mid") String mid, @Param("ym") String ym);

	public List<ScheduleVO> getScMenu(@Param("mid") String mid, @Param("ymd") String ymd);

	public void scheduleInputOk(@Param("vo") ScheduleVO vo);

	public void scheduleDeleteOk(@Param("idx") int idx);

}
