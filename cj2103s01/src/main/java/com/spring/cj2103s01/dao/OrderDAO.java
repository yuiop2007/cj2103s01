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

	List<OrderVO> getOrderListStatus(@Param("mId") String mId,@Param("time") int time,@Param("status") String status);

	List<OrderVO> getOrderListChange(@Param("mId") String mId,@Param("time") int time,@Param("change") String change);

	List<OrderVO> getOrderAllList(@Param("startIndexNo") int startIndexNo,@Param("pageSize") int pageSize);

	int totOrderCnt();

	List<OrderVO> getAdminOlistStatus(@Param("status") String status,@Param("startIndexNo") int startIndexNo,@Param("pageSize") int pageSize);

	List<OrderVO> getAdminOlistChagne(@Param("change") String change,@Param("startIndexNo") int startIndexNo,@Param("pageSize") int pageSize);

	List<OrderVO> getOrderSCList(@Param("status") String status,@Param("change") String change,@Param("startIndexNo") int startIndexNo,@Param("pageSize") int pageSize);

	int totRecCntStatus(@Param("searchString") String searchString);

	int totRecChange(@Param("search") String search);

	int totSearchRecCnt(@Param("search") String search,@Param("searchString") String searchString);

	List<OrderDetailVO> getOrderDetailList();

	void statusUpdate(@Param("oId") String oId,@Param("oStatus") String oStatus);

	int totRecCntMid(@Param("searchString") String searchString);

	List<OrderVO> getMemberListMid(@Param("mId") String mId,@Param("startIndexNo") int startIndexNo,@Param("pageSize") int pageSize);

	OrderVO getOrderContent(@Param("mId") String mId,@Param("oId") int oId);

	List<OrderDetailVO> getOrderDetailListOid(@Param("mId") String mId,@Param("oId") int oId);

	void setBuyUpdate(@Param("oId") int oId);

	void setReturnUpdate(@Param("oId") int oId);

	void setChangeUpdate(@Param("oId") int oId);

	void setCancelUpdate(@Param("oId") int oId);

	void setReturnEndUpdate(@Param("oId") int oId);

	void setChangeEndUpdate(@Param("oId") int oId);

	void setCancelEndUpdate(@Param("oId") int oId);

	OrderVO getOrderInfo(@Param("oId") int oId);

}
