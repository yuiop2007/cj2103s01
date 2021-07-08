package com.spring.cj2103s01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s01.vo.CartVO;
import com.spring.cj2103s01.vo.OrderDetailVO;
import com.spring.cj2103s01.vo.OrderVO;

public interface OrderDAO {

	void setInputOrder(@Param("vo") OrderVO vo);

	int getLastoId(@Param("mId") String mId);

	void setOrderDetail(@Param("cartvo") CartVO cartvo,@Param("lastoId") int lastoId);

	int notPayCnt();

	int readyPayCnt();

	int ingPayCnt();

	int endPayCnt();

	int cancelCnt();

	int changeCnt();

	int returnCnt();

	int notPayCntMid(@Param("mId") String mId);

	int readyPayCntMid(@Param("mId") String mId);

	int ingPayCntMid(@Param("mId") String mId);

	int endPayCntMid(@Param("mId") String mId);

	int cancelCntMid(@Param("mId") String mId);

	int changeCntMid(@Param("mId") String mId);

	int returnCntMid(@Param("mId") String mId);

	List<OrderVO> getOrderListMid(@Param("mId") String mId, @Param("time") int time);

	List<OrderDetailVO> getOrderDetailListMid(@Param("mId") String mId);

}
