package com.myspring.cookpro.recipeboard.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myspring.cookpro.recipeboard.dao.RecipeDAO;
import com.myspring.cookpro.recipeboard.dto.RecipeDTO;

@Service
public class RecipeServiceImpl implements RecipeService{
	@Autowired
	private RecipeDAO recipeDAO;

	@Override
	public List<RecipeDTO> recipesList() {
		// TODO Auto-generated method stub
		return recipeDAO.selectAllRecipesList();
	}

	@Override
	public int addNewRecipe(Map<String, Object> recipeMap) {
		// TODO Auto-generated method stub
		int recipeNo = recipeDAO.insertNewRecipe(recipeMap);
		recipeMap.put("recipe_no", recipeNo);
		//recipeDAO.insertNewImage(recipeMap);
		return recipeNo;
	}

	@Override
	public Map viewRecipe(int recipeNo) {
		// TODO Auto-generated method stub
		Map recipeMap = new HashMap();
		RecipeDTO recipeDTO = recipeDAO.selectRecipe(recipeNo);
		recipeMap.put("recipe", recipeDTO);
		return recipeMap;
	}

	@Override
	public void modRecipe(Map<String, Object> recipeMap) {
		// TODO Auto-generated method stub
		recipeDAO.updateRecipe(recipeMap);
		
	}

	@Override
	public void likeupRecipe(Map<String, Object> recipeMap) {
		// TODO Auto-generated method stub
		recipeDAO.likeUpdate(recipeMap);
		
	}

	@Override
	public void dislikeupRecipe(Map<String, Object> recipeMap) {
		// TODO Auto-generated method stub
		recipeDAO.dislikeUpdate(recipeMap);
		
	}

}
