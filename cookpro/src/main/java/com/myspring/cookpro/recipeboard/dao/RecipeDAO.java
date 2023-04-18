package com.myspring.cookpro.recipeboard.dao;

import java.util.List;
import java.util.Map;

import com.myspring.cookpro.recipeboard.dto.RecipeDTO;

public interface RecipeDAO {

	List<RecipeDTO> selectAllRecipesList();

	int insertNewRecipe(Map<String, Object> recipeMap);

	RecipeDTO selectRecipe(int recipeNo);

	void updateRecipe(Map<String, Object> recipeMap);

	void likeUpdate(Map<String, Object> recipeMap);

	void dislikeUpdate(Map<String, Object> recipeMap);

	List<RecipeDTO> selectKrRecipesList();

	List<RecipeDTO> selectEnRecipesList();

	List<RecipeDTO> selectCnRecipesList();

	List<RecipeDTO> selectJpRecipesList();

	List<RecipeDTO> selectOtRecipesList();



	//void insertNewImage(Map<String, Object> recipeMap);

}
