package com.spring.cj2103s01.service;

import java.util.List;

import com.spring.cj2103s01.vo.CartVO;
import com.spring.cj2103s01.vo.OrderDetailVO;
import com.spring.cj2103s01.vo.OrderVO;

public interface OrderService {

	public void setCart(String mId, int pId, String pOption, int pCnt, int pPrice);

	public List<CartVO> getCartList(String mId);

	public void setCartCntUpdate(String mId, int pId, String pOption, int pCnt, int pPrice);

	public int getCartCnt(String mId);

	public void cartDelete(int idx);

	public CartVO getIdxVo(int idx);

	public void setInputOrder(OrderVO vo);

	public int getLastoId(String mId);

	public void setOrderDetail(CartVO cartvo, int lastoId, int sale);

	public int notPayCnt();

	public int readyPayCnt();

	public int ingPayCnt();

	public int endPayCnt();

	public int cancelCnt();

	public int changeCnt();

	public int returnCnt();

	public int notPayCntMid(String mId);

	public int readyPayCntMid(String mId);

	public int ingPayCntMid(String mId);

	public int endPayCntMid(String mId);

	public int cancelCntMid(String mId);

	public int changeCntMid(String mId);

	public int returnCntMid(String mId);

	public int getLastCartIdx(String mId);

	public List<OrderVO> getOrderListMid(String mId, int time);

	public List<OrderDetailVO> getOrderDetailListMid(String mId);

	public List<OrderVO> getOrderListStatus(String mId, int time, String status);

	public List<OrderVO> getOrderListChange(String mId, int time, String change);

	public List<OrderVO> getOrderAllList(int startIndexNo, int pageSize);

	public List<OrderVO> getAdminOlistStatus(String status, int startIndexNo, int pageSize);

	public List<OrderVO> getAdminOlistChange(String change, int startIndexNo, int pageSize);

	public List<OrderVO> getOrderSCList(String status, String change, int startIndexNo, int pageSize);

	public List<OrderDetailVO> getOrderDetailList();

	public void statusUpdate(String oId, String oStatus);

	public List<OrderVO> getMemberListMid(String mId, int startIndexNo, int pageSize);

	public OrderVO getOrderContent(String mId, int oId);

	public List<OrderDetailVO> getOrderDetailListOid(String mId, int oId);

	public void setBuyUpdate(int oId);

	public void setReturnUpdate(int oId);

	public void setChangeUpdate(int oId);

	public void setCancelUpdate(int oId);

	public void setReturnEndUpdate(int oId);

	public void setChangeEndUpdate(int oId);

	public void setCancelEndUpdate(int oId);

	public OrderVO getOrderInfo(int oId);

	public int getCartIdx(String mId, int pId, String pOption);

	public void updateCancelStatus(int oId);

	public int totSellMoney();

	public List<OrderVO> getBuyEndList();

	public void setBuyEnd(OrderVO vo);

	public String nowMonth();

	public String pre1Month();

	public String pre2Month();

	public String pre3Month();

	public String pre4Month();

	public int nowMonthPrice();

	public int pre1MonthPrice();

	public int pre2MonthPrice();

	public int pre3MonthPrice();

	public int pre4MonthPrice();

	public int nowCatePrice(String Cate);

	public int pre1CatePrice(String Cate);

	public int pre2CatePrice(String Cate);

	public int pre3CatePrice(String Cate);

	public int pre4CatePrice(String Cate);

}
