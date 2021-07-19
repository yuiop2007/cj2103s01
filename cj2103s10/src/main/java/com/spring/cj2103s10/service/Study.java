package com.spring.cj2103s10.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s10.vo.AreaVO;
import com.spring.cj2103s10.vo.CartTestVO;
import com.spring.cj2103s10.vo.ThumbnailVO;

public interface Study {

	public String[] getAddress1();

	public List<AreaVO> getAddress2(String address1);

	public AreaVO getAddressSearch(String address1, String address2);

	public void getCalendar();

	public ThumbnailVO thumbnailCreate(MultipartFile file);

	public List<CartTestVO> getOrderList(String idxsStr);

	public List<CartTestVO> getCartTest();

}
