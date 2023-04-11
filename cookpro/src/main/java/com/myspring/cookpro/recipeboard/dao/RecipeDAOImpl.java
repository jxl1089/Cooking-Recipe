package com.myspring.cookpro.recipeboard.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myspring.cookpro.recipeboard.dto.RecipeDTO;

@Repository
public class RecipeDAOImpl implements RecipeDAO{
	@Autowired
	SqlSession sqlSession;

	@Override
	public List<RecipeDTO> selectAllRecipesList() {
		// TODO Auto-generated method stub
		List<RecipeDTO> recipesList = sqlSession.selectList("mapper.recipe.selectAllRecipeList");
		return recipesList;
	}
}
