package com.spring.cj2103s01.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cj2103s01.dao.MemberDAO;
import com.spring.cj2103s01.vo.MemberVO;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDAO memberDAO;

	@Override
	public void setMemberInput(MemberVO vo) {
		memberDAO.setMemberInput(vo);
	}

	@Override
	public MemberVO getIdCheck(String mId) {
		return memberDAO.getIdCheck(mId);
	}

	@Override
	public void setMemberUpdate(MemberVO vo) {
		memberDAO.setMemberUpdate(vo);
	}

	@Override
	public void mDelete(String mId) {
		memberDAO.mDelete(mId);
	}

	@Override
	public MemberVO getPwdSearch(String mId, String mEmail) {
		return memberDAO.getPwdSearch(mId, mEmail);
	}

	@Override
	public void setPwdChange(String mId, String encode) {
		memberDAO.setPwdChange(mId, encode);
	}

	@Override
	public MemberVO getNameSearch(String mName, String mEmail) {
		return memberDAO.getNameSearch(mName, mEmail);
	}

	@Override
	public List<MemberVO> getMemberList() {
		return memberDAO.getMemberList();
	}

	@Override
	public int totMemberCnt() {
		return memberDAO.totMemberCnt();
	}

	@Override
	public int dropMemberCnt() {
		return memberDAO.dropMemberCnt();
	}

	@Override
	public List<MemberVO> getMemberListLevel(int startIndexNo, int pageSize, String mLevel) {
		return memberDAO.getMemberListLevel(startIndexNo, pageSize, mLevel);
	}

	@Override
	public List<MemberVO> getMemberListMid(int startIndexNo, int pageSize, String mId) {
		return memberDAO.getMemberListMid(startIndexNo, pageSize, mId);
	}

	@Override
	public void mLevelCheck(String mId, String mLevel) {
		memberDAO.mLevelCheck(mId, mLevel);
	}

	@Override
	public void memberDelete(String idx) {
		memberDAO.memberDelete(idx);
	}

	@Override
	public void mPointInput(String idx, int point) {
		memberDAO.mPointInput(idx, point);
	}

	@Override
	public List<MemberVO> getCouponMember(int cmCate) {
		return memberDAO.getCouponMember(cmCate);
	}

	@Override
	public void updateMemberBuy(String mId) {
		memberDAO.updateMemberBuy(mId);
	}

	@Override
	public void setUpdatePoint(String mId, int mile) {
		memberDAO.setUpdatePoint(mId, mile);
	}

	@Override
	public int totSellCnt() {
		return memberDAO.totSellCnt();
	}

	@Override
	public void updateMemberPoint(String mId, int point) {
		memberDAO.updateMemberPoint(mId, point);
	}
	
	
}
