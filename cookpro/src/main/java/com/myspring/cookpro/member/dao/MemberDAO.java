package com.myspring.cookpro.member.dao;

import com.myspring.cookpro.member.dto.MemberDTO;

public interface MemberDAO {
	int insertMember(MemberDTO member);
	int checkById(String id);
	MemberDTO loginById(MemberDTO member);

}
