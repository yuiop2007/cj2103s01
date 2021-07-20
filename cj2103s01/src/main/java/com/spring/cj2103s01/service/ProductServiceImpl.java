package com.spring.cj2103s01.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.spring.cj2103s01.dao.ProductDAO;
import com.spring.cj2103s01.vo.ProductVO;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDAO productDAO;
	
	@Autowired
	FileService fileService;

	@Override
	public int setProductInput(MultipartFile file, ProductVO vo, String root) {
		int res = 0;
		try {
			String oFileName = file.getOriginalFilename();
			if (oFileName != "") {
				// 파일 업로드처리 과정(파일명 중복을 방지시킨후 파일을 업로드한다.)

				// UUID를 이용한 파일명 중복방지처리
				UUID uid = UUID.randomUUID();
				String saveFileName = uid + "_" + oFileName;
				
				
				// 파일업로드처리
				fileService.writeFile(file, saveFileName, root); // 메소드를 통해서 파일을 서버에 저장시킨다.
				vo.setpImage(saveFileName);
			} else {
				res = 0;
			}
			// 정상 파일 업로드된 자료의 DB처리
			productDAO.setProductInput(vo);
			res = 1;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public List<ProductVO> getProductList(int startIndexNo, int pageSize) {
		return productDAO.getProductList(startIndexNo, pageSize);
	}

	@Override
	public List<ProductVO> getProductSearchList(int startIndexNo, int pageSize, String search, String searchString) {
		return productDAO.getProductSearchList(startIndexNo, pageSize, search, searchString);
	}

	@Override
	public void addReadNum(int pId) {
		productDAO.addReadNum(pId);
	}

	@Override
	public ProductVO getProductContent(int pId) {
		return productDAO.getProductContent(pId);
	}

	@Override
	public ProductVO getIdCheck(int pId) {
		return productDAO.getIdCheck(pId);
	}

	@Override
	public int productUpdateOk(MultipartFile file, ProductVO vo, String root) {
		int res = 0;
		try {
			String oFileName = file.getOriginalFilename();
			if (oFileName != "") {
				// 파일 업로드처리 과정(파일명 중복을 방지시킨후 파일을 업로드한다.)

				// UUID를 이용한 파일명 중복방지처리
				UUID uid = UUID.randomUUID();
				String saveFileName = uid + "_" + oFileName;
				
				
				// 파일업로드처리
				fileService.writeFile(file, saveFileName, root); // 메소드를 통해서 파일을 서버에 저장시킨다.
				vo.setpImage(saveFileName);
			} else {
				res = 0;
			}
			
			// 정상 파일 업로드된 자료의 DB처리
			productDAO.productUpdateOk(vo);
			res = 1;
		} catch (IOException e) {
			e.printStackTrace();
		}

		return res;
	}

	@Override
	public void pDelete(int pId) {
		productDAO.pDelete(pId);
	}

	@Override
	public int totProductCnt() {
		return productDAO.totProductCnt();
	}

	@Override
	public void productDelete(int idx) {
		productDAO.productDelete(idx);
	}

	@Override
	public List<ProductVO> getProductAllList() {
		return productDAO.getProductAllList();
	}

	@Override
	public void setStockUpdate(int pId, int Cnt) {
		productDAO.setStockUpdate(pId, Cnt);
	}

	@Override
	public void setCancelStockUpdate(int pId, int Cnt) {
		productDAO.setCancelStockUpdate(pId, Cnt);
	}

	@Override
	public String getImagePid(int pId) {
		return productDAO.getImagePid(pId);
	}

	@Override
	public List<ProductVO> getEventProductList(int startIndexNo, int pageSize) {
		return productDAO.getEventProductList(startIndexNo, pageSize);
	}

	@Override
	public int MadeSellCnt() {
		return productDAO.MadeSellCnt();
	}

	@Override
	public int OuterSellCnt() {
		return productDAO.OuterSellCnt();
	}

	@Override
	public int TopSellCnt() {
		return productDAO.TopSellCnt();
	}

	@Override
	public int BottomSellCnt() {
		return productDAO.BottomSellCnt();
	}

	@Override
	public int AccSellCnt() {
		return productDAO.AccSellCnt();
	}

	@Override
	public int getSalePrice(int pId) {
		return productDAO.getSalePrice(pId);
	}
	
}
