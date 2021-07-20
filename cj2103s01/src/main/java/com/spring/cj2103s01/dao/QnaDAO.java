package com.spring.cj2103s01.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.spring.cj2103s01.vo.QnaVO;

public interface QnaDAO {

	int totRecCnt();

	int totSearchRecCnt(@Param("search") String search, @Param("searchString") String searchString);

	List<QnaVO> getQnaList(@Param("startIndexNo") int startIndexNo,@Param("pageSize") int pageSize);

	List<QnaVO> getQnaContentList(@Param("pId") int pId,@Param("startIndexNo") int startIndexNo,@Param("qpageSize") int qpageSize);

	void setQnaInput(@Param("vo") QnaVO vo);

	void addReadNum(@Param("qId") int qId);

	QnaVO getQnaContent(@Param("qId") int qId);

	QnaVO getIdCheck(@Param("qId") int qId);

	void qDelete(@Param("qId") int qId);

	void qnaUpdateOk(@Param("vo") QnaVO vo);

	List<QnaVO> getQnaSearchList(@Param("startIndexNo") int startIndexNo, @Param("pageSize") int pageSize, @Param("search") String search, @Param("searchString") String searchString);

	int totpIdRecCnt(@Param("pId") int pId);

	void setQnaUpdate(@Param("vo") QnaVO vo);

	QnaVO getQnaLastVo();

	void qnaReplyOk(@Param("vo") QnaVO vo);

	int totmIdRecCnt(@Param("mId") String mId);

	List<QnaVO> getQnaMemberList(@Param("mId") String mId,@Param("startIndexNo") int startIndexNo,@Param("qpageSize") int qpageSize);

	int qnaCheck(@Param("qId") int qId);


}
