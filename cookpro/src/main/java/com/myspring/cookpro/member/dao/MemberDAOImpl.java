package com.myspring.cookpro.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myspring.cookpro.member.dto.MemberDTO;


@Repository
public class MemberDAOImpl implements MemberDAO{
	@Autowired
	private SqlSession sqlSession;
	
	@Override
	public int insertMember(MemberDTO member) {
		// TODO Auto-generated method stub
		return sqlSession.insert("mapper.member.insertMember", member);
	}

	@Override
	public int checkById(String id) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.member.checkById", id);
	}

	@Override
	public MemberDTO loginById(MemberDTO member) {
		// TODO Auto-generated method stub
		member = sqlSession.selectOne("mapper.member.loginById", member);
		
		return member;
	}

}
