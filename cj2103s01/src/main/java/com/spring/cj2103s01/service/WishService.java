package com.spring.cj2103s01.service;

import java.util.List;

import com.spring.cj2103s01.vo.WishVO;

public interface WishService {

	int addWish(List<WishVO> vos, int pId, String mId);

	List<WishVO> getWishList(String mId);

	void wishDelete(int wId);

}
