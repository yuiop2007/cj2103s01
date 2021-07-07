package com.spring.cj2103s01.dao;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s01.vo.CartVO;
import com.spring.cj2103s01.vo.OrderVO;

public interface OrderDAO {

	void setInputOrder(@Param("vo") OrderVO vo);

	int getLastoId(@Param("mId") String mId);

	void setOrderDetail(@Param("cartvo") CartVO cartvo,@Param("lastoId") int lastoId);

}
