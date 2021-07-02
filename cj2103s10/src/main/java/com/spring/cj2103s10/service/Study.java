package com.spring.cj2103s10.service;

import java.util.List;

import com.spring.cj2103s10.vo.AreaVO;

public interface Study {

	public String[] getAddress1();

	public List<AreaVO> getAddress2(String address1);

	public AreaVO getAddressSearch(String address1, String address2);

	public void getCalendar();

}
