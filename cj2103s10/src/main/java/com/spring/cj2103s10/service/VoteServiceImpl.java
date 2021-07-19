package com.spring.cj2103s10.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import org.imgscalr.Scalr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.cj2103s10.dao.VoteDAO;
import com.spring.cj2103s10.vo.VotePersonInforVO;
import com.spring.cj2103s10.vo.VotePopularVO;
import com.spring.cj2103s10.vo.VoteVO;

@Service
@RequestMapping("/vote")
public class VoteServiceImpl implements VoteService {

	@Autowired
	VoteDAO voteDAO;
	
	@Override
	public void aVotePopularInputOk(MultipartHttpServletRequest mfile, VotePopularVO vo) {
		try {
	  	List<MultipartFile> fileList = mfile.getFiles("file");
	  	String saveFileNames = "";
	  	String ThumbFileNames = "";
	  	
	  	for(MultipartFile file : fileList) {
		  	String oFileName = file.getOriginalFilename();
		  	
		  	UUID uid = UUID.randomUUID();
		  	String saveFileName = uid + "_" + oFileName;
		  	
				writeFile(file, saveFileName);
				saveFileNames += saveFileName + "/";
				
				String thumbName = "s_" + saveFileName;
				makeThumbnail(saveFileName, thumbName);  
				ThumbFileNames += thumbName + "/";
	  	}
	  	
	  	vo.setSubTitle(vo.getSubTitle().replace(",", "/"));
	  	
			vo.setRealImg(saveFileNames);
			vo.setThumbImg(ThumbFileNames);
			
			voteDAO.aVotePopularInputOk(vo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** * 썸네일을 생성한다. * 250 x 150 크기의 썸네일을 만든다. */
	private void makeThumbnail(String saveFileName, String thumbName) throws IOException {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();

		String uploadPath = request.getSession().getServletContext().getRealPath("/")+"resources\\vote\\";  // 저장경로 구하기
		String fileExt = saveFileName.substring(saveFileName.lastIndexOf(".")+1);
		
		String filePath = uploadPath + saveFileName;
		BufferedImage srcImg = ImageIO.read(new File(filePath));

		int ow = srcImg.getWidth();
		int oh = srcImg.getHeight();
		
		int dw = 150, dh = 100;
		
		int rw = ow;
		int rh = (ow * dh) / dw;
		
		if(rh > oh) {
			rh = oh;
			rw = (oh * dw) / dh;
		}
		
		BufferedImage cropImg = Scalr.crop(srcImg, (ow-rw)/2, (oh-rh)/2, rw, rh);
		
		BufferedImage destImg = Scalr.resize(cropImg, dw, dh);
		
		thumbName = uploadPath + thumbName;
		File thumbFile = new File(thumbName);
		
		ImageIO.write(destImg, fileExt, thumbFile);
	}
	
	private void writeFile(MultipartFile file, String saveFileName) throws IOException {
		byte[] data = file.getBytes();
		
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
		String uploadPath = request.getSession().getServletContext().getRealPath("/")+"resources\\vote\\";
		
		FileOutputStream fos = new FileOutputStream(uploadPath + saveFileName);
		fos.write(data);
		
		fos.close();
	}
	
	@Override
	public int totVotePopularRecCnt() {
		return voteDAO.totVotePopularRecCnt();
	}

	@Override
	public List<VotePopularVO> aVotePopularList(int startNo, int pageSize) {
		return voteDAO.aVotePopularList(startNo, pageSize);
	}

	@Override
	public VotePopularVO getVotePopularView(int idx) {
		return voteDAO.getVotePopularView(idx);
	}

	@Override
	public int votePopularDelete(int idx, String realImg, String thumbImg) {
		HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest(); // AOP에서 request객체를 사용(선언)하기위한 방법
		
		String directory = request.getSession().getServletContext().getRealPath("/resources/vote/");
  	
  	String[] realImgs = realImg.split("/");
  	String[] thumbImgs = thumbImg.split("/");
  	
  	for(String delRealImg : realImgs) {
    	new File(directory + delRealImg).delete();
  	}
  	for(String delThumbImg : thumbImgs) {
  	  new File(directory + delThumbImg).delete();
  	}
  	
		return voteDAO.votePopularDelete(idx);
	}
	
	@Override
	public String memberSearch(String mid) {
		return voteDAO.memberSearch(mid);
	}
	
	@Override
	public void aVoteInforInsert(VotePersonInforVO personVo) {
		voteDAO.aVoteInforInsert(personVo);
	}

	@Override
	public int getMaxVotePersonInfor() {
		return voteDAO.getMaxVotePersonInfor();
	}

	@Override
	public void voteSelectInsert(int personInforIdx, int popularIdx, int voteNum) {
		voteDAO.voteSelectInsert(personInforIdx, popularIdx, voteNum);
	}

	@Override
	public List<VoteVO> voteResultView(int idx) {
		return voteDAO.voteResultView(idx);
	}

	@Override
	public int getVoteTotCnt(int idx) {
		return voteDAO.getVoteTotCnt(idx);
	}

	@Override
	public void setProgress(int idx, String progress) {
		voteDAO.setProgress(idx, progress);
	}
}
