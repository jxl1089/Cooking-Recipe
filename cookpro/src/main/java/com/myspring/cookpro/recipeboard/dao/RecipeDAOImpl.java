package com.myspring.cookpro.recipeboard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.myspring.cookpro.recipeboard.dto.ImageDTO;
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

	@Override
	public int insertNewRecipe(Map<String, Object> recipeMap) {
		// TODO Auto-generated method stub
		int recipeNo = selectNewRecipeNo();
		recipeMap.put("recipe_no", recipeNo);
		sqlSession.insert("mapper.recipe.insertNewRecipe", recipeMap);
		return recipeNo;
	}

	private int selectNewRecipeNo() {
		Integer result = sqlSession.selectOne("mapper.recipe.selectNewRecipeNo");
		if(result == null) {
			result = 1;
		}
		return (int) result;
	}

	@Override
	public RecipeDTO selectRecipe(int recipeNo) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.recipe.selectRecipe", recipeNo);
	}

	@Override
	public void updateRecipe(Map<String, Object> recipeMap) {
		// TODO Auto-generated method stub
		sqlSession.update("mapper.recipe.updateRecipe", recipeMap);
		
	}

	@Override
	public void likeUpdate(Map<String, Object> recipeMap) {
		// TODO Auto-generated method stub
		sqlSession.update("mapper.recipe.likeUpdate", recipeMap);
	}

	@Override
	public void dislikeUpdate(Map<String, Object> recipeMap) {
		// TODO Auto-generated method stub
		sqlSession.update("mapper.recipe.dislikeUpdate", recipeMap);
		
	}


}
