package com.spring.cj2103s10.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s10.vo.VotePersonInforVO;
import com.spring.cj2103s10.vo.VotePopularVO;
import com.spring.cj2103s10.vo.VoteVO;

public interface VoteDAO {
	public void aVotePopularInputOk(@Param("vo") VotePopularVO vo);
	
	public int totVotePopularRecCnt();

	public List<VotePopularVO> aVotePopularList(@Param("startNo") int startNo, @Param("pageSize") int pageSize);

	public VotePopularVO getVotePopularView(@Param("idx") int idx);

	public int votePopularDelete(@Param("idx") int idx);

	public String memberSearch(@Param("mid") String mid); 

	public void aVoteInforInsert(@Param("personVo") VotePersonInforVO personVo);

	public int getMaxVotePersonInfor();

	public void voteSelectInsert(@Param("personInforIdx") int personInforIdx, @Param("popularIdx") int popularIdx, @Param("voteNum") int voteNum);

	public List<VoteVO> voteResultView(@Param("idx") int idx);

	public int getVoteTotCnt(@Param("idx") int idx);

	public void setProgress(@Param("idx") int idx, @Param("progress") String progress);

	public int totRecCnt();
}
