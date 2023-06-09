package com.myspring.cookpro.member.dao;

import com.myspring.cookpro.member.dto.MemberDTO;

public interface MemberDAO {
	int insertMember(MemberDTO member);
	int checkById(String id);
	MemberDTO loginById(MemberDTO member);
	int updateMember(MemberDTO member);
	int deleteMember(String id);
	String searchId(MemberDTO member);
	String searchPwd(MemberDTO member);
}
