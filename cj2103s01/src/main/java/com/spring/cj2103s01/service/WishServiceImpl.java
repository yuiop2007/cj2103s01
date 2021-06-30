package com.spring.cj2103s01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cj2103s01.dao.WishDAO;
import com.spring.cj2103s01.vo.WishVO;

@Service
public class WishServiceImpl implements WishService{
	@Autowired
	WishDAO wishDAO;

	@Override
	public List<WishVO> getWishList(String mId) {
		return wishDAO.getWishList(mId);
	}

	@Override
	public int addWish(List<WishVO> vos, int pId, String mId) {
		int sw = 0;
		int res = 0;
		
		for(WishVO vo : vos) {
			if(vo.getpId()==pId) {
				sw=1;
			}
		}
		if(sw==0) {
			wishDAO.addWish(pId, mId);
			res = 1;
		}
		return res;
	}

	@Override
	public void wishDelete(int wId) {
		wishDAO.wishDelete(wId);
	}

}
