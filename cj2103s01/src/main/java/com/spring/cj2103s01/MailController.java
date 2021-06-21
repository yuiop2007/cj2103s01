package com.spring.cj2103s01;

import java.io.File;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.cj2103s01.vo.MailVO;

@Controller
@RequestMapping("/mail")
public class MailController {
	
	@Autowired
	JavaMailSender mailSender;
  
	@RequestMapping(value = "/mailForm", method = RequestMethod.GET)
	public String mailFormGet() {
		return "mail/mailForm";
	}
	
	@RequestMapping(value = "/mailForm", method = RequestMethod.POST)
	public String mailFormPost(MailVO vo) {
		String toMail = vo.getToMail();
		String title = vo.getTitle();
		String content = vo.getContent();
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			
			// 메일보관함에 회원이 보낸 메세지 저장
			messageHelper.setTo(toMail);
			messageHelper.setSubject(title);
			messageHelper.setText(content);
			
			// 메세지 내용을 전송한다.(전송전에 필요한 '텍스트+사진' 등에 대한 작업..)
			content = content.replace("\n", "<br>");
			content += "<br><hr><h3>cj2103s10 입니다.</h3><hr><br>";
			content += "<p><img src=\"cid:11.jpg\" width='500px'></p><hr>";
			content += "<p>Have a good time!!!</p>";
			content += "<p><a href='http://218.236.203.146:9090/cj2103s10'>cj2103s10</a></p>";
			messageHelper.setText(content, true);
			// 본문 내부에 그림파일을 함께 보내고자 할때.. 그림을 먼저 저장하고, 저장경로와 함께 파일명을 적는다.
			FileSystemResource file = new FileSystemResource(new File("C:/11.jpg"));
			messageHelper.addInline("11.jpg", file);
			
			// 파일 첨부하기(기타 다른파일을 첨부할 수 있다.)
			FileSystemResource mfile = new FileSystemResource(new File("D:/java/11.jpg"));
			messageHelper.addAttachment("11.jpg", mfile);
			
			mailSender.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "redirect:/msg/mailSendOk";
	}
	
	// 임시 비밀번호 발급한것 전송하기
	@RequestMapping(value = "/pwdConfirmMailForm/{mEmail}/{content}/", method = RequestMethod.GET)
	public String pwdConfirmMailFormGet(@PathVariable String mEmail, @PathVariable String content) {
		String fromMail = "ehgmd17@gmail.com";
		String title = ">> 임시 비밀번호를 발급하였습니다.";
		String pwd = content;
		content = "cj2103s01에서 발송한 메일입니다.\n아래 임시 비밀번호를 보내오니 사이트에 접속하셔서 비밀번호를 변경하세요\n";
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
			
			// 메일보관함에 회원이 보낸 메세지 저장
			messageHelper.setFrom(fromMail);
			messageHelper.setTo(mEmail);
			messageHelper.setSubject(title);
			
			// 메세지 내용을 전송한다.(전송전에 필요한 '텍스트+사진' 등에 대한 작업..)
			content = content.replace("\n", "<br>");
			content += "<br><hr><h3>임시비밀번호 : <font color='red'>"+pwd+"</font></h3><hr><br>";
//			content += "<p><img src=\"cid:11.jpg\" width='500px'></p><hr>";
			content += "<p>Have a good time!!!</p>";
			content += "<p>접속주소 : <a href='http://218.236.203.166:9090/cj2103s01'>cj2103s01</a></p>";
			messageHelper.setText(content, true);
			// 본문 내부에 그림파일을 함께 보내고자 할때.. 그림을 먼저 저장하고, 저장경로와 함께 파일명을 적는다.
//			FileSystemResource file = new FileSystemResource(new File("C:/11.jpg"));
//			messageHelper.addInline("11.jpg", file);
			
			mailSender.send(message);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return "redirect:/msg/pwdConfirmOk";
	}
	
	// 아이디 찾은것 전송하기
		@RequestMapping(value = "/idConfirmMailForm/{mEmail}/{content}/", method = RequestMethod.GET)
		public String idConfirmMailFormGet(@PathVariable String mEmail, @PathVariable String content) {
			String fromMail = "ehgmd17@gmail.com";
			String title = ">> 임시 비밀번호를 발급하였습니다.";
			String id = content;
			content = "cj2103s01에서 발송한 메일입니다.\n 가입하신 아이디입니다.\n";
			
			try {
				MimeMessage message = mailSender.createMimeMessage();
				MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");
				
				// 메일보관함에 회원이 보낸 메세지 저장
				messageHelper.setFrom(fromMail);
				messageHelper.setTo(mEmail);
				messageHelper.setSubject(title);
				
				// 메세지 내용을 전송한다.(전송전에 필요한 '텍스트+사진' 등에 대한 작업..)
				content = content.replace("\n", "<br>");
				content += "<br><hr><h3>아이디 : <font color='red'>"+id+"</font></h3><hr><br>";
				content += "<p>Have a good time!!!</p>";
				content += "<p>접속주소 : <a href='http://218.236.203.166:9090/cj2103s01'>cj2103s01</a></p>";
				messageHelper.setText(content, true);
				
				mailSender.send(message);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return "redirect:/msg/idConfirmOk";
		}
}