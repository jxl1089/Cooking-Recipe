package com.myspring.cookpro.member.service;

import com.myspring.cookpro.member.dto.MemberDTO;

public interface MemberService {
	int addMember(MemberDTO member);
	int checkById(String id);
	MemberDTO login(MemberDTO member);
	void sendMail(String to, String subject, String body);
	void delete_Member(String member);

}
