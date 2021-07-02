package com.spring.cj2103s10;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.cj2103s10.service.ScheduleService;
import com.spring.cj2103s10.vo.ScheduleVO;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService;
	
	@RequestMapping(value="/schedule", method=RequestMethod.GET)
	public String scheduleGet() {
		scheduleService.getSchedule();
		return "schedule/schedule";
	}
	
	@RequestMapping(value="/scMenu", method=RequestMethod.GET)
	public String scMenuGet(String mid, String ymd, Model model) {
		List<ScheduleVO> vos = scheduleService.getScMenu(mid, ymd);
		
		model.addAttribute("vos", vos);
		model.addAttribute("ymd", ymd);
		model.addAttribute("scheduleCnt", vos.size());
		return "schedule/scMenu";
	}
	
	@ResponseBody
	@RequestMapping(value="/scheduleInputOk", method=RequestMethod.POST)
	public String scheduleInputOkPost(ScheduleVO vo) {
		scheduleService.scheduleInputOk(vo);
		return "";
	}
	
	@ResponseBody
	@RequestMapping(value="/scheduleDeleteOk", method=RequestMethod.GET)
	public String scheduleDeleteOkGet(int idx) {
		scheduleService.scheduleDeleteOk(idx);
		return "";
	}
}
