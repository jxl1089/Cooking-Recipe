package com.myspring.cookpro.member.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.myspring.cookpro.member.dao.MemberDAO;
import com.myspring.cookpro.member.dto.MemberDTO;


@Service
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO memberDAO;
	@Autowired
	private JavaMailSender mailSender;

	
	@Override
	public int addMember(MemberDTO member) {
		// TODO Auto-generated method stub
		return memberDAO.insertMember(member);
	}

	@Override
	public int checkById(String id) {
		// TODO Auto-generated method stub
		return memberDAO.checkById(id);
	}

	@Override
	public MemberDTO login(MemberDTO member) {
		// TODO Auto-generated method stub
		return memberDAO.loginById(member);
	}

	@Override
	public void sendMail(String to, String subject, String body) {
		// TODO Auto-generated method stub
		MimeMessage message = mailSender.createMimeMessage();
		
		try {
			MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
			messageHelper.setFrom("admin@admin.com", "우리의레시피");
			messageHelper.setSubject(subject);
			messageHelper.setTo(to);
			messageHelper.setText(body, true);
			mailSender.send(message);
		} catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	@Override
	public int modMember(MemberDTO member) {
		// TODO Auto-generated method stub
		return memberDAO.updateMember(member);
	}

	@Override
	public int delMember(String id) {
		// TODO Auto-generated method stub
		return memberDAO.deleteMember(id);
	}

	@Override
	public String searchId(MemberDTO member) {
		// TODO Auto-generated method stub
		return memberDAO.searchId(member);
	}

	@Override
	public String searchPwd(MemberDTO member) {
		// TODO Auto-generated method stub
		return memberDAO.searchPwd(member);
	}
}
