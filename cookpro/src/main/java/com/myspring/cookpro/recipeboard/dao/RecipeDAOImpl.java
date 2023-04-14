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
	public void insertNewImage(Map<String, Object> recipeMap) {
		// TODO Auto-generated method stub
		List<ImageDTO> imageFileList = (List<ImageDTO>) recipeMap.get("recipe_image");
		int recipeNo = (Integer)recipeMap.get("recipe_no");
		int imageFileNo = selectNewImageFileNo();
		for(ImageDTO image : imageFileList) {
			image.setImageFileNo(++imageFileNo);
			image.setrecipeNo(recipeNo);
		}
		sqlSession.insert("mapper.recipe.insertNewImage", imageFileList);
	}

	private int selectNewImageFileNo() {
		// TODO Auto-generated method stub
		return sqlSession.selectOne("mapper.recipe.selectNewImageFileNo");
	}
}
