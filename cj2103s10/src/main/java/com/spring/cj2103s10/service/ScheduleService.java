package com.spring.cj2103s10.service;

import java.util.List;

import com.spring.cj2103s10.vo.ScheduleVO;

public interface ScheduleService {

	public void getSchedule();

	public List<ScheduleVO> getScMenu(String mid, String ymd);

	public void scheduleInputOk(ScheduleVO vo);

	public void scheduleDeleteOk(int idx);

}
