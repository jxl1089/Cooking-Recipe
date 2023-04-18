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
		sqlSession.update("mapper.recipe.viewUp", recipeNo);
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

	@Override
	public List<RecipeDTO> selectKrRecipesList() {
		// TODO Auto-generated method stub
		List<RecipeDTO> recipesList = sqlSession.selectList("mapper.recipe.selectKrRecipeList");
		return recipesList;
	}

	@Override
	public List<RecipeDTO> selectEnRecipesList() {
		// TODO Auto-generated method stub
		List<RecipeDTO> recipesList = sqlSession.selectList("mapper.recipe.selectEnRecipeList");
		return recipesList;
	}

	@Override
	public List<RecipeDTO> selectCnRecipesList() {
		// TODO Auto-generated method stub
		List<RecipeDTO> recipesList = sqlSession.selectList("mapper.recipe.selectCnRecipeList");
		return recipesList;
	}

	@Override
	public List<RecipeDTO> selectJpRecipesList() {
		// TODO Auto-generated method stub
		List<RecipeDTO> recipesList = sqlSession.selectList("mapper.recipe.selectJpRecipeList");
		return recipesList;
	}

	@Override
	public List<RecipeDTO> selectOtRecipesList() {
		// TODO Auto-generated method stub
		List<RecipeDTO> recipesList = sqlSession.selectList("mapper.recipe.selectOtRecipeList");
		return recipesList;
	}

	@Override
	public List<RecipeDTO> selectToRecipesList() {
		// TODO Auto-generated method stub
		List<RecipeDTO> recipesList = sqlSession.selectList("mapper.recipe.selectToRecipeList");
		return recipesList;
	}

	@Override
	public void deleteRecipe(int recipe_no) {
		// TODO Auto-generated method stub
		sqlSession.delete("mapper.recipe.deleteRecipe", recipe_no);
	}





}
