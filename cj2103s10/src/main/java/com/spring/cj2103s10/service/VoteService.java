package com.spring.cj2103s10.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.cj2103s10.vo.VotePersonInforVO;
import com.spring.cj2103s10.vo.VotePopularVO;
import com.spring.cj2103s10.vo.VoteVO;

public interface VoteService {
	public void aVotePopularInputOk(MultipartHttpServletRequest file, VotePopularVO vo);
	
	public int totVotePopularRecCnt();

	public List<VotePopularVO> aVotePopularList(int startNo, int pageSize);

	public VotePopularVO getVotePopularView(int idx);

	public void voteSelectInsert(int personInforIdx, int popularIdx, int voteNum);

	public int votePopularDelete(int idx, String realImg, String thumbImg);

	public String memberSearch(String mid); 

	public void aVoteInforInsert(VotePersonInforVO personVo);

	public int getMaxVotePersonInfor();

	public List<VoteVO> voteResultView(int idx);

	public int getVoteTotCnt(int idx);

	public void setProgress(int idx, String progress);
}
