package com.spring.cj2103s10.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cj2103s10.dao.StudyDAO;
import com.spring.cj2103s10.vo.AreaVO;

@Service
public class StudyImpl implements Study {

	@Autowired
	StudyDAO studyDAO;

	@Override
	public String[] getAddress1() {
		return studyDAO.getAddress1();
	}

	@Override
	public List<AreaVO> getAddress2(String address1) {
		return studyDAO.getAddress2(address1);
	}
	
}
