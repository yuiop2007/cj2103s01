package com.spring.cj2103s01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s01.vo.WishVO;

public interface WishDAO {

	void addWish(@Param("pId") int pId,@Param("mId") String mId);

	List<WishVO> getWishList(@Param("mId") String mId);

	int totWishRecCnt(@Param("search") String search);

	void wishDelete(@Param("wId") int wId);

}
