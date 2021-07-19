package com.spring.cj2103s10;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.cj2103s10.pagination.Pagination;
import com.spring.cj2103s10.pagination.PaginationVO;
import com.spring.cj2103s10.service.PdsmService;
import com.spring.cj2103s10.vo.PdsmVO;

@Controller
@RequestMapping("/pdsm")
public class PdsmController {
	String msgFlag = "";
	
	@Autowired
	PdsmService pdsmService;
	
	@Autowired
	Pagination pagination;
	
	@RequestMapping(value="/pmList", method=RequestMethod.GET)
	public String pmListGet(
			@RequestParam(name="pag", defaultValue="1", required=false) int pag,
			@RequestParam(name="part", defaultValue="전체", required=false) String part,
			Model model) {
		PaginationVO pageVO = pagination.pagination(pag, 5, "pdsm", part, "");
		
		
		List<PdsmVO> vos = pdsmService.getPmList(pageVO.getStartIndexNo(), pageVO.getPageSize(), part);
		
		model.addAttribute("vos", vos);
		model.addAttribute("pageVO", pageVO);
		model.addAttribute("part", part);
		
		return "pdsm/pmList";
	}
	
	@RequestMapping(value="/input/pmInput", method=RequestMethod.GET)
	public String pmInputGet() {
		return "pdsm/input/pmInput";
	}
	
	@RequestMapping(value="/input/pmInput", method=RequestMethod.POST)
	public String pmInputPost(MultipartHttpServletRequest file, PdsmVO vo) {
		pdsmService.pmInput(file, vo);
		
		msgFlag = "pmInputOk";
		return "redirect:/msg/" + msgFlag;
	}
	
	@ResponseBody
	@RequestMapping(value="/downCheck", method=RequestMethod.GET)
	public int downCheckGet(int idx) {
		pdsmService.setDownCheck(idx);
		return 1;
	}
	
	@RequestMapping(value="/pmDown", method=RequestMethod.GET)
	public String pmDownGet(HttpServletRequest request, PdsmVO vo) throws IOException {
		String directory = request.getSession().getServletContext().getRealPath("/resources/pdsm/");
		
		pdsmService.setDownCheck(vo.getIdx());
	  
		String[] fNames = vo.getfName().split("/");
		String[] rfNames = vo.getRfName().split("/");
		
		FileInputStream fis = null;
		
		String zipPath = directory + "imsi/";
		String zipName = vo.getTitle() + ".zip";
		ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipPath + zipName));
		
		byte[] buffer = new byte[2048];
		
		for(int i=0; i<rfNames.length; i++) {
			File file = new File(directory + rfNames[i]);
			File moveAndRename = new File(directory + "imsi/" + fNames[i]);
			
			file.renameTo(moveAndRename);
			
			fis = new FileInputStream(moveAndRename);
			zout.putNextEntry(new ZipEntry(fNames[i]));
			
			int data;
			while((data = fis.read(buffer, 0, buffer.length)) != -1) {
				zout.write(buffer, 0, data);
			}
			
			zout.closeEntry();
			fis.close();
			
			moveAndRename.renameTo(file);
		}
		zout.close();
		
		return "redirect:/pdsm/pmDownAction?file="+java.net.URLEncoder.encode(zipName, "UTF-8");
	}
	
	@RequestMapping(value="/pmDownAction", method=RequestMethod.GET)
	public void downActionGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String rfName = request.getParameter("file");
		String downPath = request.getSession().getServletContext().getRealPath("/resources/pdsm/imsi/")+rfName;
		File downFile = new File(downPath);
		
		String downFileName = null;
		if(request.getHeader("user-agent").indexOf("MSIE") == -1) {
			downFileName = new String(rfName.getBytes("UTF-8"), "8859_1");
		}
		else {
			downFileName = new String(rfName.getBytes("EUC-KR"), "8859_1");
		}
		
		response.setHeader("Content-Disposition", "attachment;filename="+downFileName);
		
		FileInputStream fis = new FileInputStream(downFile);
		ServletOutputStream sos = response.getOutputStream();
		
		byte[] b = new byte[1024];
		int data = 0;
		
		while((data = fis.read(b, 0, b.length)) != -1) {
			sos.write(b, 0, data);
		}
		sos.flush();
		
		sos.close();
		fis.close();
		
		new File(downPath).delete();
	}
	
	@RequestMapping(value="/pmContent")
	public String pmContentGet(int idx, Model model) {
		PdsmVO vo = pdsmService.getPmContent(idx);
		model.addAttribute("vo", vo);
		
		return "pdsm/pmContent";
	}
}
