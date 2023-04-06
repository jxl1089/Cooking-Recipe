package com.myspring.cookpro.recipeboard.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RecipeDAOImpl implements RecipeDAO{
	@Autowired
	SqlSession sqlSession;
}
